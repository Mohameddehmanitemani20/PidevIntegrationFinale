/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

import java.util.Date;
import java.text.SimpleDateFormat;

import lombok.*;

/**
 *
 * @author pc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Formation {
     private int id_formation;

    public Formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public Formation() {
    }
    
   
    private String nom_formation;

    public Formation(int id_formation, String nom_formation, String dispositif, String programme) {
        this.id_formation = id_formation;
        this.nom_formation = nom_formation;
        this.dispositif = dispositif;
        this.programme = programme;
    }



    private Date date_debut;

    private Date date_fin;
    

    private String dispositif;

 
    private  String programme;

    public Formation(String nom_formation, Date date_debut, Date date_fin, String type, String programme) {
        this.nom_formation = nom_formation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.dispositif = type;
        this.programme = programme;
    }

    public Formation(int id_formation, String nom_formation, Date date_debut, Date date_fin, String type) {
        this.id_formation = id_formation;
        this.nom_formation = nom_formation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.dispositif = type;
    }

    public Formation(String nom_formation, Date date_debut, Date date_fin, String type) {
        this.nom_formation = nom_formation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.dispositif = type;
    }

  public String concat(){
        return id_formation + "/@/" + nom_formation + "/@/" + date_debut + "/@/" + date_fin + "/@/" + dispositif + "/@/" + programme ;
    }

    public Formation(String nom_formation) {
        this.nom_formation = nom_formation;
    }
  
    

}
