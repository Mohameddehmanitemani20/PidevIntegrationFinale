/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import util.DataSource;
import models.Commentaire;
import models.Formation;
import models.Participation;
import models.Utilisateur;
import interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pc
 */
public class ServiceCommentaireImp implements IService <Commentaire> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commentaire t) {
   PreparedStatement pst=null;
            try {
             
    
            String req1 = "INSERT INTO commentaire (id_formation,id_participant,contenu) VALUES (?,?,"
                    + "?)";
   pst = cnx.prepareStatement(req1);           
   pst.setInt(1,t.getId_form());
   pst.setInt(2,t.getId_user());

              pst.setString(3,t.getComments());          
            
          
           
            pst.executeUpdate();
           
			
			
		
            System.out.println("Commentaire bien ajoutée !");
            }	
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(Commentaire t) {

 try {
            String req = "DELETE FROM commentaire WHERE id_commentaire=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId_comment());
            pst.executeUpdate();
            System.out.println("Commentaire suprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void modifier(Commentaire t) {
 try { 

                
    
            String req = "UPDATE formation SET contenu=?,id_formation=?,id_participant WHERE id_commentaire=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(2, t.getId_form());
                  pst.setInt(3, t.getId_user());
                        pst.setInt(4, t.getId_comment());
                  
             pst.setString(1, t.getComments());
          
            pst.executeUpdate();
            System.out.println("Commentaire modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Commentaire> afficher() {

List<Commentaire> list = new ArrayList<>();
        
        
        try{    String req = "SELECT *FROM commentaire ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Commentaire(rs.getInt(1),rs.getString(4),rs.getInt(3),rs.getInt(2)));
                
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;  

    }
    public List<Commentaire> filtrer_par_formation(Formation f)
    { List<Commentaire> list =new ArrayList();
    
    list=this.afficher().stream().filter(c->c.getId_form()==f.getId_formation()).collect(Collectors.toList());
    return list;
    
    }
    
}
