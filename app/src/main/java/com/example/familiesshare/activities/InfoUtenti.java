package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InfoUtenti extends AppCompatActivity {

    private TextView tvNome, tvCognome, tvDescrizione, tvArea, tvAddress, tvEmail, tvTelefono;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String idUser;
    private String idgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_utenti);

        tvNome = findViewById(R.id.nome);
        tvCognome = findViewById(R.id.cognome);
        tvArea = findViewById(R.id.area);
        tvAddress = findViewById(R.id.address);
        tvEmail = findViewById(R.id.email);
        tvTelefono = findViewById(R.id.telefono);
        tvDescrizione = findViewById(R.id.descrizione);

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUser");
        idgroup = intent.getStringExtra("groupid");

        if(mAuth.getCurrentUser() != null) {
            getUserData();
        }

    }

    private void getUserData() {
        mDatabase.child("Profiles").child(idUser).child("given_name").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvNome.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Profiles").child(idUser).child("family_name").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvCognome.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Profiles").child(idUser).child("area").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvArea.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Profiles").child(idUser).child("address").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvAddress.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Profiles").child(idUser).child("email").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvEmail.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Profiles").child(idUser).child("phone").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvTelefono.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Profiles").child(idUser).child("description").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvDescrizione.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
    }

    public void goBack(View v){
        Intent i = new Intent(this, GroupMembers.class);
        i.putExtra("group_id", idgroup);
        startActivity(i);
    }
}
