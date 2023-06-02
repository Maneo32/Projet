package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Ajouter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        ArrayList<Integer> number = new ArrayList<Integer>();
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        number.add(1);
        number.add(2);
        number.add(3);
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,number);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(aa);
        }

    public void onClickClose(View view) {
        Intent returnIntent = new Intent();
        EditText nom = (EditText) findViewById(R.id.nom);
        EditText description = (EditText) findViewById(R.id.Description);
        Spinner ordre = (Spinner) findViewById(R.id.spinner);
        String l = "";
        l=l+(nom.getText().toString())+"!";
        l=l+(description.getText().toString())+"!";
        l=l+(ordre.getSelectedItem().toString());
        returnIntent.putExtra(MainActivity.REQUEST_RESULT,l);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}