/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class Reclamation {
    private int idr; // Primary Key
    private String contenu;
    private int id;
    private String daterec; 
    private int idRaison;
    private String etat;

    public Reclamation() {
    }

    public Reclamation(String contenu, int id, String daterec, int idRaison, String etat) {
        this.contenu = contenu;
        this.id = id;
        this.daterec = daterec;
        this.idRaison = idRaison;
        this.etat = etat;
    }

    public Reclamation(int idr, String contenu, int id, String daterec, int idRaison, String etat) {
        this.idr = idr;
        this.contenu = contenu;
        this.id = id;
        this.daterec = daterec;
        this.idRaison = idRaison;
        this.etat = etat;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDaterec() {
        return daterec;
    }

    public void setDaterec(String daterec) {
        this.daterec = daterec;
    }

    public int getIdRaison() {
        return idRaison;
    }

    public void setIdRaison(int idRaison) {
        this.idRaison = idRaison;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return   idr + " " + contenu + " " + id + " " + daterec + " " + idRaison + "  " + etat ;
    }

    public Reclamation(String contenu, int id, int idRaison, String etat) {
        this.contenu = contenu;
        this.id = id;
        this.idRaison = idRaison;
        this.etat = etat;
    }

   
    
    
    
}
