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
        String createTableQuery = "CREATE TABLE Rappel (nom TEXT, description TEXT, ordre TEXT, date TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT)";
        String createTableQuery2 = "CREATE TABLE supp (nom TEXT, description TEXT, ordre TEXT, date TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT)";
        String createTableQuery3 = "CREATE TABLE Termine (nom TEXT, description TEXT, ordre TEXT, date TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(createTableQuery);
        db.execSQL(createTableQuery2);
        db.execSQL(createTableQuery3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void supp(SQLiteDatabase db, int id, long date, String desc, String nom, int ordre) {
        db.execSQL("DELETE FROM Rappel WHERE id = ?", new Object[]{id});
        db.execSQL("INSERT INTO supp (nom, description, ordre, date) VALUES (?, ?, ?, ?)", new Object[]{nom, desc, ordre, date});
        Log.d("TAG", "Effacé");
    }

    public void val(SQLiteDatabase db, int id, long date, String desc, String nom, int ordre) {
        db.execSQL("DELETE FROM Rappel WHERE id = ?", new Object[]{id});
        db.execSQL("INSERT INTO termine (nom, description, ordre, date) VALUES (?, ?, ?, ?)", new Object[]{nom, desc, ordre, date});
        Log.d("TAG", "terminé");
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



    public ArrayList<Evenement> recupererTermine() {
        SQLiteDatabase dbs = this.getReadableDatabase();
        ArrayList<Evenement> listeDonnees = new ArrayList<>();
        String query = "SELECT * FROM termine";
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

    public void recuperer(SQLiteDatabase db, int id, long date, String desc, String nom, int ordre){
        db.execSQL("INSERT INTO Rappel (nom, description, ordre, date) VALUES (?, ?, ?, ?)", new Object[]{nom, desc, ordre, date});
        db.execSQL("delete from supp where id=?", new Object[]{id});
    }

    public void delTer(SQLiteDatabase db, int id) {
        db.execSQL("delete from termine where id=?", new Object[]{id});
    }
    public void delSupp(SQLiteDatabase db, int id) {
        db.execSQL("delete from supp where id=?", new Object[]{id});
    }


    public void modifier(SQLiteDatabase db, int id, String nom, String desc, int ordre, String date){
        db.execSQL("Insert into Rappel (nom, description, ordre, date) values (?,?,?,?)", new Object[]{nom, desc, ordre, date});
        db.execSQL("delete from Rappel where id=?", new Object[]{id});
    }
}