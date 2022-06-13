package com.example.familiesshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.familiesshare.R;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        findViewById(R.id.InfoList).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.InfoList)).setText("");
        infos = new ArrayList<>();
    }

    public void onClickSend(View v){
        if(spunta){
            findViewById(R.id.GIORNO).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.MESE).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.ANNO).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.gradoParentela).setBackgroundColor(0xFFFFFF);
            findViewById(R.id.genere).setBackgroundColor(0xFFFFFF);
            getData();
            if(giorno.equals("Giorno") || mese.equals("Mese") || anno.equals("Anno")
                || parentela.equals("Selezionare Parentela") || genere.equals("Selezionare genere")) {
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
            }
            else{
                // fare funzione per insert nel database
            }
        }
        else
            findViewById(R.id.textView39).setBackgroundColor(0xFFAF1A1A);
    }

    private void getData(){
        nome = ((EditText) findViewById(R.id.et_given_name)).getText().toString();
        cognome = ((EditText) findViewById(R.id.et_family_name)).getText().toString();
        giorno = ((Spinner) findViewById(R.id.GIORNO)).getSelectedItem().toString();
        mese = ((Spinner) findViewById(R.id.MESE)).getSelectedItem().toString();
        anno = ((Spinner) findViewById(R.id.ANNO)).getSelectedItem().toString();
        parentela = ((Spinner) findViewById(R.id.gradoParentela)).getSelectedItem().toString();
        genere = ((Spinner) findViewById(R.id.genere)).getSelectedItem().toString();
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