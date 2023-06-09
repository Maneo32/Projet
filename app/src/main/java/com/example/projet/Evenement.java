package com.example.projet;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Evenement {


    private int id;
        private  int ordre;
        private String nom;

    private String description;
    private Date date;

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        String dateMieux = sdf.format(date);
        return "Rappel :" +
                "\n Titre :'" + nom + '\'' +
                "\n Description :'" + description + '\'' +
                " \n Ordre : " + ordre +
                "\n Date :" + dateMieux+
                "\n heure/minute :"+ date.getHours() +" "+date.getMinutes()+
                "\n";
    }



        public Evenement(int id, int ordre, String nom, String description, Date date){
            this.id=id;
            ArrayList l = new ArrayList();
            l.add(1);
            l.add(2);
            l.add(3);
            if (l.contains(ordre)){
                this.ordre = ordre;
            }
            this.date=date;
            this.nom = nom;
            this.description = description;

        }

    public int getId() {
        return id;
    }
    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
