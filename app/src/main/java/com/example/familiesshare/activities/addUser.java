package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

import classes.Dependents;

public class addUser extends AppCompatActivity {
    private String nome;
    private String cognome;
    private String giorno;
    private String mese;
    private String anno;
    private String parentela;
    private String genere;
    private EditText info;
    private ArrayList<String> infos;
    private boolean spunta = false;
    private String infolist = "";
    private String birthday;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        findViewById(R.id.InfoList).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.InfoList)).setText("");
        infos = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickSend(View v){
        if(spunta){
            findViewById(R.id.GIORNO).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.MESE).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.ANNO).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.gradoParentela).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.genere).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.et_given_name).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.et_given_name).setBackgroundColor(0xFFFFFF);
            getData();
            if(giorno.equals("Giorno") || mese.equals("Mese") || anno.equals("Anno")
                || parentela.equals("Selezionare Parentela") || genere.equals("Selezionare genere")
                || nome.equals("") || cognome.equals("")) {
                if(giorno.equals("Giorno"))
                    findViewById(R.id.GIORNO).setBackgroundColor(0xFFAF1A1A);
                if(mese.equals("Mese"))
                    findViewById(R.id.MESE).setBackgroundColor(0xFFAF1A1A);
                if(anno.equals("Anno"))
                    findViewById(R.id.ANNO).setBackgroundColor(0xFFAF1A1A);
                if(parentela.equals("Selezionare Parentela"))
                    findViewById(R.id.gradoParentela).setBackgroundColor(0xFFAF1A1A);
                if(genere.equals("Selezionare genere"))
                    findViewById(R.id.genere).setBackgroundColor(0xFFAF1A1A);
                if(nome.equals(""))
                    findViewById(R.id.et_given_name).setBackgroundColor(0xFFAF1A1A);
                if(cognome.equals(""))
                    findViewById(R.id.et_given_name).setBackgroundColor(0xFFAF1A1A);
            }
            else{
                end(v);
            }
        }
        else
            findViewById(R.id.textView39).setBackgroundColor(0xFFAF1A1A);
    }

    private void end(View v){
        Dependents d = new Dependents(nome, cognome, genere, birthday, parentela, infolist, mAuth.getCurrentUser().getUid());
        String uniqueID = UUID.randomUUID().toString();
        FirebaseDatabase.getInstance().getReference("Dependents")
                .child(uniqueID)
                .setValue(d).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(addUser.this, "Creazione dell'utente a carico con successo!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(addUser.this, UtentiCarico.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(addUser.this, "Creazioine dell'utente a carico fallita!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void getData(){
        nome = ((EditText) findViewById(R.id.et_given_name)).getText().toString().trim();
        cognome = ((EditText) findViewById(R.id.et_family_name)).getText().toString().trim();
        giorno = ((Spinner) findViewById(R.id.GIORNO)).getSelectedItem().toString().trim();
        mese = ((Spinner) findViewById(R.id.MESE)).getSelectedItem().toString().trim();
        anno = ((Spinner) findViewById(R.id.ANNO)).getSelectedItem().toString().trim();
        parentela = ((Spinner) findViewById(R.id.gradoParentela)).getSelectedItem().toString().trim();
        genere = ((Spinner) findViewById(R.id.genere)).getSelectedItem().toString().trim();
        birthday = giorno+"/"+mese+"/"+anno;
    }

    public void onClickAdd(View v){
        info = (EditText) findViewById(R.id.editTextTextPersonName15);
        infos.add(info.getText().toString());
        String str = "";
        for(String s: infos){
            str = str + s + "\n";
        }
        ((TextView) findViewById(R.id.InfoList)).setText(str);
        findViewById(R.id.InfoList).setVisibility(View.VISIBLE);
        infolist = str;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        if (view.getId() == R.id.checkBox) {
            if (checked){
                spunta = true;
                findViewById(R.id.textView39).setBackgroundColor(0xFFFFFF);
            }
            else
                spunta = false;
        }
    }

    public void goBack(View v){
        Intent i = new Intent(this, UtentiCarico.class);
        startActivity(i);
    }
}