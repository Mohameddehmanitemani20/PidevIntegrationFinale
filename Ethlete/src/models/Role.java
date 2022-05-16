/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author MSI
 */
public enum Role {
    ROLE_JOUEUR,ROLE_FORMATEUR,ROLE_ADMIN,ROLE_RESPONSABLE,ROLE_USER;

   /* @Override
    public String toString() {
        return "Role{" + '}';
    }*/

    public static Role getROLE_USER() {
        return ROLE_USER;
    }
    
    public static Role getROLE_JOUEUR() {    
        return ROLE_JOUEUR;
    }

    public static Role getROLE_FORMATEUR() {
        return ROLE_FORMATEUR;
    }

    public static Role getROLE_ADMIN() {
        return ROLE_ADMIN;
    }

    public static Role getROLE_RESPONSABLE() {
        return ROLE_RESPONSABLE;
    }
    
    
}

