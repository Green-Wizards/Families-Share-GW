package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

import classes.*;

public class NewActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public String nomegruppo, idgruppo;
    private EditText activityName, activityDescription, activityPlace, minVol, maxVol, minUC, maxUC;
    int minVolValue, maxVolValue, minUCValue, maxUCValue;
    private Spinner spin_tipo_att;
    private String date;
    private String orarioInizio = "";
    private String orarioFine = "";
    private String str = "";
    private ArrayList<String> timeslotsArrayInizio;
    private ArrayList<String> timeslotsArrayFine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if(ab!=null) { //aggiunto if per testare la creazione di nuove attività -yuri
            ab.setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.GONE);
        findViewById(R.id.linearLayout4).setVisibility(View.GONE);

        //acquisizione istanza del db firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //prendo una reference all'intent per recuperare il nome del gruppo che voglio far vedere
        Intent intent = getIntent();
        nomegruppo = intent.getStringExtra("group_name"); //sarebbe meglio usare un id univoco del gruppo -yuri
        idgruppo = intent.getStringExtra("group_id");

        activityName = (EditText) findViewById(R.id.et_activity_name);
        activityDescription = (EditText) findViewById(R.id.et_activity_description);
        activityPlace = (EditText) findViewById(R.id.et_activity_place);
        minVol = (EditText) findViewById(R.id.et_minimoVolontari);
        maxVol = (EditText) findViewById(R.id.et_maxVolontari);
        minUC = (EditText) findViewById(R.id.et_minUtentiCarico);
        maxUC = (EditText) findViewById(R.id.et_maxUtentiCarico);
        spin_tipo_att = (Spinner) findViewById(R.id.spinner_tipoAttivita);

        timeslotsArrayInizio = new ArrayList<>();
        timeslotsArrayFine = new ArrayList<>();
    }

    public void back(View view){
        Intent i = new Intent(this, Group.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }

    public void back2(View v){
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
        str = "";
        timeslotsArrayInizio.clear();
        timeslotsArrayFine.clear();
    }

    public void back1(View v){
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout4).setVisibility(View.VISIBLE);
    }

    public void go2(View v){
        findViewById(R.id.linearLayout).setVisibility(View.GONE);
        findViewById(R.id.linearLayout4).setVisibility(View.VISIBLE);
    }

    public void go3(View v){
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.VISIBLE);
    }

    public void go4(View v){
        findViewById(R.id.linearLayout4).setVisibility(View.GONE);
        findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
    }

    public void back4(View v){
        findViewById(R.id.linearLayout4).setVisibility(View.GONE);
        findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
    }

    public void done(View view){

        String nomeAttivita = activityName.getText().toString().trim();
        if(nomeAttivita.isEmpty()){
            activityName.setError("Nome dell'attività richiesta!");
            activityName.requestFocus();
            return;
        }
        String descrizAttivita = activityDescription.getText().toString().trim();
        if(descrizAttivita.isEmpty()){
            activityDescription.setError("Descrizione dell'attività richiesta!");
            activityDescription.requestFocus();
            return;
        }
        String zonaAttivita = activityPlace.getText().toString().trim();



        String minimoVol = minVol.getText().toString().trim();
        if (minimoVol.equals("")){
            minVolValue = 0;
        }else{
            minVolValue = Integer.parseInt((minVol.getText().toString().trim()).replaceAll("\\D+",""));
        }

        String massimoVol = maxVol.getText().toString().trim();
        if (massimoVol.equals("")){
            maxVolValue=-1; //-1 indica nessun limite
        }else{
            maxVolValue = Integer.parseInt((maxVol.getText().toString().trim()).replaceAll("\\D+",""));
        }

        String minimoUC = minUC.getText().toString().trim();
        if (minimoUC.equals("")){
            minUCValue = 0;
        }else{
            minUCValue = Integer.parseInt((minUC.getText().toString().trim()).replaceAll("\\D+",""));
        }

        String massimoUC = maxUC.getText().toString().trim();
        if (massimoUC.equals("")){
            maxUCValue=-1; //-1 indica nessun limite
        }else{
            maxUCValue = Integer.parseInt((maxUC.getText().toString().trim()).replaceAll("\\D+",""));
        }


        date = getDate();
        if(date.isEmpty()){
            EditText d = (EditText) findViewById(R.id.editDate);
            d.setError("Data dell'attività richiesta!");
            d.requestFocus();
            return;
        }

        for(String s : timeslotsArrayInizio)
            orarioInizio = orarioInizio + s + "\n";
        for(String s : timeslotsArrayFine)
            orarioFine = orarioFine + s + "\n";

        Activities nuovaAttivita = new Activities(idgruppo , nomeAttivita, descrizAttivita, zonaAttivita,
                     mAuth.getCurrentUser().getUid(), date, false, "Ongoing", minUCValue, maxUCValue,
                     minVolValue, maxVolValue);

        String uniqueID = UUID.randomUUID().toString();
        FirebaseDatabase.getInstance().getReference("Activities").child(uniqueID).setValue(nuovaAttivita);

        int indice = timeslotsArrayInizio.size();
        for(int index=0; index<indice; index++){
            Timeslots timeslot = new Timeslots(date, timeslotsArrayInizio.get(index), timeslotsArrayFine.get(index), uniqueID);
            String timeslot_id = UUID.randomUUID().toString();
            FirebaseDatabase.getInstance().getReference("Timeslots").child(timeslot_id).setValue(timeslot);
            Subscriptions sub = new Subscriptions(" ", " ", timeslot_id);
            FirebaseDatabase.getInstance().getReference("Subscriptions").child(uniqueID).setValue(sub);
        }

        Intent i = new Intent(NewActivity.this, Group.class);
        i.putExtra("group_name", nomegruppo );
        i.putExtra("group_id", idgruppo );
        startActivity(i);

    }

    private String getDate(){
        date = ((EditText) findViewById(R.id.editDate)).getText().toString().trim();
        findViewById(R.id.editDate).setBackgroundColor(0xFFFFFF);
        /*if(date.equals(""))
            findViewById(R.id.editDate).setBackgroundColor(0xFFAF1A1A);*/
        return date;
    }

    public void aggiungi(View v){
        String s1, s2, s4;
        s1 = ((EditText) findViewById(R.id.editStart)).getText().toString().trim();
        s2 = ((EditText) findViewById(R.id.editEnd)).getText().toString().trim();
        findViewById(R.id.editStart).setBackgroundColor(0xFFFFFF);
        findViewById(R.id.editEnd).setBackgroundColor(0xFFFFFF);
        if(s1.equals("") || s2.equals(""))
            if(s1.equals(""))
                findViewById(R.id.editStart).setBackgroundColor(0xFFAF1A1A);
            if(s2.equals(""))
                findViewById(R.id.editEnd).setBackgroundColor(0xFFAF1A1A);
        else{
            str = str + s1 + " - " + s2 + "\n";
            timeslotsArrayInizio.add(s1);
            timeslotsArrayFine.add(s2);
            s4 = "Timeslots finora aggiunte: \n" + str;
            ((TextView) findViewById(R.id.showTimeslots)).setText(s4);
        }
    }

}