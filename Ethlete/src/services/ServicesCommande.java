/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.I_commande;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Categorie;
import models.Commande;
import models.CommandeProduit;
import util.DataSource;
public class ServicesCommande implements I_commande {
            Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public boolean ajouterCommande(Commande c) {

        
         String request = "INSERT INTO `commande`(`idp`, `quantite`, `datecom`) VALUES ("+c.getIdp()+","+c.getQuantite()+",NOW())";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(request) == 1)
                    return true;
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        
    }

    @Override
    public List<Commande> afficherCommandes() {
         List<Commande> commandes = new ArrayList<Commande>();
       String req="SELECT * FROM commande";
       
         try {
             Statement st = cnx.createStatement();
             
             ResultSet rs = st.executeQuery(req);
             while (rs.next())
             {
                Commande c = new Commande (rs.getInt("idcom"),rs.getInt("idp"),rs.getInt("quantite"),rs.getString("datecom"));
                commandes.add(c);
                
                
             }
             
             
         } catch (SQLException ex) {
             ex.printStackTrace();// twarik mochkla win//
         }
         return commandes;
        
       
    }

    @Override
    public boolean modifierCommande(Commande c) {
       String req = "UPDATE `commande` SET `idp`="+c.getIdp()+",`quantite`="+c.getQuantite()+",`datecom`='"+c.getDatecom()+"' WHERE idcom = "+c.getIdcom()+" ";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean supprimerCommande(Commande c) {
        String req = "DELETE FROM `commande` WHERE idcom = "+c.getIdcom()+" ";

        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Commande> trierCommande() {
         List<Commande> commandes=afficherCommandes();
         List<Commande> sortedCom =commandes.stream().sorted(Comparator.comparing(Commande::getDatecom)).collect(Collectors.toList());
         return sortedCom;
    }

    @Override
    public List<Commande> chercherCommande(List<Commande> initialList, String input) {
       List<Commande> strList = initialList.stream()
                           .map( Commande::concat )
                           .filter(pt -> pt.toLowerCase().contains(input.toLowerCase()))
                           .map(pt -> new Commande(Integer.parseInt(pt.split(".@.")[0]),parseInt (pt.split(".@.")[1]),parseInt(pt.split(".@.")[2]),pt.split(".@.")[3]))
                           .collect( Collectors.toList() );
        
        return strList;
    }

    /**
     *
     * @return
     */
    @Override
    public List<CommandeProduit> afficherCommandeProduit() {
        List<CommandeProduit>  listcp = new ArrayList<CommandeProduit>();
         String req="SELECT produit.idp,produit.nomp,produit.prix,commande.idcom,commande.quantite,commande.datecom From produit left join commande on produit.idp=commande.idp";
         try {
             Statement st = cnx.createStatement();
             
             ResultSet rs = st.executeQuery(req);
             while (rs.next())
             {
                CommandeProduit cp= new CommandeProduit(rs.getInt("idp"),rs.getString("nomp"),rs.getFloat("prix"),rs.getInt("idcom"),rs.getInt("quantite"),rs.getString("datecom"));
                listcp.add(cp);
                
                
             }
             
             
         } catch (SQLException ex) {
             ex.printStackTrace();// twarik mochkla win//
         }
         return listcp;
        
       
    }

    @Override
    public List<CommandeProduit> trierCommandeProduit() {
          List<CommandeProduit> categories=afficherCommandeProduit();
          List<CommandeProduit> sortedCateg =categories.stream().sorted(Comparator.comparing(CommandeProduit::getNomp)).collect(Collectors.toList());
         return sortedCateg;
         
    }

    @Override
    public List<CommandeProduit> chercherCommandePorduit(List<CommandeProduit> initialList, String input) {
       List<CommandeProduit> strList = initialList.stream()
                           .map(CommandeProduit::concat )
                           .filter(pt -> pt.toLowerCase().contains(input.toLowerCase()))
                           .map(pt -> new CommandeProduit(Integer.parseInt(pt.split(".@.")[0]),pt.split(".@.")[1],Float.parseFloat(pt.split(".@.")[2]),Integer.parseInt(pt.split(".@.")[3]),Integer.parseInt(pt.split(".@.")[4]),pt.split(".@.")[5]))
                           .collect( Collectors.toList());
        
        return strList;
    }

    
        
    }
        
