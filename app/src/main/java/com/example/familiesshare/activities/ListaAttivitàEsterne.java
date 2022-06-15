package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ListaAttivitàEsterne extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_attivita_esterne);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        showAE();
    }

    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    private void showAE(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Activities").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ShowAEButton((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    private void ShowAEButton(Map<String,Object> mappaActivities) {
        LinearLayout constr;
        constr = (LinearLayout) findViewById(R.id.AEZone);
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);
            //itera tutti i gruppi
            for (Map.Entry<String, Object> entry : mappaActivities.entrySet()){
                //Get user map
                Map actTrovato = (Map) entry.getValue();
                String idAct =  entry.getKey();
                //Aggiungi alla lista dei gruppi se il gruppo è dell'utente
                if (actTrovato.get("group_id").equals(idAct)){
                    Button btn = new Button(this);
                    String str = (String) actTrovato.get("activity_name") + "\n" + (String) actTrovato.get("location");
                    btn.setText(str);
                    btn.setTag(counter);
                    btn.setOnClickListener(v -> {
                        Intent i = new Intent(this, ActivityParticipation.class);
                        i.putExtra("activity_id", idAct);
                        i.putExtra("group_id", idAct);
                        startActivity(i);
                    });
                    constr.addView(btn);
                    bottoni.add(btn);
                    counter += 1;
                }
            }
        }
    }
