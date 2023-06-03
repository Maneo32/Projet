package com.example.projet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
        String createTableQuery = "CREATE TABLE Rappel (nom TEXT, description TEXT, ordre TEXT, date TEXT, id int)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void supp(SQLiteDatabase db, int id, long date, String desc, String nom, int ordre) {
        db.execSQL("DELETE FROM Rappel WHERE id = ?", new Object[]{id});
        db.execSQL("INSERT INTO supp (nom, description, ordre, date) VALUES (?, ?, ?, ?)", new Object[]{nom, desc, ordre, date});
        Log.d("TAG", "Effacé");
    }


    public ArrayList<Evenement> recupererDonnees() {
        SQLiteDatabase dbs = this.getReadableDatabase();
        ArrayList<Evenement> listeDonnees = new ArrayList<>();
        String query = "SELECT * FROM Rappel";
        Cursor cursor = dbs.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String valeur1 = cursor.getString(0);
            String valeur2 = cursor.getString(1);
            String valeur3 = cursor.getString(2);
            String valeur4 = cursor.getString(3);
            String valeur5 = cursor.getString(4);
            Evenement donnees = new Evenement(Integer.parseInt(valeur5), Integer.parseInt(valeur3), valeur2, valeur1, new Date(Long.parseLong(valeur4))); // Remplacez par votre propre classe de modèle de données
            listeDonnees.add(donnees);
        }

        cursor.close();
        return listeDonnees;
    }


    public ArrayList<Evenement> recupererSupp() {
        SQLiteDatabase dbs = this.getReadableDatabase();
        ArrayList<Evenement> listeDonnees = new ArrayList<>();
        String query = "SELECT * FROM supp";
        Cursor cursor = dbs.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String valeur1 = cursor.getString(0);
            String valeur2 = cursor.getString(1);
            String valeur3 = cursor.getString(2);
            String valeur4 = cursor.getString(3);
            String valeur5 = cursor.getString(4);
            Evenement donnees = new Evenement(Integer.parseInt(valeur5), Integer.parseInt(valeur3), valeur2, valeur1, new Date(Long.parseLong(valeur4))); // Remplacez par votre propre classe de modèle de données
            listeDonnees.add(donnees);
        }

        cursor.close();
        return listeDonnees;
    }

}