package com.example.familiesshare.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import classes.Dependent;

public class  ActivityTimeslot extends Activity {
    private String timeslot_id;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ArrayList<Dependent> dependents;
    private ArrayList<String> volunteers;
    private String activity_id;
    private String group_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeslot);

        timeslot_id = savedInstanceState.getString("timeslot_id");
        activity_id = savedInstanceState.getString("activity_id");
        group_id = savedInstanceState.getString("group_id");
        getData(timeslot_id);
        setData();
    }

    private void getData(String activity_timeslot){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            DatabaseReference m = mDatabase.child("Subscription").child("timeslot_id")
                    .equalTo(mDatabase.child("Subscription").child("timeslot_id").equalTo(activity_timeslot).toString()).getRef();
            m.child("dependents").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    dependents = (ArrayList<Dependent>) task.getResult().getValue();
                }
            });
            m.child("volunteers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    volunteers = (ArrayList<String>) task.getResult().getValue();
                }
            });
        }
    }

    private void setData(){
        ((TextView) findViewById(R.id.timeslot)).setText(timeslot_id);
        ((TextView) findViewById(R.id.volunteersList)).setText(getVolunteers());
        ((TextView) findViewById(R.id.dependentsList)).setText(getDependents());
    }

    private String getVolunteers(){
        String n = "";
        for(String s : volunteers){
            n = n + s + "\n";
        }
        return n;
    }

    private String getDependents(){
        String n = "";
        for(Dependent s : dependents){
            n = n + s.given_name + "\n";
        }
        return n;
    }

    public void goBack(View v){
        Intent i = new Intent(this, Group.class);
        i.putExtra("activity_id", "activity_id");
        i.putExtra("group_id", "group_id");
        startActivity(i);
    }

    public void partecipare(View v){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            mDatabase.child("User").equalTo(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    volunteers.add((String) task.getResult().getValue());
                }
            });
            goBack(v);
        }
    }
}
