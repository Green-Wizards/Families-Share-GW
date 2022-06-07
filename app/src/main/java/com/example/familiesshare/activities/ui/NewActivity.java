package com.example.familiesshare.activities.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;
import com.example.familiesshare.activities.Group;
import com.example.familiesshare.activities.PopupNotifiche;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.GONE);
    }

    public void back(View view){
        Intent i = new Intent(this, Group.class);
        startActivity(i);
    }

    public void back2(View v){
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
    }
    public void back3(View v){
        findViewById(R.id.linearLayout3).setVisibility(View.GONE);
        findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
    }

    public void go2(View v){
        findViewById(R.id.linearLayout).setVisibility(View.GONE);
        findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
    }

    public void go3(View v){
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.VISIBLE);
    }

    public void done(View view){
        Intent i = new Intent(this, Group.class);
        startActivity(i);
    }
}