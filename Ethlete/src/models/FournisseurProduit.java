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
public class FournisseurProduit {
     private int idf; // Primary Key
    private String  nomf;
    private String prenomf;
    private String email;
    private int telf;
    private String adresse;
    private int idp; 
    private String  nomp;
    private float prix;

    public FournisseurProduit() {
    }

    public FournisseurProduit(int idf, String nomf, String prenomf, String email, int telf, String adresse, int idp, String nomp, float prix) {
        this.idf = idf;
        this.nomf = nomf;
        this.prenomf = prenomf;
        this.email = email;
        this.telf = telf;
        this.adresse = adresse;
        this.idp = idp;
        this.nomp = nomp;
        this.prix = prix;
    }

    public FournisseurProduit(String nomf, String prenomf, String adresse, String nomp) {
        this.nomf = nomf;
        this.prenomf = prenomf;
        this.adresse = adresse;
        this.nomp = nomp;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nomf) {
        this.nomf = nomf;
    }

    public String getPrenomf() {
        return prenomf;
    }

    public void setPrenomf(String prenomf) {
        this.prenomf = prenomf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return idf +" "+ nomf + " " + prenomf + " " + email + " " + telf + " " + adresse + " " + idp + " " + nomp + " " + prix ;
    }
   
    public String concat(){
        return nomf+".@."+prenomf+".@."+adresse+".@."+nomp;
    }  
}
