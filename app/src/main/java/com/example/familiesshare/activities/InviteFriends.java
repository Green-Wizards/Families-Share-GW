package com.example.familiesshare.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familiesshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InviteFriends  extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_invite_friends);
    }
    public void goBack(View v){
        Intent i = new Intent(this, DrawerMenu.class);
        startActivity(i);
    }

    public void openEmail(View v){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(mAuth.getCurrentUser() != null) {
            String myEmail= mDatabase.child("Profiles").child(mAuth.getCurrentUser().getUid()).child("email").toString();
            String emailStr = ((EditText) findViewById(R.id.editTextMail)).getText().toString().trim();

            Intent email= new Intent(Intent.ACTION_VIEW);
            email.setType("message/rfc822")
                    .setData(Uri.parse("mailto:" + emailStr))
                    .putExtra(Intent.EXTRA_EMAIL, myEmail)
                    .putExtra(Intent.EXTRA_SUBJECT, "Invito Families_Share")
                    .putExtra(Intent.EXTRA_TEXT, "Invito Families_Share")
                    .setPackage("com.google.android.gm");
            startActivity(email);
        }
    }
}
