/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.Rating;
import util.DataSource;

/**
 *
 * @author gaming
 */
public class ratingservice implements iservice<Rating>{
    Connection cnx;
     public ratingservice() {
         cnx = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Rating t) {try {
          
           String query="INSERT INTO `review` (`review`, `id`, `id_match`) VALUES (?,?,?);";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setInt(1, t.getReview());
                smt.setInt(2, t.getId());
                smt.setInt(3, t.getId_match());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }
    @Override
     public void modifier(Rating t) {try {
       String query2="UPDATE `match` SET `review` = ?, WHERE `id_match`  = ? `id`  = ? ;";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setInt(1, t.getReview());
                smt.setInt(2, t.getId_match());
                smt.setInt(3, t.getId());                              
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public void supprimer(Rating t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rating> find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public float stat(int id_match){
        try {
       String query2="SELECT avg(review) as stat FROM `review` where id_match = ?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, id_match);
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   
                   return rs.getFloat("stat");
         
                }
            } catch (SQLException ex) {
                return -1;
    }
return -1;
    }
    }
    
