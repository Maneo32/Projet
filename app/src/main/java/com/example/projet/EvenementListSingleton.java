package com.example.projet;

import com.example.projet.Evenement;

import java.util.ArrayList;

public class EvenementListSingleton {
    private static EvenementListSingleton instance;
    private ArrayList<Evenement> list;
    private ArrayList<Evenement> listSupprimes;
    private ArrayList<Evenement> listTermines;

    private EvenementListSingleton() {
        list = new ArrayList<>();
        listSupprimes = new ArrayList<>();
        listTermines = new ArrayList<>();
    }

    public static EvenementListSingleton getInstance() {
        if (instance == null) {
            synchronized (EvenementListSingleton.class) {
                if (instance == null) {
                    instance = new EvenementListSingleton();
                }
            }
        }
        return instance;
    }

    public ArrayList<Evenement> getList() {
        return list;
    }

    public ArrayList<Evenement> getListSupprimes() {
        return listSupprimes;
    }

    public ArrayList<Evenement> getListTermines() {
        return listTermines;
    }

    public void ajouterEvenement(Evenement evenement) {
        list.add(evenement);
    }

    public void ajouterEvenementSupprime(Evenement evenement) {
        listSupprimes.add(evenement);
    }

    public void ajouterEvenementTermine(Evenement evenement) {
        listTermines.add(evenement);
    }
}
