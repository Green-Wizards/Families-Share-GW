package com.example.familiesshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void confirm (View v){
        TextView input1 = findViewById(R.id.editTextTextEmailAddress);
        this.email = input1.getText().toString();
        TextView input2 = findViewById(R.id.editTextTextPassword);
        this.password = input2.getText().toString();
    }
}