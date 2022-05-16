/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.I_fournisseur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import models.CommandeProduit;
import models.Fournisseur;
import models.Fournisseur;
import models.Fournisseur;
import models.FournisseurProduit;
import util.DataSource;
import util.DataSource;

/**
 *
 * @author ASUS
 */
    public class ServicesFournisseur implements I_fournisseur{
    
            Connection cnx = DataSource.getInstance().getCnx();
     
     
    @Override
    public boolean ajouterfournisseur(Fournisseur f) {
        String request = "INSERT INTO `fournisseur`(`nomf`, `prenomf`, `email`, `telf`, `adresse`,`idp`) VALUES ('"+f.getNomf()+"','"+f.getPrenomf()+"','"+f.getEmail()+"',"+f.getTelf()+",'"+f.getAdresse()+"',"+f.getIdp()+")";
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
    public List<Fournisseur> afficherFournisseurs() {
       List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
       String req="SELECT * FROM fournisseur";
       
         try {
             Statement st = cnx.createStatement();
             
             ResultSet rs = st.executeQuery(req);
             while (rs.next())
             {
                Fournisseur f = new Fournisseur(rs.getInt("idf"),rs.getString("nomf"),rs.getString("prenomf"),rs.getString("email"),rs.getInt("telf"),rs.getString("adresse"),rs.getInt("idp"));
                fournisseurs.add(f);
                
                
             }
             
             
         } catch (SQLException ex) {
             ex.printStackTrace();// twarik mochkla win//
         }
         return fournisseurs;
       
       
            
               
            
    }

    @Override
    public boolean modifierFournisseur(Fournisseur f) {
        String req = "UPDATE `fournisseur` SET `nomf`='"+f.getNomf()+"',`prenomf`='"+f.getPrenomf()+"',`email`='"+f.getEmail()+"',`telf`='"+f.getTelf()+"',`adresse`='"+f.getEmail()+"',`idp`='"+f.getIdp()+"' WHERE idf = "+f.getIdf()+" ";
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
    public boolean supprimerFournisseur(Fournisseur f) {
       String req = "DELETE FROM `fournisseur` WHERE idf = "+f.getIdf()+" ";

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
    public List<Fournisseur> trierFournisseur() {
       List<Fournisseur> fournisseurs=afficherFournisseurs();
         List<Fournisseur> sortedCom =fournisseurs.stream().sorted(Comparator.comparing(Fournisseur::getPrenomf)).collect(Collectors.toList());
         return sortedCom;
    }

   // @Override
   // public List<Fournisseur> chercherFournisseur(List<Fournisseur> initialList, String input) {
       // List<Fournisseur> strList = initialList.stream()
                           //.map( Fournisseur::concat )
                           //.filter(pt -> pt.contains(input))
                           //.map(pt -> new Fournisseur(Integer.parseInt(pt.split(".@.")[0]), pt.split(".@.")[1], pt.split(".@.")[2], pt.split(".@.")[3], Integer.parseInt(pt.split(".@.")[4]),pt.split(".@.")[5],Integer.parseInt(pt.split(".@.")[6]))
                           //.collect( Collectors.toList() );
        
       // return strList;
   // }

    
    @Override
    public List<FournisseurProduit> afficherFournisseurProduit() {
       List<FournisseurProduit>  listfp = new ArrayList<FournisseurProduit>();
         String req="SELECT fournisseur.idf,fournisseur.nomf,fournisseur.prenomf,fournisseur.email,fournisseur.telf,fournisseur.adresse,produit.idp,produit.nomp,produit.prix From fournisseur left join produit on fournisseur.idp=produit.idp";
         try {
             Statement st = cnx.createStatement();
             
             ResultSet rs = st.executeQuery(req);
             while (rs.next())
             {
                FournisseurProduit fp= new FournisseurProduit(rs.getInt("idf"),rs.getString("nomf"),rs.getString("prenomf"),rs.getString("email"),rs.getInt("telf"),rs.getString("adresse"),rs.getInt("idp"),rs.getString("nomp"),rs.getFloat("prix"));
                listfp.add(fp);
                
                
             }
             
             
         } catch (SQLException ex) {
             ex.printStackTrace();// twarik mochkla win//
         }
         return listfp;
        
    }

    @Override
    public List<FournisseurProduit> trierFournisseurProduit() {
         List<FournisseurProduit> fournisseurs=afficherFournisseurProduit();
          List<FournisseurProduit> sortedCateg =fournisseurs.stream().sorted(Comparator.comparing(FournisseurProduit::getAdresse)).collect(Collectors.toList());
         return sortedCateg;
    }

    @Override
    public List<FournisseurProduit> chercherFournisseurPorduit(String s) {
        FournisseurProduit fournisseur;
        List<FournisseurProduit> fournisseurs=afficherFournisseurProduit();
        List<FournisseurProduit> resultat=fournisseurs.stream().filter(FournisseurProduit->s.equals(FournisseurProduit.getNomp())).collect(Collectors.toList());
        return resultat;
    
    }

    @Override
    public List<Fournisseur> chercherFournisseur(List<Fournisseur> initialList, String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
    
    
    
}
