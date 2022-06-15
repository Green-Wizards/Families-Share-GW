package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

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
            Task<DataSnapshot> nome = mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("given_name")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(UtentiCarico.this, "NON SO CHI SEI", Toast.LENGTH_LONG).show();
                    } else {
                        String nome = (String) task.getResult().getValue();
                        tv_nomeAccount.setText(nome);
                    }
                }
            });

        }
        showDependents();
    }

    private void showDependents(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Dependents").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ShowDependentsButton((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    private void ShowDependentsButton(Map<String,Object> mappaDep) {
        LinearLayout constr;
        constr = (LinearLayout) findViewById(R.id.depZone);
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);
        if(mappaDep.isEmpty()){
            findViewById(R.id.textView37).setVisibility(View.VISIBLE);
        }
        else{
            findViewById(R.id.textView37).setVisibility(View.GONE);
            //itera tutti i gruppi
            for (Map.Entry<String, Object> entry : mappaDep.entrySet()){
                //Get user map
                Map dependentTrovato = (Map) entry.getValue();
                String idDependent =  entry.getKey();
                //Aggiungi alla lista dei gruppi se il gruppo è dell'utente
                if (dependentTrovato.get("tutor_id").equals(mAuth.getCurrentUser().getUid())){
                    Button btn = new Button(this);
                    String str = (String) dependentTrovato.get("given_name") + " " + (String) dependentTrovato.get("family_name")
                            + " - " + (String) dependentTrovato.get("gradoParentela");
                    btn.setText(str);
                    btn.setTag(counter);
                btn.setOnClickListener(v -> {
                    Intent i = new Intent(this, UtentiCaricoInfo.class);
                    i.putExtra("idDependent", idDependent);
                    i.putExtra("tutor_id", (String) dependentTrovato.get("tutor_id"));
                    startActivity(i);
                });
                    constr.addView(btn);
                    bottoni.add(btn);
                    counter += 1;
                }
            }
        }
    }

    public void account(View v){
        Intent i = new Intent(this, Account.class);
        startActivity(i);
    }

    public void gruppoFamiliare(View v){
        Intent i = new Intent(this, GruppoFamiliare.class);
        String user = mAuth.getCurrentUser().getUid();
        i.putExtra("userid", user);
        startActivity(i);
    }

    public void aggiungiUtenteCarico(View v){
        Intent i = new Intent(this, addUser.class);
        startActivity(i);
    }

}