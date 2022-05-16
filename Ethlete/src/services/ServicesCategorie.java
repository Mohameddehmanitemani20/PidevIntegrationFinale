/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.I_categorie;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.TableColumn;
import models.Categorie;
import models.Commande;
import util.DataSource;

/**
 *
 * @author ASUS
 */
public class ServicesCategorie implements I_categorie{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public boolean ajouterCategorie(Categorie cg) {
        String request = "INSERT INTO `categorie`(`nomcateg`) VALUES ('"+cg.getNomcateg()+"')";
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
    public List<Categorie> afficherCategorie() {
        List<Categorie> categories = new ArrayList<Categorie>();

        String req="select*from categorie";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
               categories.add(new Categorie(rs.getInt("idcateg"),rs.getString("nomcateg")));
                
            }
        

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        
        
     return categories;
        
    }

    @Override
    public List<Categorie> afficherNomCategorie() {
         List<Categorie> categories = new ArrayList<Categorie>();

        String req="select nomcateg from categorie";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
               categories.add(new Categorie(rs.getString("nomcateg")));
                
            }
        

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        
        
     return categories;
        
    }
    

   

        
        
    

    @Override
    public boolean modifierCategorie(Categorie cg) {
        String req = "UPDATE `categorie` SET `nomcateg`='"+cg.getNomcateg()+"' WHERE idcateg = "+cg.getIdcateg()+" ";
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
    public boolean supprimerCategorie(Categorie cg) {
        String req = "DELETE FROM `categorie` WHERE idcateg= '"+cg.getIdcateg()+"' ";

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
    
    
   
    public List<Categorie> trierCategorie(){
         List<Categorie> categories=afficherCategorie();
         List<Categorie> sortedCateg =categories.stream().sorted(Comparator.comparing(Categorie::getNomcateg)).collect(Collectors.toList());
         return sortedCateg;
     }

    @Override
    public List<Categorie> chercherCategorie(List<Categorie> initialList, String input) {
         List<Categorie> strList = initialList.stream()
                           .map( Categorie::concat )
                           .filter(pt -> pt.toLowerCase().contains(input.toLowerCase()))
                           .map(pt -> new Categorie(Integer.parseInt(pt.split(".@.")[0]),pt.split(".@.")[1]))
                           .collect( Collectors.toList() );
        
        return strList;
    }
   
    public Categorie retrieveCategorie(int id){
        Categorie categorie = null;

        String req="select*from categorie where idcateg = "+id+"";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
               categorie =new Categorie(rs.getInt("idcateg"),rs.getString("nomcateg"));
                
            }
        

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        
        
     return categorie;

    }

    @Override
    public int getidcateg(String s) {
         int id = 0;

        String req="select idcateg from categorie where nomcateg='"+s+"'";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
               id=rs.getInt("idcateg");
               
                
            }
        

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        
        
     return id;
    }
   
     
    }

    

    

        
        
        
    
        
    
     
     
     
     
    



