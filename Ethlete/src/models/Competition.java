/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author gaming
 */
public class Competition {
    private int id_competition;
    private int nb_equipe;
    private String nom;
    private String date;
    private String adresse;
    private static Competition INSTANCE = null;


    public Competition(){
    }
   

    public Competition(int id_competition, int nb_equipe, String date,  String adresse,String nom) {
        this.id_competition = id_competition;
        this.nb_equipe = nb_equipe;
        this.nom = nom;
        this.date = date;
        this.adresse = adresse;
    }

    public Competition(int nb_equipe, String nom, String date, String adresse) {
        this.nb_equipe = nb_equipe;
        this.nom = nom;
        this.date = date;
        this.adresse = adresse;
    }

    public Competition(int id_competition) {
        this.id_competition = id_competition;
    }
 

    public int getId_competition() {
        return id_competition;
    }

    public int getNb_equipe() {
        return nb_equipe;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    public void setNb_equipe(int nb_equipe) {
        this.nb_equipe = nb_equipe;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getAdresse() {
        return adresse;
    }
public static Competition getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Competition();
            
        }
        return INSTANCE;
}
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "competition{" + "id_competition=" + id_competition + ", nb_equipe=" + nb_equipe + ", nom=" + nom + ", date=" + date + ", adresse=" + adresse + '}';
    }
    
   
    

}
