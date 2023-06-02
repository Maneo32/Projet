package com.example.projet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaseDonnees extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Rappel";
    private static final int DATABASE_VERSION = 1;

    public BaseDonnees(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE Rappel (nom TEXT, description TEXT, ordre TEXT, date TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Evenement> recupererDonnees() {
        Log.i("TAG", "MyDatabaseHelper.getAllNotes ... " );
        SQLiteDatabase dbs = this.getReadableDatabase();
        ArrayList<Evenement> listeDonnees = new ArrayList<>();
        String query = "SELECT * FROM Rappel";
        Cursor cursor = dbs.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String valeur1 = cursor.getString(0);
            String valeur2 = cursor.getString(1);
            String valeur3 = cursor.getString(2);
            String valeur4 = cursor.getString(3);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Evenement donnees = new Evenement(Integer.parseInt(valeur3), valeur2, valeur1, new Date()); // Remplacez par votre propre classe de modèle de données
            listeDonnees.add(donnees);
        }

        cursor.close();
        return listeDonnees;
    }
}
