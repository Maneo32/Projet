package com.example.projet;

import java.util.ArrayList;
import java.util.Date;

public class Evenement {


        private  int ordre;
        private String nom;

    @Override
    public String toString() {
        return "Evenement{" +
                "ordre=" + ordre +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    private String description;
        private Date date;


        public Evenement(int ordre, String nom, String description, Date date){
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
