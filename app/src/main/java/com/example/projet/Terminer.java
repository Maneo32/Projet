package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Terminer extends AppCompatActivity {

    private EvenementListSingleton singleton = EvenementListSingleton.getInstance();
    private TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminer);
        StringBuilder builder = new StringBuilder();
        for (Evenement evenement : singleton.getListTermines()) {
            builder.append(evenement.toString()).append("\n");
        }

        textView = findViewById(R.id.textTer);
        textView.setText(builder.toString());
    }

    public void accesHomeTerminer(View View) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}