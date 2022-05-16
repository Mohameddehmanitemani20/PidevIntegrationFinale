/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import models.Journe;
import models.Match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author gaming
 */
public class journeservice implements iservice<Journe>{
    Connection cnx;
     public journeservice() {
         cnx = DataSource.getInstance().getCnx();
    }
         @Override
    public void ajouter(Journe t) {
        
            try {
          
           String query="INSERT INTO journe(numJourne,date_journe,id_competition) values(?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setInt(1, t.getNumJourne());
                smt.setString(2, t.getDate_journe());
                smt.setInt(3, t.getId_competition());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }
    
    
    
    @Override
    public void modifier(Journe t) {
         try {
       String query2="UPDATE `journe` SET `numJourne` = ?, `date_journe` = ? WHERE `journe`.`id_journe` = ?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setInt(1, t.getNumJourne());
                smt.setString(2, t.getDate_journe());                
                smt.setInt(3, t.getId_journe());
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    @Override
    public void supprimer(Journe t) {
         try {
       String query2="DELETE FROM `journe` WHERE `journe`.`id_journe` = ?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_journe());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    @Override
    public List<Journe> find() {
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from journe";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Journe p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Journe(rs.getInt("Id_journe"),rs.getInt("numJourne"),rs.getString("date_journe"));
                System.out.println(p);
                  
                   l.add(p);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    
}
 public Journe findByID_inv(int id_journe){
        List<Journe> invitation=find();
        Journe resultat=invitation.stream().filter(i->id_journe==i.getId_journe()).findFirst().get();
        return resultat;
    }
 public ObservableList<Journe> getlist() {
        ArrayList l=new ArrayList(); 
        
        try { String query2="select * from journe";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Journe p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Journe(rs.getInt("Id_journe"),rs.getInt("numJourne"),rs.getString("date_journe"));
                System.out.println(p);
                  
                   l.add(p);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
        return FXCollections.observableList(l);

        
    
}
   public List<Journe> findByCompId(int id) {
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from journe where id_competition = ?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                                smt.setInt(1, id);

                Journe p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Journe(rs.getInt("Id_journe"),rs.getInt("numJourne"),rs.getString("date_journe"));
                  
                   l.add(p);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    
}
}
