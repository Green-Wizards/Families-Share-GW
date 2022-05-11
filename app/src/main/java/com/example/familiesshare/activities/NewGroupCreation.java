package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.familiesshare.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewGroupCreation extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_creation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.view2).setVisibility(View.GONE);
        findViewById(R.id.view3).setVisibility(View.GONE);
        findViewById(R.id.view4).setVisibility(View.GONE);
        findViewById(R.id.view5).setVisibility(View.GONE);
    }


    public void menu(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void go2(View v){
        findViewById(R.id.view1).setVisibility(View.GONE);
        findViewById(R.id.view2).setVisibility(View.VISIBLE);
    }

        public void go3(View v){
            findViewById(R.id.view2).setVisibility(View.GONE);
            findViewById(R.id.view3).setVisibility(View.VISIBLE);
        }

        public void go4(View v){
            findViewById(R.id.view3).setVisibility(View.GONE);
            findViewById(R.id.view4).setVisibility(View.VISIBLE);
        }

        public void go5(View v){
            findViewById(R.id.view4).setVisibility(View.GONE);
            findViewById(R.id.view5).setVisibility(View.VISIBLE);
        }

    public void back1(View v){
        findViewById(R.id.view2).setVisibility(View.GONE);
        findViewById(R.id.view1).setVisibility(View.VISIBLE);
    }

    public void back2(View v){
        findViewById(R.id.view3).setVisibility(View.GONE);
        findViewById(R.id.view2).setVisibility(View.VISIBLE);
    }
    public void back3(View v){
        findViewById(R.id.view4).setVisibility(View.GONE);
        findViewById(R.id.view3).setVisibility(View.VISIBLE);
    }
    public void back4(View v){
        findViewById(R.id.view5).setVisibility(View.GONE);
        findViewById(R.id.view4).setVisibility(View.VISIBLE);
    }

    public void end(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

}