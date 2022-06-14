package com.example.familiesshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.familiesshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import classes.Activities;

public class NewActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public String nomegruppo, idgruppo;
    private EditText activityName, activityDescription, activityPlace;
    private CalendarView activityCalendar;

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
        activityCalendar = (CalendarView) findViewById(R.id.calendar_activity);

    }

    public void back(View view){
        Intent i = new Intent(this, Group.class);
        startActivity(i);
    }

    public void back2(View v){
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
    }
    public void back3(View v){
        findViewById(R.id.linearLayout3).setVisibility(View.GONE);
        findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
    }

    public void go2(View v){
        findViewById(R.id.linearLayout).setVisibility(View.GONE);
        findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
    }

    public void go3(View v){
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.VISIBLE);
    }

    public void done(View view){

        String nomeAttivita = activityName.getText().toString().trim();
        String descrizAttivita = activityDescription.getText().toString().trim();
        String zonaAttivita = activityPlace.getText().toString().trim();
/*
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final Date[] data = new Date[1];

        activityCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                //String giornoAttivita = String.valueOf(dayOfMonth);
                //String meseAttivita = String.valueOf(month);
                //String annoAttivita = String.valueOf(year);

                Calendar calendario = Calendar.getInstance();
                calendario.set(year, month, dayOfMonth);
                Date d = calendario.getTime();
                data[0] = d;

                Toast.makeText(NewActivity.this, sdf.format(data[0]), Toast.LENGTH_LONG).show();

            }
        });
*/


        Activities nuovaAttivita = new Activities(idgruppo , nomeAttivita, descrizAttivita, zonaAttivita,
                     mAuth.getCurrentUser().getUid(), "");//sdf.format(data[0]));

        String uniqueID = UUID.randomUUID().toString();
        FirebaseDatabase.getInstance().getReference("Activities")
                .child(uniqueID)
                .setValue(nuovaAttivita).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(NewActivity.this, "Creazione Attività avvenuta con successo!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(NewActivity.this, Group.class);
                            i.putExtra("group_name", nomegruppo );
                            i.putExtra("group_id", idgruppo );
                            startActivity(i);
                        } else {
                            //Toast.makeText(NewActivity.this, "Creazioine del gruppo fallita!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}