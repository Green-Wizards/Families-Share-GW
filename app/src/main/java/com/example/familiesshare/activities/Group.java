package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Group extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public String nomegruppo;
    public TextView txtnomegruppo, txtdescgruppo, txtlocationgruppo, txtinfocontatto;
    public String idgruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent intent = getIntent();
        nomegruppo = intent.getStringExtra("group_name");
        idgruppo = intent.getStringExtra("group_id");

        txtnomegruppo = (TextView) findViewById(R.id.nomeGruppo);
        txtdescgruppo = (TextView) findViewById(R.id.descGruppo);
        txtlocationgruppo = (TextView) findViewById(R.id.locationGruppo);
        txtinfocontatto = (TextView) findViewById(R.id.infoContatto);

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            txtnomegruppo.setText(nomegruppo);
            retrieveDescrizioneGruppo();
            retrieveLocation();
            retrieveInfoContatto();
        }


    }

    public void retrieveDescrizioneGruppo(){
        mDatabase.child("Groups").child(idgruppo).child("description").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(Group.this, "Operazione fallita", Toast.LENGTH_LONG).show();
                                               } else {
                                                   String descr = task.getResult().getValue().toString();
                                                   txtdescgruppo.setText(descr);
                                               }
                                           }
                                       }
                );
    }

    public void retrieveLocation(){
        mDatabase.child("Groups").child(idgruppo).child("location").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(Group.this, "Operazione fallita", Toast.LENGTH_LONG).show();
                                               } else {
                                                   String loc = task.getResult().getValue().toString();
                                                   txtlocationgruppo.setText(loc);
                                               }
                                           }
                                       }
                );
    }


    public void retrieveInfoContatto(){
        mDatabase.child("Groups").child(idgruppo).child("contact_info").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DataSnapshot> task) {
                                               if (!task.isSuccessful()) {
                                                   Toast.makeText(Group.this, "Operazione fallita", Toast.LENGTH_LONG).show();
                                               } else {
                                                   String ci = task.getResult().getValue().toString();
                                                   txtinfocontatto.setText(ci);
                                               }
                                           }
                                       }
                );
    }

    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void goInfo(View v){
        Intent i = new Intent(this, Group.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }

    public void goActivity(View v){
        Intent i = new Intent(this, GruppoAttivita.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }

    public void goMembers(View v){
        Intent i = new Intent(this, GroupMembers.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }
}