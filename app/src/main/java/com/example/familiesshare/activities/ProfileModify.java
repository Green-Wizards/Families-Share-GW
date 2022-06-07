package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.familiesshare.R;

public class ProfileModify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ImageView simpleImageView1=(ImageView) findViewById(R.id.imageView2);
        simpleImageView1.setBackgroundColor(R.drawable.ic_profile);

        ImageView simpleImageView2=(ImageView) findViewById(R.id.imageView4);
        simpleImageView1.setBackgroundColor(Color.WHITE);

        ImageView simpleImageView3=(ImageView) findViewById(R.id.imageView12);
        simpleImageView1.setBackgroundColor(Color.WHITE);

        ImageView simpleImageView4=(ImageView) findViewById(R.id.imageView13);
        simpleImageView1.setBackgroundColor(Color.WHITE);

        ImageView simpleImageView5=(ImageView) findViewById(R.id.imageView14);
        simpleImageView1.setBackgroundColor(Color.WHITE);

    }
}