package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EvenementListSingleton singleton = EvenementListSingleton.getInstance();
    private TextView textView;


    public static final String REQUEST_RESULT="REQUEST_RESULT";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, (String) (data.getStringExtra(REQUEST_RESULT)), Toast.LENGTH_LONG).show();
            String result = (String) (data.getStringExtra(REQUEST_RESULT));
            String[] str = result.split("!");
            Evenement evt = new Evenement(Integer.parseInt(str[2]), str[0], str[1], new Date());
            singleton.ajouterEvenement(evt);

            // Appeler onCreate() après la fin de onActivityResult()
            performOnCreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Utiliser performOnCreate() au lieu de setContentView()
        performOnCreate();
    }

    private void performOnCreate() {
        setContentView(R.layout.activity_main);

        StringBuilder builder = new StringBuilder();

        for (Evenement evenement : singleton.getList()) {
            builder.append(evenement.toString()).append("\n");
        }

        textView = findViewById(R.id.text);
        textView.setText(builder.toString());
    }






    public void ajouter(View view){
        Intent intent = new Intent(this, Ajouter.class);

        startActivityForResult(intent, 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void AccesSupprimer(MenuItem menu){
        Intent intent = new Intent(this, Supprimer.class);
        startActivity(intent);
    }


    public void AccesTerminer(MenuItem item) {
        Intent intent = new Intent(this,Terminer.class);
        startActivity(intent);
    }
}