package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class GruppoAttivita extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public String nomegruppo;
    public String idgruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_activity);

        Intent intent = getIntent();
        nomegruppo = intent.getStringExtra("group_name");
        idgruppo = intent.getStringExtra("group_id");
        findViewById(R.id.textView9).setVisibility(View.VISIBLE);

        showActivities();
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

    public void goInMezzo(View v){
        Intent i = new Intent(this.getBaseContext(), GruppoAttivita.class);
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

    public void new_activity (View view) {
        Intent i = new Intent(this, NewActivity.class);
        i.putExtra("group_name", nomegruppo );
        i.putExtra("group_id", idgruppo );
        startActivity(i);
    }
    private void showActivities(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Activities").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ShowActivitiesButton((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    private void ShowActivitiesButton(Map<String,Object> mappaAct) {
        LinearLayout constr;
        constr = (LinearLayout) findViewById(R.id.activityZone);
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);
        if(mappaAct.isEmpty()){
            findViewById(R.id.textView9).setVisibility(View.VISIBLE);
        }
        else{
            for (Map.Entry<String, Object> entry : mappaAct.entrySet()){
                Map actTrovato = (Map) entry.getValue();
                String idAct =  entry.getKey();
                //Aggiungi alla lista dei gruppi se il gruppo è dell'utente
                if (actTrovato.get("group_id").equals(idgruppo)){
                    findViewById(R.id.textView9).setVisibility(View.GONE);
                    Button btn = new Button(this);
                    String str = (String) actTrovato.get("activity_name");
                    btn.setText(str);
                    btn.setTag(counter);
                    btn.setOnClickListener(v -> {
                        Intent i = new Intent(this, UtentiCaricoInfo.class);
                        i.putExtra("activity_id", idAct);
                        i.putExtra("group_id", idgruppo);
                        startActivity(i);
                    });
                    constr.addView(btn);
                    bottoni.add(btn);
                    counter += 1;
                }
            }
        }
    }
    // collegare ad activityParticipation activity_id corretto (quello di firebase)
}
