package com.example.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Ajouter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        private ArrayList<Integer> number = new ArrayList<Integer>();

    private int hour = new Date().getHours();
    private int minute = new Date().getMinutes();
        private long selectedDateInMillis = new Date().getTime();
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
            CalendarView calendarView = findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    // Ici, vous pouvez récupérer la date sélectionnée et la convertir en long
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    selectedDateInMillis = calendar.getTimeInMillis();

                    // Utilisez la valeur de selectedDateInMillis comme vous le souhaitez
                }
            });
        }

    public void showTimePickerDialog(View view) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour = hourOfDay; // Mettre à jour l'heure
                        Ajouter.this.minute = minute; // Mettre à jour les minutes
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    public void onClickClose(View view) {
        Intent returnIntent = new Intent();
        EditText nom = (EditText) findViewById(R.id.nom);
        EditText description = (EditText) findViewById(R.id.Description);
        Spinner ordre = (Spinner) findViewById(R.id.spinner);
        Date date = new Date(selectedDateInMillis);
        date.setHours(hour); // Utiliser l'heure déjà stockée
        date.setMinutes(minute); // Utiliser les minutes déjà stockées
        selectedDateInMillis = date.getTime();
        String l = "";
        l = l + (nom.getText().toString()) + "!";
        l = l + (description.getText()) + "!";
        l = l + (ordre.getSelectedItem().toString()) + "!";
        l = l + (selectedDateInMillis);

        returnIntent.putExtra(MainActivity.REQUEST_RESULT, l);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void accesHomeAjouter(View View) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}