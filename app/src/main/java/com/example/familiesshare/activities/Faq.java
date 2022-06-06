package com.example.familiesshare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.familiesshare.R;

import java.util.ArrayList;

public class Faq extends AppCompatActivity {

    ArrayList<View> arrayV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        arrayV.add(findViewById(R.id.hiddenText1));
        arrayV.add(findViewById(R.id.hiddenText2));
        arrayV.add(findViewById(R.id.hiddenText3));
        arrayV.add(findViewById(R.id.hiddenText4));
        arrayV.add(findViewById(R.id.hiddenText5));
        arrayV.add(findViewById(R.id.hiddenText6));
        arrayV.add(findViewById(R.id.hiddenText7));
        arrayV.add(findViewById(R.id.hiddenText8));
        arrayV.add(findViewById(R.id.hiddenText9));
        arrayV.add(findViewById(R.id.hiddenText));
    }

    public void closeView(){
        for(View v: arrayV){
            v.setVisibility(View.GONE);
        }
    }

    public void open1 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton6);
        TextView text = (TextView) findViewById(R.id.hiddenText1);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open2 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton1);
        TextView text = (TextView) findViewById(R.id.hiddenText2);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open3 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton8);
        TextView text = (TextView) findViewById(R.id.hiddenText3);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open4 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton7);
        TextView text = (TextView) findViewById(R.id.hiddenText4);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open5 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton3);
        TextView text = (TextView) findViewById(R.id.hiddenText5);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open6 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton4);
        TextView text = (TextView) findViewById(R.id.hiddenText6);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open7 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton11);
        TextView text = (TextView) findViewById(R.id.hiddenText7);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open8 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton5);
        TextView text = (TextView) findViewById(R.id.hiddenText8);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open9 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton9);
        TextView text = (TextView) findViewById(R.id.hiddenText9);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
    public void open10 (View v){
        Button btn = (Button) findViewById(R.id.toggleButton10);
        TextView text = (TextView) findViewById(R.id.hiddenText);
        if (text.getVisibility() == View.VISIBLE) {
            text.setVisibility(View.GONE);
            btn.setText("<");
        } else{
            closeView();
            text.setVisibility(View.VISIBLE);
            btn.setText(">");
        }
    }
}