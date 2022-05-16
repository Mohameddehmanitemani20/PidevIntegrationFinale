/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author 21624
 */
public class Evenement {
    private int id_event;
    private String nom_event;
    private Date date_debut;
    private Date date_fin;
    private String typeE;
    private String lieu;
    private Float prixU;
   
    
    public Evenement(String nom_event, Date date_debut, Date date_fin, String typeE, String lieu) {
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.lieu = lieu;
    }
    

    public Evenement(String nom_event, Date date_debut, Date date_fin, String typeE, String lieu, int id_formation, int id_inter, int id_compet) {
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.lieu = lieu;
        this.id_formation = id_formation;
        this.id_inter = id_inter;
        this.id_compet = id_compet;
    }

    public Evenement(int id_event, String nom_event, Date date_debut, Date date_fin, String typeE, String lieu, int id_formation, int id_inter, int id_compet) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.lieu = lieu;
        this.id_formation = id_formation;
        this.id_inter = id_inter;
        this.id_compet = id_compet;
    }

   

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    private int id_formation;
    private int id_inter;
    private int id_compet;


    public Evenement() {
    }

    public Evenement(String nom_event, Date date_debut, Date date_fin, String typeE, int id_formation, int id_inter) {
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.id_formation = id_formation;
        this.id_inter = id_inter;
    }
    

    public Evenement(int id_event, String nom_event, Date date_debut, Date date_fin, String typeE, int id_formation, int id_inter, int id_compet) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.id_formation = id_formation;
        this.id_inter = id_inter;
        this.id_compet = id_compet;
    }

    public Evenement(int id_event, String nom_event, Date date_debut, Date date_fin, String typeE, String lieu, int id_formation, int id_inter, int id_compet , Float prixU) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.lieu = lieu;
        this.prixU = prixU;
        this.id_formation = id_formation;
        this.id_inter = id_inter;
        this.id_compet = id_compet;
    }

    public Evenement(String nom_event, Date date_debut, Date date_fin, String typeE, String lieu, Float prixU) {
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.lieu = lieu;
        this.prixU = prixU;
    }

    public Evenement(String nom_event, Date date_debut, Date date_fin, String typeE, int id_formation, int id_inter, int id_compet) {
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.typeE = typeE;
        this.id_formation = id_formation;
        this.id_inter = id_inter;
        this.id_compet = id_compet;
    }

   
    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

  

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getId_inter() {
        return id_inter;
    }

    public void setId_inter(int id_inter) {
        this.id_inter = id_inter;
    }

    public int getId_compet() {
        return id_compet;
    }

    public void setId_compet(int id_compet) {
        this.id_compet = id_compet;
    }

    public String getTypeE() {
        return typeE;
    }

    public void setTypeE(String typeE) {
        this.typeE = typeE;
    }

    public Float getPrixU() {
        return prixU;
    }

    public void setPrixU(Float prixU) {
        this.prixU = prixU;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", nom_event=" + nom_event + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", typeE=" + typeE + ", lieu=" + lieu + ", prixU=" + prixU + ", id_formation=" + id_formation + ", id_inter=" + id_inter + ", id_compet=" + id_compet + '}';
    }

   
    

public String concat(){
        return id_event + "/@/" + nom_event + "/@/" + date_debut + "/@/" + date_fin + "/@/" + typeE + "/@/" + id_formation + "/@/" + id_inter + "/@/" + id_compet  + "/@/" + prixU ;
    }
    
}
