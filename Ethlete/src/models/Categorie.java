/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


public class Categorie {
    private int idcateg; 
    private String  nomcateg;//primary

    public Categorie() {
    }

    public Categorie(String nomcateg) {
        this.nomcateg = nomcateg;
    }

    public Categorie(int idcateg, String nomcateg) {
        this.idcateg = idcateg;
        this.nomcateg = nomcateg;
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
        return   nomcateg ;
    }

   public String concat(){
        return idcateg + ".@." + nomcateg ;
    } 
}
