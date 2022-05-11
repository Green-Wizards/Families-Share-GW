package com.example.familiesshare.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;

public class GuidaAvviamento extends  AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_guida_avviamento);
    }
    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void link(View v){
        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://families-share.eu/fam-uploads/2020/06/Families_Share_Toolkit-.pdf")));

    }

}
