package com.example.familiesshare.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        activity_id = savedInstanceState.getString("activity_id");
        group_id = savedInstanceState.getString("group_id");
        getData(activity_id);
        setData();
        getTimeslots(activity_name);
    }

    private void getData(String activity_id){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            DatabaseReference m = mDatabase.child("ActivityFS").child("activity_id")
                    .equalTo(mDatabase.child("ActivityFS").child("activity_id").equalTo(activity_id).toString()).getRef();
            //controllo id di firebase corretto?
            m.child("description").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    description=  (String) task.getResult().getValue();
                }
            });
            m.child("location").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    location=  (String) task.getResult().getValue();
                }
            });
            m.child("color").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    color=  (String) task.getResult().getValue();
                }
            });
            m.child("creator_id").get().addOnCompleteListener(task -> creator_id=  (String) task.getResult().getValue());
            m.child("repetition").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    repetition=  (boolean) task.getResult().getValue();
                }
            });
            m.child("repetition_type").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    repetition_type=  (String) task.getResult().getValue();
                }
            });
            m.child("different_timeslot").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
            });
            /*
            location = m.child("location").getKey();
            color = m.child("color").getKey();
            creator_id = m.child("creator_id").getKey();
            repetition = getBoolean(m.child("repetition").getKey());
            repetition_type = m.child("repetition_type").toString();
            different_timeslot = getBoolean(m.child("different_timeslot").toString());
            status = m.child("status").toString();
            */
        }
    }

    private void setData(){
        ((TextView) findViewById(R.id.activityName)).setText(activity_name);
        ((TextView) findViewById(R.id.activityDescription)).setText(description);
        ((TextView) findViewById(R.id.activityLocation)).setText(location);
    }

    private void getTimeslots(String activity_name){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Timeslot").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Get map in datasnapshot
                            ShowUserTimeslots((Map<String,Object>) dataSnapshot.getValue(), activity_name);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }
                    });

        }
    }

    private void ShowUserTimeslots(Map<String,Object> mappaSlots, String activity_name) {

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
            if (timeslot.get("activity_name").equals(activity_name)){
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
                    i.putExtra("activity_id", activity_id);
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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Group").child("group_id").child("group_name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
