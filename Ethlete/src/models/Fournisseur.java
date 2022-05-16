/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

public class Fournisseur{
    //var

    private int idf; // Primary Key
    private String  nomf;
    private String prenomf;
    private String email;
    private int telf;
    private String adresse;
    private int idp;

//Constructors

    public Fournisseur() { }

    public Fournisseur(String nomf, String prenomf, String email, int telf, String adresse, int idp) {
        this.nomf = nomf;
        this.prenomf = prenomf;
        this.email = email;
        this.telf = telf;
        this.adresse = adresse;
        this.idp = idp;
    }

    public Fournisseur(int idf, String nomf, String prenomf, String email, int telf, String adresse, int idp) {
        this.idf = idf;
        this.nomf = nomf;
        this.prenomf = prenomf;
        this.email = email;
        this.telf = telf;
        this.adresse = adresse;
        this.idp = idp;
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

    @Override
    public String toString() {
        return "Fournisseur{" + "idf=" + idf + ", nomf=" + nomf + ", prenomf=" + prenomf + ", email=" + email + ", telf=" + telf + ", adresse=" + adresse + ", idp=" + idp + '}';
    }

   public String concat(){
        return idf + ".@." + nomf + ".@." + prenomf+ ".@." + email +".@."+ telf +".@."+ email +".@."+ adresse +".@."+ idp  ;
    } 
}

