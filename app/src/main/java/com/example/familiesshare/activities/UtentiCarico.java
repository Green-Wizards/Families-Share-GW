package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;

public class UtentiCarico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utenti_carico);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void account(View v){
        Intent i = new Intent(this, AccountActivity.class);
        startActivity(i);
    }

    /*
    public void aggiungiUtenteCarico(View v){
        Intent i = new Intent(this, addUser.class);
        startActivity(i);
    }
    */
}