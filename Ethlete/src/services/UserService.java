/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IService1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import models.Role;
import models.User;
import static services.CryptWithMD5.cryptWithMD5;
import util.DataSource;



/**
 *
 * @author MSI
 */
public class UserService implements IService1 <User> {
    Connection cnx;
    
    public UserService(){
        cnx=DataSource.getInstance().getCnx();
        
    }

    @Override
    public void ajouter(User t) {
        try {
            Statement st;
            st=cnx.createStatement();
 String s ="["+ "\""+t.getRole().toString()+"\""+"]";
      
            String query="INSERT INTO `user`( `adresse`, `date_naissance`, `email`, `nom`, `num_tel`, `password`, `prenom`, `roles`, `username`, `genre`,`is_verified`) "
                    + "VALUES ('"+t.getAdresse()+"','"+t.getDate_naissance()+"','"+t.getEmail()+"','"+t.getNom()+"','"+t.getNumTel()+"','"+cryptWithMD5(t.getPassword())+"','"+t.getPrenom()+"','"+s+"','"+t.getUsername()+"','"+t.getGenre()+"','"+1+"')";
            st.executeUpdate(query);
            System.out.println("user ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(long id_amodifier, User t) {
        try {
            PreparedStatement st;
            st=cnx.prepareStatement("UPDATE `user` SET `adresse`=?,`date_naissance`=?,"
                    + "`email`=?,`nom`=?,`num_tel`=?,"
                    + "`password`=?,`prenom`=?,"
                    + "`roles`=?,`username`=? WHERE id=?");
            st.setString(1, t.getAdresse());
            st.setDate(2,new java.sql.Date(t.getDate_naissance().getTime()));
            st.setString(3,t.getEmail());
            st.setString(4,t.getNom());
            st.setInt(5, t.getNumTel());
            st.setString(6,cryptWithMD5( t.getPassword()));
            st.setString(7, t.getPrenom());
            st.setString(8, t.getRole().toString());
            st.setString(9, t.getUsername());
            st.setLong(10, id_amodifier);
            if (st.executeUpdate()==1){
            System.out.println("user modifier avec success");
            }else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(long id) {
        try {
            Statement st=cnx.createStatement();
            String query="delete from user where id="+id;
            if(st.executeUpdate(query)==1){
            System.out.println("suppression avec success");
            }else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<User> afficher() {
        List<User> lu=new ArrayList<>();
        try {
            Statement st=cnx.createStatement();
            String query="select * from user";
            String s;
            String s1="";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                s1="";
                User u =new User();
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


}
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(s1));
                u.setGenre(rs.getString("genre"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    
    public User findById(long id){
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
    
    public List<User> findByName(String name){
        List<User> users=afficher();
        List<User> resultat=users.stream().filter(user->name.equals(user.getNom())).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
        //resultat.forEach(System.out::println);
        return resultat;
    }
    

    public List<User> findByPrenom(String prenom){
        List<User> users=afficher();
        List<User> resultat=users.stream().filter(user->prenom.equals(user.getPrenom())).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }
    
     public List<User> findByRole(Role role){
         List<User> users=afficher();
         List<User> resultat=users.stream().filter(user->role.equals(user.getRole())).collect(Collectors.toList());
         
         if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
         return resultat;
     }
    
     public List<User> findByDate(Date date){
         List<User> users=afficher();
         List<User> resultat=users.stream().filter(user->date.toString().equals(user.getDate_naissance().toString())).collect(Collectors.toList());
         if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
         return resultat;
     }
     
     public List<User> findByEmail(String email){
         List<User> users = afficher();
         List<User> resultat=users.stream().filter(user->email.equals(user.getEmail())).collect(Collectors.toList());
         if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
         return resultat;
     }
     public boolean usernameExist(String username){
        
          try {
            Statement st=cnx.createStatement();
            String query="select * from user where username='"+username+"'";
            ResultSet rs=st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
     }
     public User findByUsername(String username){
         User u=new User();
         try {
            Statement st=cnx.createStatement();
            String query="select * from user where username='"+username+"'";
            ResultSet rs=st.executeQuery(query);
            String s1="";
            String s;
            if(rs.next()){
                         s1="";
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setNumTel(rs.getInt("num_tel"));
               // u.setPassword(cryptWithMD5(rs.getString("password")));
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

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
     }
     
     public List<User> findByNum(int numtel){
         List<User> users=afficher();
         List<User> resultat=users.stream().filter(user->numtel==user.getNumTel()).collect(Collectors.toList());
         if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
         return resultat;
     }
     
     public List<User> findByAdresse(String adresse){
         List<User> users=afficher();
         List<User> resultat=users.stream().filter(user->adresse.equals(user.getAdresse())).collect(Collectors.toList());
         if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
         return resultat;
     }
     
     public List<User> sortByDate(){
         List<User> users=afficher();
         List<User> resultat=users.stream().sorted(Comparator.comparing(User::getDate_naissance)).collect(Collectors.toList());
         return resultat;
         
     }
     
     public List<User> sortById(){
         List<User> users=afficher();
         List<User> resultat=users.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
         return resultat;
     }
     
     public List<User> sortByNom(){
         List<User> users=afficher();
         List<User> resultat=users.stream().sorted(Comparator.comparing(User::getNom)).collect(Collectors.toList());
         return resultat;
     }
     
     public boolean checklogin(String username,String password){
        try {
            Statement st=cnx.createStatement();
            String query="SELECT * FROM `user` WHERE `username`='"+username+"' AND `password`='"+password+"'";
            ResultSet rs=st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
     }
     
     
     
     
     
    
     
    

     }


     
         
     
     
    
     
     
    

