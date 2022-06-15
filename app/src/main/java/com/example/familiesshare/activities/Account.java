package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Account extends AppCompatActivity implements View.OnClickListener {

    private TextView txtUserName, txtTelefono, txtIndirizzo, txtContatto, txtDescrizione;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    private FloatingActionButton editBtn;

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


        editBtn = findViewById(R.id.btn_edit_user);

        //aggiorna il nome dell'utente
        txtUserName = findViewById(R.id.tv_accountName);
        txtTelefono = findViewById(R.id.tv_telefono);
        txtIndirizzo = findViewById(R.id.tv_indirizzo);
        txtContatto = findViewById(R.id.tv_contatto);
        txtDescrizione = findViewById(R.id.tv_descrizione);

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            retrieveNome();
            retrieveTelefono();
            retrieveMail();
            retrieveDescrizione();
            retrieveAddress();
        }

    }


    public void retrieveNome(){
        mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("given_name").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Account.this, "NON SO CHI SEI", Toast.LENGTH_LONG).show();
                        } else {
                            String nome = task.getResult().getValue().toString();
                            txtUserName.setText(nome);
                        }
                    }
                }
        );
    }
    public void retrieveTelefono(){
        mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("phone").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(Account.this, "NON SO CHI SEI", Toast.LENGTH_LONG).show();
                                               } else {
                                                   String tel = task.getResult().getValue().toString();
                                                   txtTelefono.setText(tel);
                                               }
                                           }
                                       }
                );
    }
    public void retrieveMail(){
        mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("email").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(Account.this, "NON SO CHI SEI", Toast.LENGTH_LONG).show();
                                               } else {
                                                   String mail = task.getResult().getValue().toString();
                                                   txtContatto.setText(mail);
                                               }
                                           }
                                       }
                );
    }

    public void retrieveAddress(){
        mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("address").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(Account.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   String address = task.getResult().getValue().toString();

                                                   mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("area").get()
                                                           .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                                                      @Override
                                                                                      public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                                                          if (!task.isSuccessful()) {
                                                                                              Toast.makeText(Account.this, "Errore", Toast.LENGTH_LONG).show();
                                                                                          } else {
                                                                                              String area = task.getResult().getValue().toString();

                                                                                              txtIndirizzo.setText(new String(area + " - " + address));
                                                                                          }
                                                                                      }
                                                                                  }
                                                           );




                                                   txtDescrizione.setText(address);
                                               }
                                           }
                                       }
                );
    }

    public void retrieveDescrizione(){
        mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("description").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(Account.this, "NON SO CHI SEI", Toast.LENGTH_LONG).show();
                                               } else {
                                                   String descr = task.getResult().getValue().toString();
                                                   if(descr.equals(""))
                                                       txtDescrizione.setText("Descrizione non disponibile.");
                                                   else
                                                       txtDescrizione.setText(descr);
                                               }
                                           }
                }
        );
    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, ProfileModify.class);
        startActivity(i);
    }

    private void edit_user(View view) {
        Intent i = new Intent(this, ProfileModify.class);
        startActivity(i);
    }

    public void utentiCarico(View v){
        Intent i = new Intent(this, UtentiCarico.class);
        startActivity(i);
    }
}