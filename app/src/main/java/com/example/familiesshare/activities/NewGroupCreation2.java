package com.example.familiesshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;

public class NewGroupCreation2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_creation2);
    }

    public void go3(View v){
        Intent i = new Intent(this, NewGroupCreation3.class);
        startActivity(i);
    }

    public void go1(View v){
        Intent i = new Intent(this, NewGroupCreation.class);
        startActivity(i);
    }
}