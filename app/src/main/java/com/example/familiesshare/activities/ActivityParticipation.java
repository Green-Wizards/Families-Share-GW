package com.example.familiesshare.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import java.util.Objects;

public class ActivityParticipation extends Activity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String activity_id;
    private String activity_name;
    private String description;
    private String location;
    private String color;
    private long minUsers;
    private long maxUsers;
    private long minDependents;
    private long maxDependents;
    private String group_id;
    private String name;
    private String timeslotName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        activity_id = intent.getStringExtra("activity_id");
        group_id = intent.getStringExtra("group_id");
        getData();
        //setData();
        getTimeslots();

    }

    private void getData(){

        if(mAuth.getCurrentUser() != null){
            mDatabase.child("Activities").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Get map in datasnapshot
                            GetActivities((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }
                    });
        }
    }

    private void GetActivities(Map<String,Object> activities) {
        //itera tutti i gruppi
        for (Map.Entry<String, Object> entry : activities.entrySet()){
            //Get user map
            String activity =  entry.getKey();
            if(activity.equals(activity_id)) {
                //mDatabase = mDatabase.child("Activities").child(activity);


            //Toast.makeText(ActivityParticipation.this, m.toString(), Toast.LENGTH_LONG).show();
                mDatabase.child("Activities").child(activity).child("description").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    description = (String) task.getResult().getValue();
                    ((TextView) findViewById(R.id.activityDescription)).setText(description);
                }
            });
                mDatabase.child("Activities").child(activity).child("location").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    location = (String) task.getResult().getValue();
                    ((TextView) findViewById(R.id.activityLocation)).setText(location);
                }
            });
                mDatabase.child("Activities").child(activity).child("activity_name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    activity_name = (String) task.getResult().getValue();
                    ((TextView) findViewById(R.id.activityName)).setText(activity_name);
                }
            });
                mDatabase.child("Activities").child(activity).child("minUsers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        minUsers = (Long) task.getResult().getValue();
                    }
                });
                mDatabase.child("Activities").child(activity).child("maxUsers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        maxUsers = (Long) task.getResult().getValue();
                    }
                });
                mDatabase.child("Activities").child(activity).child("minDependents").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        minDependents = (Long) task.getResult().getValue();
                    }
                });
                mDatabase.child("Activities").child(activity).child("maxDependents").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        maxDependents = (Long) task.getResult().getValue();
                    }
                });

            }
        }
    }

    private void setData(){
        ((TextView) findViewById(R.id.activityName)).setText(activity_name);
        ((TextView) findViewById(R.id.activityDescription)).setText(description);
        ((TextView) findViewById(R.id.activityLocation)).setText(location);
    }

    private void getTimeslots(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Timeslots").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Get map in datasnapshot
                            ShowUserTimeslots((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }
                    });
        }
    }

    private void ShowUserTimeslots(Map<String,Object> mappaSlots) {

        ArrayList<String> timeslots = new ArrayList<>();
        LinearLayout constr;
        constr = (LinearLayout) findViewById(R.id.buttonZone);
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);

        //itera tutti i gruppi
        for (Map.Entry<String, Object> entry : mappaSlots.entrySet()){

            //Get user map
            Map timeslot = (Map) entry.getValue();
            //Aggiungi alla lista dei gruppi se il gruppo è dell'utente
            if (((String) timeslot.get("activity_id")).equals(activity_id)){
                timeslotName = (String) (timeslot.get("date") + " " + timeslot.get("startTime") +" - "+ timeslot.get("endTime"));
                timeslots.add((String) timeslot.get(timeslotName));

                Button btn = new Button(this);
                btn.setTag(counter);
                btn.setText(timeslotName);
                btn.setOnClickListener(v -> {
                    Intent i = new Intent(ActivityParticipation.this, ActivityTimeslot.class);
                    i.putExtra("timeslot_id", entry.getKey());
                    i.putExtra("activity_id", activity_id);
                    i.putExtra("timeslot_name", timeslotName);
                    i.putExtra("group_id", group_id);
                    i.putExtra("minV", minUsers);
                    i.putExtra("maxV", maxUsers);
                    i.putExtra("minD", minDependents);
                    i.putExtra("maxD", maxDependents);
                    startActivity(i);
                });
                constr.addView(btn);
                bottoni.add(btn);
                counter += 1;
            }
        }
    }

    public void goBack(View v){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Groups").child("group_id").child("group_name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    name = (String) task.getResult().getValue();
                }
            });
        }
        Intent i = new Intent(this, GruppoAttivita.class);
        i.putExtra("group_name", name);
        i.putExtra("group_id", group_id);

        startActivity(i);
    }
}
