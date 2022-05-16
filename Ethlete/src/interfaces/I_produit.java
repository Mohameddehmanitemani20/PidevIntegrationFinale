/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import models.Categorie;
import models.Produit;
import models.ProduitCategorie;
import util.DataSource;

/**
 *
 * @author ASUS
 */
public interface I_produit {
    
    public boolean ajouterProduit(Produit p);

    public List<Produit> afficherProduits();

    public boolean modifierProduit(Produit p);

    public boolean supprimerProduit(Produit p);
    public List<Produit> trierProduit();
      
    public List<Produit> chercherProduit(List<Produit> initialList, String input);
     
     public int getidcprod(String s);
     
     
     public List<ProduitCategorie> afficherProduitCateg();
     public List<ProduitCategorie> chercherProduitCategorie(String s);
     
    
}
