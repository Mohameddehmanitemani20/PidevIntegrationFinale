/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import lombok.*;

/**
 *
 * @author pc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
     private int id;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String email;
    private String telephone;
    private String adresse;
    private String genre;

    private String username;
    private String password;
    private String role;

    public Utilisateur(String nom, String prenom, String date_naissance, String email, String telephone, String adresse, String username, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(int id, String nom, String prenom, String adresse, String email,String telephone,String genre, String date_naissance,String role) {
        this.id = id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.email = email;
        this.adresse = adresse;
        this.role=role;
        this.genre=genre;
    }
       public Utilisateur( String nom, String prenom, String adresse, String email,String telephone, String date_naissance) {
      
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.email = email;
        this.adresse = adresse;
    }

 

    
}
