/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Connection;
import java.util.List;
import models.CommandeProduit;
import models.Fournisseur;
import models.Fournisseur;
import models.FournisseurProduit;
import util.DataSource;

/**
 *
 * @author ASUS
 */
public interface I_fournisseur {
    
    
    public boolean ajouterfournisseur(Fournisseur f);

    public List<Fournisseur> afficherFournisseurs();

    public boolean modifierFournisseur(Fournisseur f);

    public boolean supprimerFournisseur(Fournisseur f);
    public List<FournisseurProduit> afficherFournisseurProduit(); 
    
     public List<Fournisseur> trierFournisseur();
     public List<FournisseurProduit> trierFournisseurProduit();
    
    public List<Fournisseur> chercherFournisseur(List<Fournisseur> initialList, String input);
    public List<FournisseurProduit> chercherFournisseurPorduit(String s);


}
