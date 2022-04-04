package com.example.familiesshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    public void utentiCarico(View v){
        Intent i = new Intent(this, UtentiCarico.class);
        startActivity(i);
    }
}