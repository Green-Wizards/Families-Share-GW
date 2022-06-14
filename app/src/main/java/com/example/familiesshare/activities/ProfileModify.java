package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileModify extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText newName, newSurname, newPhone, newCity, newVia, newCivic, newMail, newDescription;
    private Spinner newMetodoPreferito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


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


        newName = (EditText) findViewById(R.id.et_newName);
        newSurname = (EditText) findViewById(R.id.et_newSurname);
        newPhone = (EditText) findViewById(R.id.et_newPhone);
        newCity = (EditText) findViewById(R.id.et_newCity);
        newVia = (EditText) findViewById(R.id.et_newVia);
        newCivic = (EditText) findViewById(R.id.et_newCivic);
        newMail = (EditText) findViewById(R.id.et_newMail);
        newDescription = (EditText) findViewById(R.id.et_newDescription);
        newMetodoPreferito = (Spinner) findViewById(R.id.spinner_newMetodoPreferito);


    }
    ///TODO: manca il bottone di conferma delle modifiche
    public void update(View v){

        ///TODO: da testare!! nessun test ancora fatto
        String nuovoNome = newName.getText().toString().trim();
        String nuovoCognome = newSurname.getText().toString().trim();
        String nuovoTelefono = newPhone.getText().toString().trim();
        String nuovaArea = newCity.getText().toString().trim();
        String nuovaVia = newVia.getText().toString().trim();
        String nuovoCivico = newCivic.getText().toString().trim();
        String nuovaMail = newMail.getText().toString().trim();
        String nuovaDescrizione = newDescription.getText().toString().trim();

        String nuovoMetodoPreferito = newMetodoPreferito.getSelectedItem().toString();

        mDatabase.child("Profiles").child(mAuth.getUid()).child("given_name").setValue(nuovoNome);
        mDatabase.child("Profiles").child(mAuth.getUid()).child("family_name").setValue(nuovoCognome);
        mDatabase.child("Profiles").child(mAuth.getUid()).child("phone").setValue(nuovoTelefono);
        mDatabase.child("Profiles").child(mAuth.getUid()).child("area").setValue(nuovaArea);
        mDatabase.child("Profiles").child(mAuth.getUid()).child("address").setValue(nuovaVia+" "+nuovoCivico);
        mDatabase.child("Profiles").child(mAuth.getUid()).child("email").setValue(nuovaMail);
        mDatabase.child("Profiles").child(mAuth.getUid()).child("description").setValue(nuovaDescrizione);
        mDatabase.child("Profiles").child(mAuth.getUid()).child("contact_option").setValue(nuovoMetodoPreferito);

        Intent i = new Intent(this, Account.class);
        startActivity(i);

    }


}