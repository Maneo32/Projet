package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.example.projet.BaseDonnees;

public class MainActivity extends AppCompatActivity {

    private EvenementListSingleton singleton = EvenementListSingleton.getInstance();
    private TextView textView;
    private BaseDonnees db;

    public static final String REQUEST_RESULT="REQUEST_RESULT";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //Toast.makeText(this, "Rappel créé", Toast.LENGTH_LONG).show();
            String result = data.getStringExtra(REQUEST_RESULT);
            String[] str = result.split("!");
            Evenement evt = new Evenement(Integer.parseInt(str[2]), str[0], str[1], new Date());
            singleton.ajouterEvenement(evt);

            db = new BaseDonnees(this);
            SQLiteDatabase dbs = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nom", str[0]);
            values.put("description", str[1]);
            values.put("ordre", str[2]);
            values.put("date", evt.getDate().toString());

            long newRowId = dbs.insert("Rappel", null, values);


            // Rafraîchir le contenu du TextView avec les nouvelles données
            refreshTextView();
        }
    }

    private void refreshTextView() {
        StringBuilder builder = new StringBuilder();

        db = new BaseDonnees(getApplicationContext());
        ArrayList<Evenement> evt = db.recupererDonnees();
        evt.add(new Evenement(1, "n", "d", new Date()));
        for (Evenement evenement : evt) {
            builder.append(evenement.toString()).append("\n");
        }
        textView = findViewById(R.id.text);
        textView.setText(builder.toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Utiliser performOnCreate() au lieu de setContentView()
        performOnCreate();
    }

    private void performOnCreate() {
        setContentView(R.layout.activity_main);
        refreshTextView();
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