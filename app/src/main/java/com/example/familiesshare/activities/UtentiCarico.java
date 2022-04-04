package com.example.familiesshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;

public class UtentiCarico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utenti_carico);
    }

    public void account(View v){
        Intent i = new Intent(this, AccountActivity.class);
        startActivity(i);
    }

    public void aggiungiUtenteCarico(View v){
        Intent i = new Intent(this, addUser.class);
        startActivity(i);
    }
}