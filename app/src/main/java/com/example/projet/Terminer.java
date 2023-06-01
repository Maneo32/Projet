package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Terminer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminer);
        Toolbar t = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarTerminer);
        setSupportActionBar(t);
    }

    public void accesHomeTerminer(View View) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}