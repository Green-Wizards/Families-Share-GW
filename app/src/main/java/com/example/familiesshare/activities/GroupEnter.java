package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import classes.Dependents;
import classes.Members;

public class GroupEnter extends AppCompatActivity {

    private TextView nomeGruppo, locationGruppo, descrizioneGruppo;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String groupid;
    private boolean visibleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_enter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        groupid  = intent.getStringExtra("groupid");


        nomeGruppo = findViewById(R.id.nomeGruppoAggiungi);
        locationGruppo = findViewById(R.id.locationAggiungi);
        descrizioneGruppo = findViewById(R.id.DescAggiungi);

        updateUI();

    }

    public void updateUI(){
        mDatabase.child("Groups").child(groupid).child("name").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(GroupEnter.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   nomeGruppo.setText((String) task.getResult().getValue());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Groups").child(groupid).child("location").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(GroupEnter.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   locationGruppo.setText((String) task.getResult().getValue());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Groups").child(groupid).child("description").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(GroupEnter.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   descrizioneGruppo.setText((String) task.getResult().getValue());
                                               }
                                           }
                                       }
                );
    }


    public void onClickEnter(View v){

        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Members").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            CheckIfMember((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }


    }

    private void CheckIfMember(Map<String,Object> membri) {

        for (Map.Entry<String, Object> entry : membri.entrySet()){
            Map membroTrovati = (Map) entry.getValue();
            //String idgruppo =  entry.getKey();

            if ( membroTrovati.get("user_id").equals(mAuth.getCurrentUser().getUid()) && membroTrovati.get("group_id").equals(groupid)){
                Toast.makeText(GroupEnter.this, "Fai già parte del gruppo!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, Group.class);
                i.putExtra("group_id", groupid);
                startActivity(i);
            }else{
                Members membro = new Members(mAuth.getCurrentUser().getUid(), groupid, false, false);
                FirebaseDatabase.getInstance().getReference("Members")
                        .child(groupid)
                        .setValue(membro).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(GroupEnter.this, "Aggiunta membro avvenuta con successo!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(GroupEnter.this, Group.class);
                                    i.putExtra("group_name", (String) nomeGruppo.getText());
                                    i.putExtra("group_id", groupid);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(GroupEnter.this, "Creazioine del membro fallita!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        }
    }


    public void goBack(View v){
        Intent i = new Intent(this, CercaGruppi.class);
        i.putExtra("sender", "drawer");
        startActivity(i);
    }
}
