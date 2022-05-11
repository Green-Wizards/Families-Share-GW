package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;
import com.google.android.material.navigation.NavigationView;

public class NewGroupCreation extends AppCompatActivity{

    View v1 = findViewById(R.id.view1);
    View v2 = findViewById(R.id.view2);
    View v3 = findViewById(R.id.view3);
    View v4 = findViewById(R.id.view4);
    View v5 = findViewById(R.id.view5);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_creation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void menu(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void go2(View v){
        v1.setVisibility(View.GONE);
        v2.setVisibility(View.VISIBLE);
    }

    public void go3(View v){
        v2.setVisibility(View.GONE);
        v3.setVisibility(View.VISIBLE);
    }

    public void go4(View v){
        v3.setVisibility(View.GONE);
        v4.setVisibility(View.VISIBLE);
    }

    public void go5(View v){
        v4.setVisibility(View.GONE);
        v5.setVisibility(View.VISIBLE);
    }

    public void back1(View v){
        v2.setVisibility(View.GONE);
        v1.setVisibility(View.VISIBLE);
    }

    public void back2(View v){
        v2.setVisibility(View.GONE);
        v1.setVisibility(View.VISIBLE);
    }
    public void back3(View v){
        v3.setVisibility(View.GONE);
        v4.setVisibility(View.VISIBLE);
    }
    public void back4(View v){
        v5.setVisibility(View.GONE);
        v4.setVisibility(View.VISIBLE);
    }

    public void end(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

}