/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import interfaces.I_evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Evenement;
import javafx.scene.control.DatePicker;
import util.DataSource;
/**
 *
 * @author 21624
 */
public class ServiceEvenement implements I_evenement{
    DatePicker dateD;
       DatePicker dateF;
     //var
   Connection     cnx = DataSource.getInstance().getCnx();

    
     @Override
    public Evenement ajouterEvenement(Evenement t) {
         String request;
                PreparedStatement pst=null;

      
        
        
        try {
            String req1;
     
   if(t.getTypeE() == "Formation")
       
   {
       System.out.println(t);

       
       req1 = "INSERT INTO evenement (nom_event,date_debut,date_fin,typeE,lieu,id_formation,id_inter,prixU) VALUES (?,?,"
                    + "?,?,?,?,?,?)";
        pst = cnx.prepareStatement(req1, Statement.RETURN_GENERATED_KEYS); 
        
                

            pst.setString(1,t.getNom_event());
            pst.setDate(2,t.getDate_debut());          
            pst.setDate(3,t.getDate_fin());
            pst.setString(4,t.getTypeE());
            pst.setString(5,t.getLieu());
            pst.setInt(6,t.getId_formation());
            pst.setInt(7,t.getId_inter());
            pst.setFloat(8,t.getPrixU());

            
   
   }
   else 
   {  req1 = "INSERT INTO evenement (nom_event,date_debut,date_fin,typeE,lieu,id_compet,id_inter,prixU) VALUES (?,?,"
                    + "?,?,?,?,?,?)";
        pst = cnx.prepareStatement(req1, Statement.RETURN_GENERATED_KEYS); 
       pst.setString(1,t.getNom_event());

            pst.setDate(2,   t.getDate_debut());          
            pst.setDate(3,   t.getDate_fin());
            pst.setString(4, t.getTypeE());
            pst.setString(5,t.getLieu());
            pst.setInt(6,t.getId_compet());
            pst.setInt(7,t.getId_inter());
            pst.setFloat(8,t.getPrixU());


   }
    pst.executeUpdate();
System.out.println("Evenement ajouté");
          
           
           
          


        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        ResultSet rs;
        try {
            rs = pst.getGeneratedKeys();
            if(rs.next())
                    t.setId_event(rs.getInt(1));   
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                 return t;

    }

    @Override
    public List<Evenement> afficherEvenement() {

        
        List<Evenement> evenements = new ArrayList<Evenement>();

        String req="SELECT id_event,nom_event,date_debut,date_fin,typeE,lieu,id_formation,id_inter,id_compet,prixU FROM evenement";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                evenements.add(new Evenement(rs.getInt("id_event"),
                        rs.getString("nom_event"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getString("typeE"),
                        rs.getString("lieu"),
                        rs.getInt("id_formation"),
                        rs.getInt("id_inter"),
                        rs.getInt("id_compet"),
                        rs.getFloat("prixU")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return evenements;    }

    @Override
    public boolean modifierEvenement(Evenement E) {

           String req = "UPDATE `evenement` SET "
                   + "`nom_event`='"+E.getNom_event()+"',`date_debut`='"+E.getDate_debut()+"',`date_fin`='"+E.getDate_fin()+"',`typeE`='"+E.getTypeE()+"',`lieu`='"+E.getLieu()+"',`id_formation`="+E.getId_formation()+",`id_inter`="+E.getId_inter()+",`id_compet`="+E.getId_compet()+",`prixU`="+E.getPrixU()+", WHERE id_event = "+E.getId_event()+" ";
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
    public boolean supprimerEvenement(Evenement E) {
   String req = "DELETE FROM `evenement` WHERE id_event = "+E.getId_event()+" ";

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
    /* @Override
    public List<Evenement> rechercherEvenement(List<Evenement> initialList, String input) {
    List<Evenement> strList = initialList.stream()
                           .map( Evenement::concat )
                           .filter(pt -> pt.toLowerCase().contains(input.toLowerCase()))
                           .map(pt -> new Evenement(Integer.parseInt(pt.split("/@/")[0]),pt.split("/@/")[1],pt.split("/@/")[2],pt.split("/@/")[3],1,1,1,1))
                           .collect( Collectors.toList() );
        
        return strList;
    }    
*/
    
    
     public List<Evenement> sortByNom(){
         List<Evenement> intervenant=afficherEvenement();
         List<Evenement> resultat = intervenant.stream()
                                                 .sorted(Comparator.comparing(Evenement::getNom_event))
                                                 .collect(Collectors.toList());
         return resultat;
     }

    @Override
    public List<Evenement> rechercherEvenement(List<Evenement> initialList, String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Evenement retrieveEvent(int id) {
        Evenement evenement = null;

        String req="SELECT id_event,nom_event,date_debut,date_fin,typeE,lieu,id_formation,id_inter,id_compet,prixU  FROM evenement where id_event = "+id+"";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                evenement = new Evenement(rs.getInt("id_event"),
                        rs.getString("nom_event"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getString("typeE"),
                        rs.getString("lieu"),
                        rs.getInt("id_formation"),
                        rs.getInt("id_inter"),
                        rs.getInt("id_compet"),
                        rs.getFloat("prixU")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return evenement;    
    }
    }
    
  
    


   


