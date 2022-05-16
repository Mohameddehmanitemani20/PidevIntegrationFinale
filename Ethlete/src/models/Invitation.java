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
public class Invitation {
     private long id_invitation ;	
    private String etat;	
    private long id_eq	;
    private long id_joueur;
   
    /**
     *
     * @param etat
     * @param id_eq
     * @param id_joueur
     */
    public Invitation(String etat, long id_eq, long id_joueur) {
        this.etat = etat;
        this.id_eq = id_eq;
        this.id_joueur = id_joueur;
    }

    public Invitation() {
    }

        
    

    public long getId_invitation() {
        return id_invitation;
    }

    public void setId_invitation(long id_invitation) {
        this.id_invitation = id_invitation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public long getId_eq() {
        return id_eq;
    }

    public void setId_eq(long id_eq) {
        this.id_eq = id_eq;
    }

    public long getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(long id_joueur) {
        this.id_joueur = id_joueur;
    }

    @Override
    public String toString() {
        return "Invitation{" + "id_invitation=" + id_invitation + ", etat=" + etat + ", id_eq=" + id_eq + ", id_joueur=" + id_joueur + '}';
    }

   

    
    
    
    

}