/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IService1;
import models.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.User;
import models.Utilisateur;
import util.DataSource;

/**
 *
 * @author anasl
 */
public class ServiceEquipe implements IService1 <Equipe> {
     Connection cnx;

    public ServiceEquipe() {
        cnx=DataSource.getInstance().getCnx();
    }
    

    @Override
    public void ajouter(Equipe t) {
       try {
            Statement st;
            st=cnx.createStatement();
           // String query="SELECT * FROM `utilisateur` , `equipe` WHERE id=id_responsable ";
            String query1="INSERT INTO `equipe`( `nom_equipe`, `id_responsable`) VALUES ('"+t.getNom_equipe()+"','"+t.getId_responsable()+"')";
            //st.executeUpdate(query);
            st.executeUpdate(query1);
            System.out.println("equipe  ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(long id_amodifier, Equipe t) {
        try {
            PreparedStatement st;
            st=cnx.prepareStatement(
                    "UPDATE `equipe` SET `nom_equipe`=?,`id_responsable`=? WHERE id_equipe=?");
            st.setString(1, t.getNom_equipe());
            st.setInt(2, (int) t.getId_responsable());
            st.setLong(3, id_amodifier);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void supprimer(long id_equipe) {
        try {
            Statement st=cnx.createStatement();
            String query="delete from equipe where id_equipe="+id_equipe;
            st.executeUpdate(query);
            System.out.println("suppression avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public User find_responsable(int id){
    ServiceResponsable sr = new ServiceResponsable();
    Equipe e = findByID_equipe(id);
    User res = sr.afficher_part().stream().filter(s->s.getId()==e.getId_responsable()).findFirst().get();
    return res;

    }

    @Override
    public List<Equipe> afficher() {
         List<Equipe> lu=new ArrayList<>();
        try {
            
            Statement st=cnx.createStatement();
            String query="select * from equipe";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Equipe u =new Equipe();
                u.setId_equipe(rs.getInt("id_equipe"));
                u.setNom_equipe(rs.getString("nom_equipe"));
                u.setId_responsable(rs.getInt("id_responsable"));
                lu.add(u);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    
    } 
    public Equipe findBynom_equipe(String nom_equipe){
        List<Equipe> equipes=afficher();
        Equipe resultat=equipes.stream().filter(equipe->nom_equipe.equals(equipe.getNom_equipe())).findFirst().get();
        return resultat;
    }
      public Equipe findByID_equipe(int id){
        
        return afficher().stream().filter(e->e.getId_equipe()==id).findFirst().get();
    }
    
    public List<Equipe> sortByNom(){
         List<Equipe> equipes=afficher();
         List<Equipe> resultat=new ArrayList();
              return  equipes.stream().sorted((a, b) -> a.getNom_equipe().compareTo(b.getNom_equipe()))
               .collect(Collectors.toList());
     }

public boolean existe(String nom)
{
return this.afficher().stream().filter(e->e.getNom_equipe().equals(nom)).findFirst().isPresent();

}
    public List<Equipe> paginateEquipes(int pageNum, int pageSize) {

    int SKIP_COUNT = (pageNum - 1) * pageSize;

    int LIMIT_COUNT = 0;

    try {

        LIMIT_COUNT = pageSize / SKIP_COUNT;

    } catch (ArithmeticException e) {
        LIMIT_COUNT = 5;
    }

    return afficher()
        .stream()
        .skip(SKIP_COUNT)
        .limit(LIMIT_COUNT).collect(Collectors.toList());
    }
    }
    

   
