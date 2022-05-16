/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author anasl
 */
public class Equipe {
     private long id_equipe ;
    private String nom_equipe ;	
    private long id_responsable;

    public Equipe() {
    }

    public Equipe(String nom_equipe, long id_responsable) {
        this.nom_equipe = nom_equipe;
        this.id_responsable = id_responsable;
    }

    public Equipe(long id_equipe, String nom_equipe, long id_responsable) {
        this.id_equipe = id_equipe;
        this.nom_equipe = nom_equipe;
        this.id_responsable = id_responsable;
    }

    public long getId_equipe() {
        return id_equipe;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public long getId_responsable() {
        return id_responsable;
    }

    public void setId_equipe(long id_equipe) {
        this.id_equipe = id_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public void setId_responsable(long id_responsable) {
        this.id_responsable = id_responsable;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id_equipe=" + id_equipe + ", nom_equipe=" + nom_equipe + ", id_responsable=" + id_responsable + '}';
    }
    
}
