package com.example.familiesshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signup_class(View v){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void login_class(View v){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}