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
public class ReclamationUtilisateur {
    private int idr; // Primary Key
    private String contenu;
    private int id;
    private String daterec; 
    private int idRaison;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private int telephone;
    private String password;
    private String etat;

    public ReclamationUtilisateur() {
    }

    public ReclamationUtilisateur(int idr, String contenu, int id, String daterec, int idRaison, String nom, String prenom, String username, String email, int telephone, String password, String etat) {
        this.idr = idr;
        this.contenu = contenu;
        this.id = id;
        this.daterec = daterec;
        this.idRaison = idRaison;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.etat = etat;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDaterec() {
        return daterec;
    }

    public void setDaterec(String daterec) {
        this.daterec = daterec;
    }

    public int getIdRaison() {
        return idRaison;
    }

    public void setIdRaison(int idRaison) {
        this.idRaison = idRaison;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return   idr + " " + contenu + " " + id + " " + daterec + " " + idRaison + " " + nom + " " + prenom + " " + username + " " + email + " " + telephone + " " + password + " " + etat ;
    }

    

    
    

   
    
    
    
    
}


