package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
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
import java.util.Objects;

import com.example.projet.BaseDonnees;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Evenement> supp = new ArrayList<>();

    private TextView textView;
    private BaseDonnees db;

    public static final String REQUEST_RESULT = "REQUEST_RESULT";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (Objects.equals(data.getStringExtra(REQUEST_RESULT), "modif")) {
                recreate();
            } else {
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
        SQLiteDatabase dbs = db.getWritableDatabase();

        evenements.addAll(db.recupererDonnees());


        for (Evenement evenement : evenements) {
            LinearLayout cardContainer = new LinearLayout(this);
            cardContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            cardContainer.setOrientation(LinearLayout.HORIZONTAL);

            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(evenement.getId());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        supp.add(evenement);
                        Log.d("Tag", "Rappel ajouté : " + evenement.getId());
                    } else {
                        supp.remove(evenement);
                    }
                }
            });
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            CardView cv = new CardView(this);
            cv.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1 // Poids 1 pour occuper l'espace restant
            ));
            cv.setCardElevation(4);

            LinearLayout innerContainer = new LinearLayout(this);
            innerContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            innerContainer.setOrientation(LinearLayout.VERTICAL);

            TextView titleTextView = new TextView(this);
            titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            titleTextView.setTextSize(16);
            titleTextView.setTypeface(null, Typeface.BOLD);
            titleTextView.setPadding(8, 8, 8, 8);
            titleTextView.setText(evenement.getNom().toUpperCase());

            TextView descriptionTextView = new TextView(this);
            descriptionTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            descriptionTextView.setTextSize(14);
            descriptionTextView.setPadding(8, 0, 8, 8);
            descriptionTextView.setText(evenement.getDescription());


            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateMieux = sdf.format(evenement.getDate());
            TextView dateTextView = new TextView(this);
            dateTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            dateTextView.setTextSize(14);
            dateTextView.setPadding(8, 0, 8, 8);
            dateTextView.setText("Date: " + dateMieux + " " + evenement.getDate().getHours()+":"+evenement.getDate().getMinutes());

            if(evenement.getOrdre()==1){
                cv.setCardBackgroundColor(ColorStateList.valueOf(Color.WHITE));
            }
            else if(evenement.getOrdre()==2){
                cv.setCardBackgroundColor(ColorStateList.valueOf(Color.BLUE));
            }
            else if (evenement.getOrdre()==3){
                cv.setCardBackgroundColor(ColorStateList.valueOf(Color.RED));
            }

            innerContainer.addView(titleTextView);
            innerContainer.addView(descriptionTextView);
            innerContainer.addView(dateTextView);

            cv.addView(innerContainer);

            cardContainer.addView(checkBox);
            cardContainer.addView(cv);

            container.addView(cardContainer);
            container.addView(new TextView(this));
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


    public void valider(View view) {
        if (supp.size() != 0) {
            for (Evenement evt : supp) {
                SQLiteDatabase dbs = db.getWritableDatabase();
                db.val(dbs, evt.getId(), evt.getDate().getTime(), evt.getDescription(), evt.getNom(), evt.getOrdre());

                recreate();
            }

        }
    }



    public void accesModif(View View) {
        if (supp.size()==1) {
            Intent intent = new Intent(this, Modifier.class);
            intent.putExtra("nom", supp.get(0).getNom());
            intent.putExtra("desc", supp.get(0).getDescription());
            intent.putExtra("ordre", supp.get(0).getOrdre());
            intent.putExtra("date", supp.get(0).getDate());
            intent.putExtra("id", supp.get(0).getId());
            startActivityForResult(intent, 2);
        }
    }
}

