/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.I_reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import models.Commande;
import models.CommandeProduit;
import models.FournisseurProduit;
import models.Reclamation;
import models.ReclamationUtilisateur;
import util.DataSource;

/**
 *
 * @author ASUS
 */
public class ServicesReclamation implements I_reclamation {
      Connection cnx= DataSource.getInstance().getCnx();

    @Override
    public boolean ajouterReclamation(Reclamation r) {
    //   String request = "INSERT INTO `reclamation`(`contenu`,`id`,`daterec`,`idRaison`,`etat`) VALUES ('"+r.getContenu()+"',"+r.getId()+",NOW(),"+r.getIdr()+",'"+r.getEtat()+"')";
       PreparedStatement pst=null;
    
    try {
                 String req1 = "INSERT INTO reclamation (contenu,id,daterec,idRaison,etat) VALUES (?,?,"
                    + "?,?,?)";
   pst = cnx.prepareStatement(req1);           
   pst.setString(1, r.getContenu());
long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
              pst.setInt(2,   r.getId());          
            pst.setString(3,   date.toString());

            pst.setInt(4, r.getIdRaison());
            pst.setString(5,r.getEtat());
          
           
            pst.executeUpdate();
         //   Statement st = cnx.createStatement();
         /*   if (st.executeUpdate(request) == 1)
                    return true;
            return false;*/
return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public List<Reclamation> afficherReclamations() {
      List<Reclamation> reclamations = new ArrayList<Reclamation>();
       String req="SELECT * FROM reclamation";
       
         try {
             Statement st = cnx.createStatement();
             
             ResultSet rs = st.executeQuery(req);
             while (rs.next())
             {
                Reclamation r = new Reclamation (rs.getInt("idr"),rs.getString("contenu"),rs.getInt("id"),rs.getString("daterec"),rs.getInt("idRaison"),rs.getString("etat"));
                reclamations.add(r);
                
                
             }
             
             
         } catch (SQLException ex) {
             ex.printStackTrace();// twarik mochkla win//
         }
         return reclamations;
            }

    @Override
    public boolean modifierReclamation(Reclamation r) {
        String req = "UPDATE `reclamation` SET `contenu`='"+r.getContenu()+"',`id`="+r.getId()+",`daterec`='"+r.getDaterec()+"',`idRaison`="+r.getIdRaison()+",`etat`='"+r.getEtat()+"' WHERE idr = "+r.getIdr()+" ";
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
    public boolean supprimerReclamation(Reclamation r) {
        String req = "DELETE FROM `reclamation` WHERE idr = "+r.getIdr()+" ";

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
     public int nombreReclamation(){
      String sql="SELECT COUNT(*) FROM reclamation";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
     }

    @Override
    public List<ReclamationUtilisateur> afficherReclamationUser() {
        List<ReclamationUtilisateur>  listru = new ArrayList<ReclamationUtilisateur>();
         String req="SELECT reclamation.idr,reclamation.contenu,reclamation.daterec,reclamation.idRaison,reclamation.etat,user.id,user.nom,user.prenom,user.username,user.email,user.num_tel,user.password From reclamation left join user on reclamation.id=user.id";
         try {
             Statement st = cnx.createStatement();
             
             ResultSet rs = st.executeQuery(req);
             while (rs.next())
             {
                ReclamationUtilisateur ru= new ReclamationUtilisateur(rs.getInt("idr"),rs.getString("contenu"),rs.getInt("id"),rs.getString("daterec"),rs.getInt("idRaison"),rs.getString("nom"),rs.getString("prenom"),rs.getString("username"),rs.getString("email"),rs.getInt("num_tel"),rs.getString("password"),rs.getString("etat"));
                listru.add(ru);
                
                
             }
             
             
         } catch (SQLException ex) {
             ex.printStackTrace();// twarik mochkla win//
         }
         return listru;
        
    }

    @Override
    public boolean ajouterReclamationUser(Reclamation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getiduser(String s) {
         int id = 0;

        String req="select id from user where password='"+s+"'";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
               id=rs.getInt("id");
               
                
            }
        

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        
        
     return id;
    }

    @Override
    public List<ReclamationUtilisateur> trierReclamationUtilisateur() {
       List<ReclamationUtilisateur> reclamations=afficherReclamationUser();
          List<ReclamationUtilisateur> sortedCateg =reclamations.stream().sorted(Comparator.comparing(ReclamationUtilisateur::getIdRaison)).collect(Collectors.toList());
         return sortedCateg;
    }

    @Override
    public List<ReclamationUtilisateur> chercherReclamationUse(String s) {
     ReclamationUtilisateur recu;
        List<ReclamationUtilisateur> reclamations=afficherReclamationUser();
         List<ReclamationUtilisateur> resultat=reclamations.stream().filter(ReclamationUtilisateur->s.equals(ReclamationUtilisateur.getUsername())).collect(Collectors.toList());
        return resultat;
    }

   
    
     
      
     
}

