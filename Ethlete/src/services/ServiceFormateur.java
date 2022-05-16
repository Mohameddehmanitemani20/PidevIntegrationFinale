/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import util.DataSource;
import models.Formation;
import models.Utilisateur;
import interfaces.IService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Role;
import models.User;
import static services.CryptWithMD5.cryptWithMD5;

/**
 *
 * @author pc
 */
public class ServiceFormateur implements IService<User> {

    public ServiceFormateur() {
    }

    Connection cnx = DataSource.getInstance().getCnx();

    /* @Override
    public void ajouter(Utilisateur t) {
   try {String s="ROLE_FORMATEUR";
        Random rand = new Random();
   
String alphabet = "abcd12345#/";
String pswd="";
int longueur = alphabet.length();
for(int i = 0; i < 12; i++) {
  int k = rand.nextInt(longueur);
 
  pswd=pswd+alphabet.charAt(k);
}


       t.setPassword(pswd);
       t.setUsername(t.getEmail());
     
       
            String req = "SELECT id from roles  where nom_role like '"+s+"'";
            PreparedStatement pst1 = cnx.prepareStatement(req);
            ResultSet rs = pst1.executeQuery();
              while(rs.next()) {
t.setRole(rs.getInt(1));                
            
            }
           

String req1 = "INSERT INTO utilisateur (nom, prenom,adresse,email,telephone,date_naissance,username,password,role) VALUES (?,?,"
                    + "?,?,?,?,?,?,?)";
                    PreparedStatement pst = cnx.prepareStatement(req1);

           cnx.prepareStatement(req1);
          
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getAdresse());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getTelephone());
            pst.setString(6,  t.getDate_naissance());
            pst.setString(7, t.getEmail());
            pst.setString(8, t.getPassword());
            pst.setString(9, t.getRole());
            pst.executeUpdate();
            System.out.println("Formateur bien ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(Utilisateur t) {
          try {
            String req = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Personne suprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     */
//list de tous les formateurs
    @Override
    public List<User> afficher() {
        List<User> formateurs = new ArrayList<>();

        String s = "[\"ROLE_FORMATEUR\"]";
  String s1="";
          String[] words = s.split("");
for(int i=0;i<s.length();i++)
{if (s.charAt(i)=='[' || s.charAt(i)==']'|| s.charAt(i)=='\"'   )
{s1=s1;}
else
{s1=s1+s.charAt(i);
}


}

//final String s3=s1;
        formateurs = this.afficher_part().stream().filter(e -> e.getRole()==Role.ROLE_FORMATEUR).collect(Collectors.toList());

        return formateurs;
    }

    @Override
    public void modifier(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //consulterformateur
    /*public User consulte_formateur(int id) {
        User u = new User();
        u = this.afficher().stream().filter(e -> e.getId() == id).findFirst().get();
        return u;
    }*/
    
    public User consulte_formateur(int id){
         User u =new User();
          try {
            Statement st=cnx.createStatement();
            String query="select * from user where id="+id;
            ResultSet rs=st.executeQuery(query);
               String s;
               String s1="";
            if(rs.next()){
                
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
              //  u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
             
         s=rs.getString("roles");
          String[] words = s.split("");
for(int i=0;i<s.length();i++)
{if (s.charAt(i)=='[' || s.charAt(i)==']'|| s.charAt(i)=='\"'   )
{s1=s1;}
else
{s1=s1+s.charAt(i);
}


}
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(s1));
                                u.setGenre(rs.getString("genre"));

            }else{
                System.out.println("user n existe pas");
                
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        
    }
    
    

    public User findbyId(int id) {
        return this.afficher_part().stream().filter(e -> e.getId() == id).findFirst().get();
    }

    public List<User> afficher_part() {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user";
            String s;
            String s1="";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                         s1="";
                User u = new User();
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
//                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));

                  s=rs.getString("roles");
          String[] words = s.split("");
for(int i=0;i<s.length();i++)
{if (s.charAt(i)=='[' || s.charAt(i)==']'|| s.charAt(i)=='\"'   )
{s1=s1;}
else
{s1=s1+s.charAt(i);
}


}                  u.setRole(Role.valueOf(s1));

                u.setUsername(rs.getString("username"));
                u.setGenre(rs.getString("genre"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    @Override
    public void ajouter(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
