package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class GroupActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public String nomegruppo;
    public String idgruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_activity);

        Intent intent = getIntent();
        nomegruppo = intent.getStringExtra("group_name");
        idgruppo = intent.getStringExtra("group_id");
    }

    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void goInfo(View v){
        Intent i = new Intent(this, Group.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }

    public void goActivity(View v){
        Intent i = new Intent(this, GroupActivity.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }

    public void goMembers(View v){
        Intent i = new Intent(this, GroupMembers.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }

    public void new_activity (View view) {
        Intent i = new Intent(this, NewActivity.class);
        i.putExtra("group_name", nomegruppo );
        i.putExtra("group_id", idgruppo );
        startActivity(i);
    }
}
