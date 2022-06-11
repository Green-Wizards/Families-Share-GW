package com.example.familiesshare.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ActivityParticipation extends Activity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String activity_id;
    private String group_id;
    private String group_name;
    private String description;
    private String location;
    private String color;
    private String creator_id;
    private boolean repetition;
    private String repetition_type;
    private boolean different_timeslot;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation);

        activity_id = savedInstanceState.getString("activity_id");
        getData(activity_id);
        getTimeslots(activity_id);
    }

    private void getData(String s){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            DatabaseReference m = mDatabase.child("ActivityFS").child("activity_id").equalTo(activity_id).getRef();
            group_id = m.child("group_id").getKey();
            group_name = m.child("group_name").getKey();
            description = m.child("description").getKey();
            location = m.child("location").getKey();
            color = m.child("color").getKey();
            creator_id = m.child("creator_id").getKey();
            repetition = getBoolean(m.child("repetition").getKey());
            repetition_type = m.child("repetition_type").toString();
            different_timeslot = getBoolean(m.child("different_timeslot").toString());
            status = m.child("status").toString();
        }
    }

    private void getTimeslots(String activity_id){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Timeslot").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Get map in datasnapshot
                            ShowUserTimeslots((Map<String,Object>) dataSnapshot.getValue(), activity_id);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }
                    });

        }
    }

    private void ShowUserTimeslots(Map<String,Object> mappaSlots, String activity_id) {

        ArrayList<String> timeslots = new ArrayList<>();
        ConstraintLayout constr;
        constr = (ConstraintLayout) findViewById(R.id.buttonZone);
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);

        //itera tutti i gruppi
        for (Map.Entry<String, Object> entry : mappaSlots.entrySet()){

            //Get user map
            Map timeslot = (Map) entry.getValue();
            //Aggiungi alla lista dei gruppi se il gruppo è dell'utente
            if (timeslot.get("activity_id").equals(activity_id)){
                timeslots.add((String) timeslot.get("timeslot_id"));

                Button btn = new Button(this);
                btn.setX(100);
                btn.setY(200*(counter+1));
                btn.setHeight(80);
                btn.setWidth(875);
                btn.setText((String) timeslot.get("timeslot_id"));
                btn.setTag(counter);
                btn.setOnClickListener(v -> {
                    Intent i = new Intent(ActivityParticipation.this, ActivityTimeslot.class);
                    i.putExtra("timeslot_id", (String) timeslot.get("timeslot_id"));
                    startActivity(i);
                });
                constr.addView(btn);

                bottoni.add(btn);
                //per adesso i gruppi mostrati, se superiori a 3, sovrascrivono il resto dei pulsanti dell'itnerfaccia
                counter += 1;
            }
        }
    }

    private boolean getBoolean(String s){
        if(s.equals("true"))
            return true;
        else
            return false;
    }

    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }
}
