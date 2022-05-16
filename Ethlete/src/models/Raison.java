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
public class Raison {
    private int idraison; // Primary Key
    private String  raisontxt;

    public Raison() {
    }

    public Raison(String raisontxt) {
        this.raisontxt = raisontxt;
    }

    public Raison(int idraison, String raisontxt) {
        this.idraison = idraison;
        this.raisontxt = raisontxt;
    }

    public int getIdraison() {
        return idraison;
    }

    public void setIdraison(int idraison) {
        this.idraison = idraison;
    }

    public String getRaisontxt() {
        return raisontxt;
    }

    public void setRaisontxt(String raisontxt) {
        this.raisontxt = raisontxt;
    }

    @Override
    public String toString() {
        return "Raison{" + "idraison=" + idraison + ", raisontxt=" + raisontxt + '}';
    }
    
    
     public String concat(){
        return idraison+ ".@." + raisontxt ;
    } 
}
