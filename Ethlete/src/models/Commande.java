/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 *
 * @author ASUS
 */
public class Commande {
    private int idcom; // Primary Key
    private int idp;
    private int quantite  ;
    private String datecom  ;

    public Commande() {
    }

    public Commande(int idp, int quantite, String datecom) {
        this.idp = idp;
        this.quantite = quantite;
        this.datecom = datecom;
    }

    public Commande(int idcom, int idp, int quantite, String datecom) {
        this.idcom = idcom;
        this.idp = idp;
        this.quantite = quantite;
        this.datecom = datecom;
    }

    public int getIdcom() {
        return idcom;
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
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
        return "Commande{" + "idcom=" + idcom + ", idp=" + idp + ", quantite=" + quantite + ", datecom=" + datecom + '}';
    }
    
    public String concat(){
        return idcom + ".@." + idp + ".@." + quantite+ ".@." + datecom ;
    } 
}
   
    
    
  
