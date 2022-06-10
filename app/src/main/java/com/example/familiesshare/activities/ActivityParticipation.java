package com.example.familiesshare.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
