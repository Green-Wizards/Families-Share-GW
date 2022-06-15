package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class GruppoFamiliare extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String userid;
    private String[] nucleo;
    private int limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gruppo_familiare);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        showFamiliari();
    }

    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void newNF(View v){
        Intent i = new Intent(this, PopupNF.class);
        i.putExtra("userid", userid);
        startActivity(i);
    }

    private void showFamiliari(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("FamilyNucleus").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ShowFamilyButton((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    private void getDataFamily(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Profiles").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GetFamilyProfileButton((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
            mDatabase.child("Dependents").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GetFamilyDepButton((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    private void GetFamilyProfileButton(Map<String,Object> mappaUser) {
        LinearLayout constr;
        constr = (LinearLayout) findViewById(R.id.FamiliariZone);
        Integer counter = new Integer(0);
            for (Map.Entry<String, Object> entry : mappaUser.entrySet()){
                Map userTrovato = (Map) entry.getValue();
                String iduser =  entry.getKey();
                for(int in = 0; in<limit;in++){
                    if (iduser.equals(nucleo[in])){
                    TextView v = new TextView(this);
                    String str = (String) userTrovato.get("given_name") + " " + (String) userTrovato.get("family_name");
                    v.setText(str);
                    v.setTag(counter);
                    constr.addView(v);
                    counter += 1;
                }
            }}
        }

    private void GetFamilyDepButton(Map<String,Object> mappaDep) {
        LinearLayout constr;
        constr = (LinearLayout) findViewById(R.id.FamiliariZone);
        Integer counter = new Integer(0);
        for (Map.Entry<String, Object> entry : mappaDep.entrySet()){
            Map depTrovato = (Map) entry.getValue();
            String iddep =  entry.getKey();
            for(int in = 0; in<limit;in++){
                if (iddep.equals(nucleo[in])){
                    TextView v = new TextView(this);
                    String str = (String) depTrovato.get("given_name") + " " + (String) depTrovato.get("family_name");
                    v.setText(str);
                    v.setTag(counter);
                    constr.addView(v);
                    counter += 1;
                }
            }}
    }

    private void ShowFamilyButton(Map<String,Object> mappaFamily) {
            for (Map.Entry<String, Object> entry : mappaFamily.entrySet()){
                Map familyTrovato = (Map) entry.getValue();
                if (familyTrovato.get("users_id").equals(mAuth.getCurrentUser().getUid())){
                    nucleo = ((String)familyTrovato.get("user_list")).split("\n");
                    limit = nucleo.length;
                }
            }
        getDataFamily();
    }
}
