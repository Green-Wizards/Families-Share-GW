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

public class InviteMember extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String emailStr;
    private String nomegruppo;
    private String idgruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_invite_member);

        Intent intent = getIntent();
        nomegruppo = intent.getStringExtra("group_name");
        idgruppo = intent.getStringExtra("group_id");
    }

    private void getText(){
        emailStr = ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString().trim();

    }

    public void goBack(View v){
        Intent i= new Intent(InviteMember.this, GroupMembers.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }

    private void checkEmail(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
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

    private void ListUsers(Map<String,Object> mappaUsers) {
        boolean found = false;
        for (Map.Entry<String, Object> entry : mappaUsers.entrySet()){
            Map userTrovato = (Map) entry.getValue();
            String idReceiver =  entry.getKey();
            getText();
            if(userTrovato.get("email").equals(emailStr)){
                found = true;
                String uniqueID = UUID.randomUUID().toString();
                Notifications n = new Notifications(mAuth.getCurrentUser().getUid(), idReceiver, "Member", false);
                FirebaseDatabase.getInstance().getReference("Notifications")
                        .child(uniqueID).setValue(n).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(InviteMember.this, "Invito mandato!", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(InviteMember.this, "Errore nell'invio dell'invito!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
            else{
                if(found)
                    Toast.makeText(InviteMember.this, "L'utente cercato non esiste", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void sendInvite(View v){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(mAuth.getCurrentUser() != null) {
            checkEmail();
            Intent i= new Intent(InviteMember.this, GroupMembers.class);
            i.putExtra("group_name", nomegruppo);
            i.putExtra("group_id", idgruppo);
            startActivity(i);
        }
    }
}
