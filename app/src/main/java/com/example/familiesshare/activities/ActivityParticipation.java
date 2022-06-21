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
    private String creator_id;
    private boolean repetition;
    private String repetition_type;
    private boolean different_timeslot;
    private String status;
    private String group_id;
    private String name;

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
        setData();
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
            /*m.child("color").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    color=  (String) task.getResult().getValue();
                }
            });*/
            /*m.child("creator_id").get().addOnCompleteListener(task -> creator_id=  (String) task.getResult().getValue());*/
            /*m.child("repetition").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    repetition=  (boolean) task.getResult().getValue();
                }
            });*/
            /*m.child("repetition_type").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    repetition_type=  (String) task.getResult().getValue();
                }
            });*/
            /*m.child("different_timeslot").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    different_timeslot= (boolean) task.getResult().getValue();
                }
            });
            m.child("status").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    status=  (String) task.getResult().getValue();
                }
            });*/
                mDatabase.child("Activities").child(activity).child("activity_name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    activity_name = (String) task.getResult().getValue();
                    ((TextView) findViewById(R.id.activityName)).setText(activity_name);
                }
            });

            }
        }
    }

    private void setData(){
        ((TextView) findViewById(R.id.activityName)).setText(activity_name);
        Toast.makeText(ActivityParticipation.this, "-----" + ((TextView) findViewById(R.id.activityName)).getText().toString(), Toast.LENGTH_LONG).show();
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
                String tmp = (String) (timeslot.get("date") + " " + timeslot.get("startTime") +" - "+ timeslot.get("endTime"));
                timeslots.add((String) timeslot.get(tmp));

                Button btn = new Button(this);
                btn.setTag(counter);
                btn.setText(tmp);
                btn.setOnClickListener(v -> {
                    Intent i = new Intent(ActivityParticipation.this, ActivityTimeslot.class);
                    i.putExtra("timeslot_id", entry.getKey());
                    i.putExtra("activity_id", activity_id);
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
        Intent i = new Intent(this, Group.class);
        i.putExtra("group_name", name);
        i.putExtra("group_id", group_id);
        startActivity(i);
    }
}
