package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.familiesshare.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ImageView simpleImageView1=(ImageView) findViewById(R.id.imageView);
        simpleImageView1.setBackgroundColor(R.drawable.ic_profile);

        ImageView simpleImageView=(ImageView) findViewById(R.id.imageView3);
        simpleImageView.setBackgroundColor(Color.WHITE);
    }

    public void utentiCarico(View v){
        Intent i = new Intent(this, UtentiCarico.class);
        startActivity(i);
    }
}