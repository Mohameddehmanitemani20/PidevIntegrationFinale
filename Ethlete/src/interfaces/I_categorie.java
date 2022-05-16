/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Connection;
import java.util.List;
import models.Categorie;
import util.DataSource;

/**
 *
 * @author ASUS
 */
public interface I_categorie {
    
    public boolean ajouterCategorie(Categorie cg);
    public List<Categorie> afficherCategorie();
    public boolean modifierCategorie(Categorie cg);
    public boolean supprimerCategorie(Categorie cg);
    
    public List<Categorie> trierCategorie();
    public List<Categorie> chercherCategorie(List<Categorie> initialList, String input);
   
     public List<Categorie> afficherNomCategorie();
     public int getidcateg(String s);
     
   }
  

