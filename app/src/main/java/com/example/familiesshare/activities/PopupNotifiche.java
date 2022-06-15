package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class PopupNotifiche extends AppCompatActivity {

    private LinearLayout linLayout;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_notifiche);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        linLayout = (LinearLayout) findViewById(R.id.notificationZone);

        getNotifications();
    }


    public void getNotifications(){
        mDatabase.child("Notifications").addListenerForSingleValueEvent( //gruppi a cui si partecipa
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map in datasnapshot
                        ShowNotifications((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    public void ShowNotifications(Map<String,Object> mappaNotifiche){
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);

        //itera tutti i gruppi
        for (Map.Entry<String, Object> entry : mappaNotifiche.entrySet()){
            //Get user map
            Map notificatrovata = (Map) entry.getValue();
            String idGruppo =  entry.getKey();
            //Aggiungi alla lista dei gruppi se il gruppo è dell'utente
            if (notificatrovata.get("receiver_id").equals(mAuth.getCurrentUser().getUid())
                && notificatrovata.get("read").toString().equals("false")){ //gruppi a cui si partecipa, quindi user_id
                Button btn = new Button(this);

                Toast.makeText(PopupNotifiche.this, "TEST HERE", Toast.LENGTH_LONG).show();

                if(notificatrovata.get("subject").equals("FN")) {
                    btn.setText("Sei stato inserito in un nucleo familiare!");
                    btn.setTag(counter);
                    btn.setOnClickListener(v -> {
                        Intent i = new Intent(PopupNotifiche.this, GruppoFamiliare.class);
                        startActivity(i);
                    });
                    linLayout.addView(btn);
                }
                else{
                    Toast.makeText(PopupNotifiche.this, "visualizzo notifiche: " + notificatrovata.get("subject").toString(), Toast.LENGTH_LONG).show();
                    String s = "Sei invitato ad entrare in un gruppo!";
                    btn.setText(s);
                    btn.setTag(counter);
                    btn.setOnClickListener(v -> {
                        Intent i = new Intent(PopupNotifiche.this, GroupEnter.class);
                        i.putExtra("groupid", notificatrovata.get("subject").toString());
                        startActivity(i);
                    });
                    linLayout.addView(btn);
                }

                bottoni.add(btn);
                counter += 1;
            }
        }
    }

    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }



}
