package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Supprimer extends AppCompatActivity {

    private TextView textView;
    private ArrayList<Evenement> supp = new ArrayList<>();
    BaseDonnees db = new BaseDonnees(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);

        LinearLayout container = findViewById(R.id.containerSupp);
        container.removeAllViews();

        StringBuilder builder = new StringBuilder();
        db = new BaseDonnees(getApplicationContext());
        ArrayList<Evenement> evenements = new ArrayList<Evenement>();
        evenements.addAll(db.recupererSupp());

        for (Evenement evenement : evenements) {
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

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

            if (evenement.getOrdre() == 1) {
                cv.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FDF5E6")));
            } else if (evenement.getOrdre() == 2) {
                cv.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#AED6F1")));
            } else if (evenement.getOrdre() == 3) {
                cv.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#9A7ED0")));
            }
            innerContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBox.setChecked(!checkBox.isChecked());
                }
            });

            dateTextView.setTextColor(ColorStateList.valueOf(Color.BLACK));
            descriptionTextView.setTextColor(ColorStateList.valueOf(Color.BLACK));
            titleTextView.setTextColor(ColorStateList.valueOf(Color.BLACK));
            innerContainer.addView(titleTextView);
            innerContainer.addView(descriptionTextView);
            innerContainer.addView(dateTextView);

            cv.addView(innerContainer);

            itemLayout.addView(checkBox);
            itemLayout.addView(cv);

            container.addView(itemLayout);
            container.addView(new TextView(this));

            builder.append(evenement.toString()).append("\n");
        }
    }


    public void accesHomeSupprimer(View View) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }














    public void supp(View view){
        Log.d("TAG", "supp");
        if (supp.size() != 0) {
            for (Evenement evt : supp) {
                SQLiteDatabase dbs = db.getWritableDatabase();
                db.delSupp(dbs, evt.getId());
                recreate();
            }

        }
    }
    public void recup(View view){
        if (supp.size() != 0) {
            for (Evenement evt : supp) {
                SQLiteDatabase dbs = db.getWritableDatabase();
                db.recuperer(dbs, evt.getId(), evt.getDate().getTime(), evt.getDescription(), evt.getNom(), evt.getOrdre());
                recreate();
            }

        }
    }


}