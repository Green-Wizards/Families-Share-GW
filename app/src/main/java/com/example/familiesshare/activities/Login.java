package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText email, password;
    private FirebaseAuth mAuth;
    private Button loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.et_login_email);
        password = (EditText) findViewById(R.id.et_login_pw);
        loginbtn = (Button) findViewById(R.id.btn_login);
        loginbtn.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            Intent goToMainMenu = new Intent(this, MainActivity.class);
            startActivity(goToMainMenu);
        }
    }

    public void Switch2(View v){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login_conferma(view);
                break;
        }
    }

    private void login_conferma(View view) {
        String indirizzo = email.getText().toString().trim();
        String pw = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(indirizzo, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Login avvenuto con successo!", Toast.LENGTH_LONG).show();
                            FirebaseUser profile = mAuth.getCurrentUser();
                            Intent goToMainMenu = new Intent(Login.this, MainActivity.class);
                            startActivity(goToMainMenu);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Login fallito!", Toast.LENGTH_LONG).show();
                            email.requestFocus();
                            password.requestFocus();
                        }
                    }
                });
    }

}