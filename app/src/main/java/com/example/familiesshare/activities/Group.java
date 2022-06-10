package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Group extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        //prendo una reference all'intent per recuperare il nome del gruppo che voglio far vedere
        Intent intent = getIntent();
        String nomegruppo = intent.getStringExtra("group_name"); //sarebbe meglio usare un id univoco del gruppo

        Toast.makeText(Group.this, "Nome gruppo caricato:" + nomegruppo, Toast.LENGTH_LONG).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


    }

    public void new_activity (View view) {
        Intent i = new Intent(this, NewActivity.class);
        startActivity(i);
    }
}