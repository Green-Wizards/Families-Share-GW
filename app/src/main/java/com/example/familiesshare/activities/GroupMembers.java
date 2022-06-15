package com.example.familiesshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class GroupMembers extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String nomegruppo;
    private String idgruppo;
    private Map<String, Object> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        Intent intent = getIntent();
        nomegruppo = intent.getStringExtra("group_name");
        idgruppo = intent.getStringExtra("group_id");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        getUsers();
        showMembers();
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
        Intent i = new Intent(this, GruppoAttivita.class);
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

    private void getUsers(){
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Profiles").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            users = ((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
        }
    }

    private void showMembers(){
        if(mAuth.getCurrentUser() != null) {
            mDatabase.child("Members").addListenerForSingleValueEvent(
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
        constr = (LinearLayout) findViewById(R.id.memberZone);
        ArrayList<Button> bottoni = new ArrayList<>();
        Integer counter = new Integer(0);
            //itera tutti i gruppi
            for (Map.Entry<String, Object> entry : mappaDep.entrySet()){
                //Get user map
                Map member = (Map) entry.getValue();
                String idMember = entry.getKey();
                //Aggiungi alla lista dei gruppi se il gruppo è dell'utente
                if (member.get("group_id").equals(idgruppo)){
                    String idUser = (String) member.get("user_id");
                    for(Map.Entry<String, Object> user : users.entrySet()){
                        String str = user.getKey();
                        Map userMap = (Map) user.getValue();
                        if(str.equals(member.get("user_id"))){
                            Button btn = new Button(this);
                            String str2 = (String) userMap.get("given_name") + " " + (String) userMap.get("family_name");
                            btn.setText(str2);
                            btn.setTag(counter);
                            btn.setOnClickListener(v -> {
                                Intent i = new Intent(this, InfoUtenti.class);
                                i.putExtra("idUser", idUser);
                                i.putExtra("groupid", idgruppo);
                                startActivity(i);
                            });
                            constr.addView(btn);
                            bottoni.add(btn);
                            counter += 1;
                            }
                        }
                    }
                }
    }

    public void onClickNewMember(View v){
        Intent i = new Intent(this, InviteMember.class);
        i.putExtra("group_name", nomegruppo);
        i.putExtra("group_id", idgruppo);
        startActivity(i);
    }
}
