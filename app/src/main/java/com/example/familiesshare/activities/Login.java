package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.familiesshare.R;

public class Login extends AppCompatActivity {

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void confirm (View v){
        TextView input1 = findViewById(R.id.editTextTextEmailAddress);
        this.email = input1.getText().toString();
        TextView input2 = findViewById(R.id.editTextTextPassword);
        this.password = input2.getText().toString();
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }
}