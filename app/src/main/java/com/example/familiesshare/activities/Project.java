package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.familiesshare.R;

public class Project extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.textView1).setVisibility(View.GONE);
        findViewById(R.id.textView12).setVisibility(View.GONE);
        findViewById(R.id.textView15).setVisibility(View.GONE);
        findViewById(R.id.textView17).setVisibility(View.GONE);

    }

    public void site (View v){
        Uri uri = Uri.parse("http://www.families-share.eu"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void open1 (View v){
        TextView text = (TextView) findViewById(R.id.textView1);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
        } else{
            text.setVisibility(View.VISIBLE);
        }
    }
    public void open2 (View v){
        TextView text = (TextView) findViewById(R.id.textView12);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
        } else{
            text.setVisibility(View.VISIBLE);
        }
    }public void open3 (View v){
        TextView text = (TextView) findViewById(R.id.textView15);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
        } else{
            text.setVisibility(View.VISIBLE);
        }
    }public void open4 (View v){
        TextView text = (TextView) findViewById(R.id.textView17);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
        } else{
            text.setVisibility(View.VISIBLE);
        }

    }

}