package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Supprimer extends AppCompatActivity {

    private TextView textView;
    private ArrayList<Evenement> supp = new ArrayList<>();
    BaseDonnees db = new BaseDonnees(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);
        StringBuilder builder = new StringBuilder();
        LinearLayout container = findViewById(R.id.containerSupp);
        for (Evenement evenement : db.recupererSupp()) {
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