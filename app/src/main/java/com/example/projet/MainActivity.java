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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Evenement> list=new ArrayList<Evenement>();
    private ConstraintLayout ConLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list.add(new Evenement(1, "Tache 1", "description1", new Date()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i =0; i<list.size(); i++){
            TextView tv = new TextView(this);
            tv.setText(list.get(i).getNom().toString());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tv.setGravity(Gravity.CENTER);
            ConLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
            LinearLayout LinLayout = new LinearLayout(this);
            LinLayout.setId(View.generateViewId());
            LinLayout.setLayoutParams(new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LinLayout.setOrientation(LinearLayout.VERTICAL);
            ConLayout.addView(LinLayout);

            LinLayout.addView(tv);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void AccesTerminer(MenuItem item) {
        Intent intent = new Intent(this,Terminer.class);
        startActivity(intent);
    }
}