package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.UUID;

import classes.Notifications;

public class PopupNF extends AppCompatActivity {
    private String userid;
    private String email;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_invite_nf);
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private void ListUsers(Map<String,Object> mappaUsers) {
        boolean found = false;
        for (Map.Entry<String, Object> entry : mappaUsers.entrySet()){
            Map userTrovato = (Map) entry.getValue();
            String idReceiver =  entry.getKey();
            if(userTrovato.get("email").equals(email)){
                found = true;
                String uniqueID = UUID.randomUUID().toString();
                Notifications n = new Notifications(mAuth.getCurrentUser().getUid(), idReceiver, "FN", false);
                FirebaseDatabase.getInstance().getReference("Notifications")
                        .child(uniqueID).setValue(n).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PopupNF.this, "Nucleo familiare aggiornato!", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(PopupNF.this, "Errore nell'aggiornamento!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
            else{
                if(found)
                    Toast.makeText(PopupNF.this, "L'utente cercato non esiste", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkEmail(){
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Profiles").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ListUsers((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    public void inviteNF(View v){
        email = ((EditText) findViewById(R.id.editTextTextPersonNameNF)).getText().toString().trim();
        checkEmail();
        Intent i= new Intent(this, GruppoFamiliare.class);
        i.putExtra("userid", userid);
        startActivity(i);
        }

    public void goBack(View v){
        Intent i = new Intent(this, GruppoFamiliare.class);
        i.putExtra("userid", userid);
        startActivity(i);
    }
}
