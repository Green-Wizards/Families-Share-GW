package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import classes.Profile;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText given_name, family_name, phone,email,password, password_confirm;
    private Button signUp;
    private Switch visibile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        given_name = (EditText) findViewById(R.id.et_given_name);
        family_name = (EditText) findViewById(R.id.et_family_name);
        phone = (EditText) findViewById(R.id.et_phone);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        password_confirm = (EditText) findViewById(R.id.et_repeat_password);

        signUp = (Button) findViewById(R.id.button_sign_up);
        signUp.setOnClickListener(this);


    }

    public void Switch(View v){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_up:
                signUp_conferma(view);
                break;
        }
    }

    public void signUp_conferma(View v){

        String nome = given_name.getText().toString().trim();
        String cognome = family_name.getText().toString().trim();
        String ntelefono = phone.getText().toString().trim();
        String indirizzo = email.getText().toString().trim();
        String pw = password.getText().toString().trim();
        String pw_conferma = password_confirm.getText().toString().trim();

        if(nome.isEmpty()){
            given_name.setError("Nome richiesto!");
            given_name.requestFocus();
            return;
        }
        if(cognome.isEmpty()){
            family_name.setError("Cognome richiesto!");
            family_name.requestFocus();
            return;
        }
        if(indirizzo.isEmpty()){
            email.setError("Email richiesta!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(indirizzo).matches()){
            email.setError("La mail deve essere nel formato richiesto!");
            email.requestFocus();
            return;
        }
        if(pw.isEmpty()){
            password.setError("Password richiesta!");
            password.requestFocus();
            return;
        }
        if(pw.length()<6){
            password.setError("Password troppo corta! Deve avere più di 6 caratteri!");
            password.requestFocus();
            return;
        }
        if(!pw_conferma.equals(pw)){
            password.setError("Le due password non coincidono!");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(indirizzo, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Profile profilo = new Profile(nome, cognome, indirizzo, pw, ntelefono, true);

                            FirebaseDatabase.getInstance().getReference("Profiles")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(profilo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "Registrazione avvenuta con successo!", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(SignUp.this, DrawerMenu.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(SignUp.this, "Registrazione fallita!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



}