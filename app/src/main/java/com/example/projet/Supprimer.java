package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Supprimer extends AppCompatActivity {

    private EvenementListSingleton singleton = EvenementListSingleton.getInstance();
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);
        StringBuilder builder = new StringBuilder();
        for (Evenement evenement : singleton.getListSupprimes()) {
            builder.append(evenement.toString()).append("\n");
        }

        textView = findViewById(R.id.textSupp);
        textView.setText(builder.toString());
    }

    public void accesHomeSupprimer(View View) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}