package com.example.familiesshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CercaGruppi extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText search;
    private String sender;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_gruppi);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        search = (EditText) findViewById(R.id.et_search);

        Intent intent = getIntent();
        sender = intent.getStringExtra("sender");
        input = intent.getStringExtra("input");
        showGroups();
    }


    private void showGroups(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Groups").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ShowGroupsButton((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    private void ShowGroupsButton(Map<String,Object> mappaGroup) {
        LinearLayout constr;
        constr = (LinearLayout) findViewById(R.id.groupZoneSearch);
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);
            for (Map.Entry<String, Object> entry : mappaGroup.entrySet()){
                Map groupTrovato = (Map) entry.getValue();
                String idGroup =  entry.getKey();

                if ((Boolean) groupTrovato.get("visibility") 
                    && (((String) groupTrovato.get("name")).toLowerCase().contains(input.toLowerCase()))){
                    Button btn = new Button(this);
                    String str = (String) groupTrovato.get("name");
                    btn.setText(str);
                    btn.setTag(counter);
                    btn.setOnClickListener(v -> {
                        Intent i = new Intent(this, GroupEnter.class);
                        i.putExtra("groupid", idGroup);
                        startActivity(i);
                    });
                    constr.addView(btn);
                    bottoni.add(btn);
                    counter += 1;
                }
            }
        }

    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void onSearch(View v){

        input = search.getText().toString().trim();

        Intent i = new Intent(this, CercaGruppi.class);
        i.putExtra("sender", "search");
        i.putExtra("input", input);
        startActivity(i);
    }
}
