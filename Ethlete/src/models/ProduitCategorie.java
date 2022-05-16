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
public class ProduitCategorie {
     private int idp; // Primary Key
    private String  nomp;
    private float prix;
    private int idcateg;  
    private String  nomcateg;//primary

    public ProduitCategorie(int idp, String nomp, float prix, int idcateg, String nomcateg) {
        this.idp = idp;
        this.nomp = nomp;
        this.prix = prix;
        this.idcateg = idcateg;
        this.nomcateg = nomcateg;
    }

    public ProduitCategorie() {
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

    public int getIdcateg() {
        return idcateg;
    }

    public void setIdcateg(int idcateg) {
        this.idcateg = idcateg;
    }

    public String getNomcateg() {
        return nomcateg;
    }

    public void setNomcateg(String nomcateg) {
        this.nomcateg = nomcateg;
    }

    @Override
    public String toString() {
        return  + idp + "  " + nomp + "  " + prix + " " + idcateg + " " + nomcateg ;
    }
    
    
}


