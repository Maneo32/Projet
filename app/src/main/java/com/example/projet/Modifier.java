package com.example.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Modifier extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ArrayList<Integer> number = new ArrayList<Integer>();
    private long selectedDateInMillis = new Date().getTime();
    private int hour = new Date().getHours();
    private int minute = new Date().getMinutes();
    private BaseDonnees db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        EditText editText = (EditText) findViewById(R.id.DescriptionModif);
        Spinner spin = (Spinner) findViewById(R.id.spinnerModif);
        spin.setOnItemSelectedListener(this);
        number.add(1);
        number.add(2);
        number.add(3);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,number);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        editText.setText((CharSequence) getIntent().getSerializableExtra("desc"));
        editText = findViewById(R.id.nomModif);
        editText.setText((CharSequence) getIntent().getSerializableExtra("nom"));
        spin.setSelection((int)getIntent().getSerializableExtra("ordre")-1);
        CalendarView cv = findViewById(R.id.calendarViewModif);
        Date date = (Date) getIntent().getSerializableExtra("date");
        cv.setDate(date.getTime());
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Ici, vous pouvez récupérer la date sélectionnée et la convertir en long
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selectedDateInMillis = calendar.getTimeInMillis();
                showTimePickerDialog();

                // Utilisez la valeur de selectedDateInMillis comme vous le souhaitez
            }
        });
    }


    public void accesHomeModif(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void onClickClose(View view) {
        Intent returnIntent = new Intent();
        db = new BaseDonnees(this);
        EditText nom = (EditText) findViewById(R.id.nomModif);
        EditText description = (EditText) findViewById(R.id.DescriptionModif);
        Spinner ordre = (Spinner) findViewById(R.id.spinnerModif);
        int id = (int) getIntent().getSerializableExtra("id");
        Date date = (Date) getIntent().getSerializableExtra("date");

        // Créez une nouvelle instance de Calendar
        Calendar calendar = Calendar.getInstance();

        // Définissez la date sélectionnée
        calendar.setTime(date);

        // Définissez l'heure et les minutes sélectionnées
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        // Récupérez le temps en millisecondes
        long selectedDateTimeInMillis = calendar.getTimeInMillis();

        db.modifier(db.getWritableDatabase(), id, nom.getText().toString(), description.getText().toString(), Integer.parseInt(ordre.getSelectedItem().toString()), Long.toString(selectedDateTimeInMillis));

        returnIntent.putExtra(MainActivity.REQUEST_RESULT, "modif");
        setResult(RESULT_OK, returnIntent);
        finish();
    }



    public void showTimePickerDialog() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour = hourOfDay; // Mettre à jour l'heure
                        Modifier.this.minute = minute; // Mettre à jour les minutes
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}