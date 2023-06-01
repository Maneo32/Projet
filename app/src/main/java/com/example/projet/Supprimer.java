package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Supprimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);
        Toolbar t = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarSupprimer);
        setSupportActionBar(t);
    }
    
    public void accesHomeSupprimer(View View) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}