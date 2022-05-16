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
public class CommandeProduit {
    private int idp; 
    private String  nomp;
    private float prix;
    private int idcom; 
    private int quantite  ;
    private String datecom  ;

    public CommandeProduit() {
    }

    public CommandeProduit(int idp, String nomp, float prix, int idcom, int quantite, String datecom) {
        this.idp = idp;
        this.nomp = nomp;
        this.prix = prix;
        this.idcom = idcom;
        this.quantite = quantite;
        this.datecom = datecom;
    }

    public CommandeProduit(int idp, String nomp, float prix, int quantite, String datecom) {
        this.idp = idp;
        this.nomp = nomp;
        this.prix = prix;
        this.quantite = quantite;
        this.datecom = datecom;
    }

    public CommandeProduit(int idp, int idcom, int quantite, String datecom) {
        this.idp = idp;
        this.idcom = idcom;
        this.quantite = quantite;
        this.datecom = datecom;
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

    public int getIdcom() {
        return idcom;
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDatecom() {
        return datecom;
    }

    public void setDatecom(String datecom) {
        this.datecom = datecom;
    }

    @Override
    public String toString() {
        return idp +" "+ nomp+ " " + prix +" " +idcom  +" " +quantite + " "  +datecom ;
    }
    
    
     public String concat(){
        return idp+".@."+nomp+".@."+prix+".@."+idcom+".@." +quantite+".@." +datecom;
    } 
    
}
