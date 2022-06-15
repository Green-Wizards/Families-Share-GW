package com.example.familiesshare.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  ActivityTimeslot extends Activity {
    private String timeslot_id;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String dependents;
    private String volunteers;
    private String activity_id;
    private String group_id;
    private String nome;
    private String addDep;
    private String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeslot);

        findViewById(R.id.addedDependents).setVisibility(View.GONE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            DatabaseReference m = mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("given_name").getRef();

            m.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    nome = (String) task.getResult().getValue();
                }
            });
        }
        Intent intent = getIntent();
        timeslot_id = intent.getStringExtra("timeslot_id");
        activity_id = intent.getStringExtra("activity_id");
        group_id = intent.getStringExtra("group_id");
        getData();
        setData();
    }

    private void getData(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            DatabaseReference m = mDatabase.child("Subscriptions").child(activity_id).child("timeslot_id").equalTo(timeslot_id).getRef();

            m.child("dependents").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    dependents = (String) task.getResult().getValue();
                }
            });
            m.child("volunteers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    volunteers = (String) task.getResult().getValue();
                }
            });
        }
    }

    private void setData(){
        ((TextView) findViewById(R.id.timeslot)).setText(timeslot_id);
        ((TextView) findViewById(R.id.volunteersList)).setText(volunteers);
        ((TextView) findViewById(R.id.dependentsList)).setText(dependents);
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
        if(mAuth.getCurrentUser() != null) {
            volunteers = volunteers + nome + "\n";
            mDatabase.child("Subscriptions").child(activity_id).child("volunteers").setValue(volunteers);
            goBack(v);
        }
    }

    public void aggiungi(View v){
        addDep = ((EditText) findViewById(R.id.editDependent)).getText().toString().trim();
        str = str + addDep +"\n";
        String tmp = "Utenti a carico aggiunti:\n" + str;
        ((TextView) findViewById(R.id.InfoList)).setText(tmp);
        findViewById(R.id.addedDependents).setVisibility(View.VISIBLE);
        dependents = dependents + addDep + "\n";
        mDatabase.child("Subscriptions").child(activity_id).child("dependents").setValue(dependents);
    }
}
