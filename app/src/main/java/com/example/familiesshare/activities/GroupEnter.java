package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;

public class GroupEnter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_enter);
        
    }

    public void onClickEnter(View v){

    }

    public void goBack(View v){
        Intent i = new Intent(this, CercaGruppi.class);
        i.putExtra("sender", "drawer");
        startActivity(i);
    }
}
