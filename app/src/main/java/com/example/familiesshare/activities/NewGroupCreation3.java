package com.example.familiesshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;

public class NewGroupCreation3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_creation3);
    }

    public void go2(View v){
        Intent i = new Intent(this, NewGroupCreation2.class);
        startActivity(i);
    }

    public void go4(View v){
        Intent i = new Intent(this, NewGroupCreation4.class);
        startActivity(i);
    }
}