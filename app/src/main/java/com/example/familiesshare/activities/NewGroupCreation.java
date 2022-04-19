package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.familiesshare.R;
import com.google.android.material.navigation.NavigationView;

public class NewGroupCreation extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_creation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar); //mi fa crashare l'app,dovrebbe rimpiazzare actionbar
        //implemento la possibilità di selezionare un item del menu e aprire l'activity corrispondente
    }

    public void go2(View v){
        Intent i = new Intent(this, NewGroupCreation2.class);
        startActivity(i);
    }

    public void menu(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }


}