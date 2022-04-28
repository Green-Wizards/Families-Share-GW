package com.example.familiesshare.activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawermenu);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //mi fa crashare l'app,dovrebbe rimpiazzare actionbar
        //implemento la possibilità di selezionare un item del menu e aprire l'activity corrispondente

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //provo a vedere se riesco a mettere home page come sfondo default del menu NON FUNZIA
        //NON SI VEDE LA BARRA SOPRA
        /*
        if (savedInstanceState==null){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        navigationView.setCheckedItem(R.id.nav_homepage);}*/
    }

    public void create(View v){
        Intent i = new Intent(this, NewGroupCreation.class);
        startActivity(i);
    }

    //per aprire le activity dal menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        switch (id){
            case R.id.nav_homepage://prendo gli id da drawer_menu
                Intent intent = new Intent(this, DrawerMenu.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                Intent profilo = new Intent(this, AccountActivity.class);
                startActivity(profilo);
                break;
            //AGGIUNGERE CALENDARIO
            case R.id.group_create:
                Intent creagruppo = new Intent(this, NewGroupCreation.class);
                startActivity(creagruppo);
                break;
            case R.id.nav_exit:
                FirebaseAuth.getInstance().signOut();
                Intent esci = new Intent(this, MainActivity.class);
                startActivity(esci);
                break;
        }
        //prova
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}

