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
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    private ArrayList<Evenement> supp = new ArrayList<Evenement>();
    private TextView textView;
    private BaseDonnees db;

    public static final String REQUEST_RESULT = "REQUEST_RESULT";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //Toast.makeText(this, "Rappel créé", Toast.LENGTH_LONG).show();
            String result = data.getStringExtra(REQUEST_RESULT);
            String[] str = result.split("!");
            Log.d("TAG", str[3]);
            db = new BaseDonnees(this);
            SQLiteDatabase dbs = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nom", str[1]);
            values.put("description", str[0]);
            values.put("ordre", str[2]);
            values.put("date", str[3]);
            dbs.insert("Rappel", null, values);


            // Rafraîchir le contenu du TextView avec les nouvelles données
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

        LinearLayout container = findViewById(R.id.container);
        container.removeAllViews();

        StringBuilder builder = new StringBuilder();
        db = new BaseDonnees(getApplicationContext());
        ArrayList<Evenement> evenements = new ArrayList<Evenement>();
        evenements.addAll(db.recupererDonnees());

        for (Evenement evenement : evenements) {
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(evenement.getId());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        supp.add(evenement);
                        Log.d("Tag", "evt ajouté : " + evenement.getId());
                    } else {
                        supp.remove(evenement);
                    }
                }
            });
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(evenement.toString());

            itemLayout.addView(checkBox);
            itemLayout.addView(textView);

            container.addView(itemLayout);

            builder.append(evenement.toString()).append("\n");
        }
    }


    public void ajouter(View view) {
        Intent intent = new Intent(this, Ajouter.class);

        startActivityForResult(intent, 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void AccesSupprimer(MenuItem menu) {
        Intent intent = new Intent(this, Supprimer.class);
        startActivity(intent);
    }


    public void AccesTerminer(MenuItem item) {
        Intent intent = new Intent(this, Terminer.class);
        startActivity(intent);
    }

    public void supprimer(View view) {
        if (supp.size() != 0) {
            for (Evenement evt : supp) {
                SQLiteDatabase dbs = db.getWritableDatabase();
                db.supp(dbs, evt.getId(), evt.getDate().getTime(), evt.getDescription(), evt.getNom(), evt.getOrdre());

                recreate();
            }

        }
    }
}