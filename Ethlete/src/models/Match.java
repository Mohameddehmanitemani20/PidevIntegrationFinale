/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author gaming
 */
public class Match {
    private int id_match;
    private String equipe1;
    private String equipe2;
    private String etat;
    private int id_journe;

    public Match(int id_match, String equipe1, String equipe2, String etat, int id_journe) {
        this.id_match = id_match;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.etat = etat;
        this.id_journe = id_journe;
    }

    public Match(String equipe1, String equipe2, int id_journe) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.id_journe = id_journe;
    }

    public Match(String equipe1, String equipe2, String etat, int id_journe) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.etat = etat;
        this.id_journe = id_journe;
    }

    public Match(int id_match, String equipe1, String equipe2, String etat) {
        this.id_match = id_match;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.etat = etat;
    }

    public Match(int id_match) {
        this.id_match = id_match;
    }

    
    public int getId_match() {
        return id_match;
    }

    public String getEquipe1() {
        return equipe1;
    }

    public String getEquipe2() {
        return equipe2;
    }

    public String getEtat() {
        return etat;
    }

    public int getId_journe() {
        return id_journe;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public void setEquipe1(String equipe1) {
        this.equipe1 = equipe1;
    }

    public void setEquipe2(String equipe2) {
        this.equipe2 = equipe2;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setId_journe(int id_journe) {
        this.id_journe = id_journe;
    }
    





    
    
    

    }
    
    
    






    

    

    
