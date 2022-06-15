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

public class InfoUtenti extends AppCompatActivity {

    private TextView tvNome, tvCognome, tvDataNascita, tvGenere, tvParentela, tvInfo;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String idUser;
    private String idgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_utenti);

        tvNome = findViewById(R.id.NomeUCI);
        tvCognome = findViewById(R.id.CognomeUCI);
        tvInfo = findViewById(R.id.InfoUCI);

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUser");
        idgroup = intent.getStringExtra("groupid");

        if(mAuth.getCurrentUser() != null) {
            getDependantData();
        }

    }

    private void getDependantData() {
        mDatabase.child("Dependents").child(idUser).child("given_name").get()
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
        mDatabase.child("Dependents").child(idUser).child("family_name").get()
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
        mDatabase.child("Dependents").child(idUser).child("birthdate").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvDataNascita.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Dependents").child(idUser).child("gender").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvGenere.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Dependents").child(idUser).child("gradoParentela").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvParentela.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
        mDatabase.child("Dependents").child(idUser).child("infoList").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(InfoUtenti.this, "Errore", Toast.LENGTH_LONG).show();
                                               } else {
                                                   tvInfo.setText(task.getResult().getValue().toString());
                                               }
                                           }
                                       }
                );
    }

    public void goBack(View v){
        Intent i = new Intent(this, GroupMembers.class);
        i.putExtra("idgroup", idgroup);
        startActivity(i);
    }
}
