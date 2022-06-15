package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import classes.Groups;
import classes.Members;

public class NewGroupCreation extends AppCompatActivity{

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText groupName, groupDescription, groupArea, groupContactInfo;
    private Switch groupVisibility;
    private Spinner groupContactType;



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

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        groupName = (EditText) findViewById(R.id.et_group_name);
        groupDescription = (EditText) findViewById(R.id.et_group_description);
        groupArea = (EditText) findViewById(R.id.et_group_area);
        groupContactInfo = (EditText) findViewById(R.id.et_group_contactinfo);
        groupVisibility = (Switch) findViewById(R.id.switch_group_visibility);
        groupContactType = (Spinner) findViewById(R.id.spinner_group_contactinfo);


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

    public void back1(View v){
        findViewById(R.id.view2).setVisibility(View.GONE);
        findViewById(R.id.view1).setVisibility(View.VISIBLE);
    }

    public void back2(View v){
        findViewById(R.id.view3).setVisibility(View.GONE);
        findViewById(R.id.view2).setVisibility(View.VISIBLE);
    }
    public void back4(View v){
        findViewById(R.id.view4).setVisibility(View.GONE);
        findViewById(R.id.view3).setVisibility(View.VISIBLE);
    }

    public void end(View v){

        String nomeGruppo = groupName.getText().toString().trim();
        String descrizioneGruppo = groupDescription.getText().toString().trim();
        String areaGruppo = groupArea.getText().toString().trim();
        String infoContattoGruppo = groupContactInfo.getText().toString().trim();
        Boolean visibilitaGruppo = groupVisibility.isChecked();
        String tipoContattoGruppo = groupContactType.getSelectedItem().toString();

        if(nomeGruppo.isEmpty()){
            groupName.setError("Nome del gruppo richiesto!");
            groupName.requestFocus();
            return;
        }
        if(descrizioneGruppo.isEmpty()){
            groupDescription.setError("Descrizione del gruppo richiesta!");
            groupDescription.requestFocus();
            return;
        }
        if(areaGruppo.isEmpty()){
            groupArea.setError("Area del gruppo richiesta!");
            groupArea.requestFocus();
            return;
        }
        if(infoContattoGruppo.isEmpty()){
            groupContactInfo.setError("Informazioni del contatto richieste!");
            groupContactInfo.requestFocus();
            return;
        }


        Groups nuovoGruppo = new Groups(nomeGruppo, descrizioneGruppo, areaGruppo, mAuth.getCurrentUser().getUid(), tipoContattoGruppo, infoContattoGruppo, visibilitaGruppo);
        String uniqueID = UUID.randomUUID().toString();
        FirebaseDatabase.getInstance().getReference("Groups")
                .child(uniqueID)
                .setValue(nuovoGruppo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(NewGroupCreation.this, "Creazione gruppo avvenuta con successo!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(NewGroupCreation.this, "Creazione del gruppo fallita!", Toast.LENGTH_LONG).show();
                }
            }
        });
        Members membro = new Members(mAuth.getCurrentUser().getUid(), uniqueID, true, false);
        FirebaseDatabase.getInstance().getReference("Members")
                .child(uniqueID)
                .setValue(membro).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(NewGroupCreation.this, "Aggiunta membro avvenuta con successo!", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(NewGroupCreation.this, "Creazioine del membro fallita!", Toast.LENGTH_LONG).show();
                        }
                    }
                });


        Intent i = new Intent(NewGroupCreation.this, DrawerMenu.class);
        startActivity(i);
    }
}