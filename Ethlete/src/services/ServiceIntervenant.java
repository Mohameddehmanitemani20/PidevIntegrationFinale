/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.util.ArrayList;
import interfaces.I_intervenant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import models.Intervenant;
import models.Produit;
import util.DataSource;

/**
 *
 * @author 21624
 */
public class ServiceIntervenant implements I_intervenant{
   Connection     cnx = DataSource.getInstance().getCnx();

    @Override
    public boolean ajouterIntervenant(Intervenant I) {
        String request ="INSERT INTO `intervenant`(`image_In`,`nom`, `prenom`, `email`, `telephone` , `id_typeint`) VALUES ('"+I.getImage_In()+"','"+I.getNom()+"','"+I.getPrenom()+"','"+I.getEmail()+"',"+I.getTelephone()+" ,'"+I.getId_typeint()+"')";
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
    public List<Intervenant> afficherIntervenant() {
         List<Intervenant> intervenants = new ArrayList<Intervenant>();

        String req="SELECT * FROM intervenant";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                intervenants.add(new Intervenant(rs.getInt("id_inter"),
                        rs.getString("image_In"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),rs.getInt("telephone"),rs.getString("id_typeint")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return intervenants; 
    }
    

    @Override
    public boolean modifierIntervenant(Intervenant I) {
           String req = "UPDATE `intervenant` SET  `nom`='"+I.getNom()+"',`prenom`='"+I.getPrenom()+"',`email`='"+I.getEmail()+"',`telephone`="+I.getTelephone()+"WHERE id_inter = "+I.getId_inter()+"";
        try {
           
     PreparedStatement st;
            st=cnx.prepareStatement(
                    "UPDATE `intervenant` SET `nom`=?,`prenom`=?,`email`=?,`telephone`=? WHERE id_inter=?");
             st.setString(1, I.getNom());             st.setString(2, I.getPrenom());
             st.setString(3, I.getEmail());

                        st.setInt(4, I.getTelephone());                        st.setInt(5, I.getId_inter());

            st.executeUpdate();
return true;
// Statement st = cnx.createStatement();
       
     
        } catch (SQLException e) {
            e.printStackTrace();
       
        }
             return false;
    }

    @Override
    public boolean supprimerIntervenant(Intervenant I) {
String req = "DELETE FROM `intervenant` WHERE id_inter = "+I.getId_inter()+" ";

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
    public List<Intervenant> rechercherIntervenant(List<Intervenant> initialList, String input) {
          List<Intervenant> inter=new ArrayList();
          inter= initialList.stream()
                  .map( Intervenant::concat )
                  .filter(pt -> pt.toLowerCase().contains(input.toLowerCase()))
                  .map(pt -> new Intervenant(pt.split(".@.")[0],pt.split(".@.")[1],pt.split(".@.")[2],Integer.parseInt(pt.split(".@.")[3])))
                  .collect( Collectors.toList() );
        
        return inter;   
    }    

    
    
     public List<Intervenant> sortByNom(){
         List<Intervenant> intervenant=afficherIntervenant();
         List<Intervenant> resultat = intervenant.stream()
                                                 .sorted(Comparator.comparing(Intervenant::getNom))
                                                 .collect(Collectors.toList());
         return resultat;
     }

   
   public List<Intervenant> display() {
        List<Intervenant> intervenants = new ArrayList<>();
        
        try {
        String query = "select * from intervenant";
        Statement st = null;
        
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Intervenant I = new Intervenant(rs.getInt(1));
                
                I.setImage_In(rs.getString(2));
                System.out.println("test");
                I.setNom(rs.getString(3));
                                System.out.println("test1");

                I.setPrenom(rs.getString(4));
                                System.out.println("tes2");

                I.setEmail(rs.getString(5));
                                System.out.println("test3");

                I.setTelephone(rs.getInt(6));
                                System.out.println("test4");

                I.setId_typeint(rs.getString(7));

                
                intervenants.add(I);
            }
                return intervenants;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}

    

    

