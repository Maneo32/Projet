package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;

public class Terminer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminer);
        Toolbar t = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarTerminer);
        setSupportActionBar(t);
    }

}