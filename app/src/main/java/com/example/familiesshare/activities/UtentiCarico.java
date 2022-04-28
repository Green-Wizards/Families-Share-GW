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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UtentiCarico extends AppCompatActivity {

    private TextView tv_nomeAccount;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utenti_carico);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ImageView simpleImageView1=(ImageView) findViewById(R.id.imageView5);
        simpleImageView1.setBackgroundColor(R.drawable.ic_profile);

        ImageView simpleImageView=(ImageView) findViewById(R.id.imageView7);
        simpleImageView.setBackgroundColor(Color.WHITE);

        //TEST read dal db firebase
        tv_nomeAccount = findViewById(R.id.tv_accountNameUC);
        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            Task<DataSnapshot> nome = mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("given_name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(UtentiCarico.this, "NON SO CHI SEI", Toast.LENGTH_LONG).show();
                    } else {
                        String nome = task.getResult().getValue().toString();
                        tv_nomeAccount.setText(nome);
                    }
                }
            });

        }

    }

    public void account(View v){
        Intent i = new Intent(this, AccountActivity.class);
        startActivity(i);
    }

    /*
    public void aggiungiUtenteCarico(View v){
        Intent i = new Intent(this, addUser.class);
        startActivity(i);
    }
    */
}