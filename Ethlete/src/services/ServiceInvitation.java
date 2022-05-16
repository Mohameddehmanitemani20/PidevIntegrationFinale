/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IService1;
import models.Invitation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.User;
import models.Utilisateur;
import util.DataSource;

/**
 *
 * @author anasl
 */
public class ServiceInvitation implements IService1 <Invitation> {
    Connection cnx;

    public ServiceInvitation() {
        cnx=DataSource.getInstance().getCnx();
    }
    public List<Invitation> afficherpares(int ideq)
    {
    
   return this.afficher().stream().filter(e->e.getId_eq()==ideq).collect(Collectors.toList());
    }

    @Override
    public void ajouter(Invitation t) {
         try {
            Statement st;
            st=cnx.createStatement();
         
            String query1="INSERT INTO `invitation`( `etat`,`id_eq`,`id_joueur`) VALUES ('"+t.getEtat()+"','"+t.getId_eq()+"','"+t.getId_joueur()+"')";
            
            st.executeUpdate(query1);
            System.out.println("invitation ajoutée avec success");
            
        
             try {     ServiceEquipe eq=new ServiceEquipe();
            User resp=eq.find_responsable((int)t.getId_eq());
                        String to = resp.getEmail();

            Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication("anas.laamiri@esprit.tn","211JMT1901");  
           }    
          });    
  
   //Compose the message  
    
    MimeMessage message = new MimeMessage(session);       
                 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
                  message.setSubject("Nouvelle Affectation chez Ethlete");    
           message.setText("Bonjour madame/mr"+resp.getNom()+"Bonjour,vous êtes invités à consulter votre compte vous avez une nouvelle invitation "
                   + "réponse\n Merci");    
           //send message  
           Transport.send(message);  
                   System.out.println("message sent successfully");    
  
     System.out.println("message sent successfully...");  
             } 
             
             catch (MessagingException mex) {mex.printStackTrace();}  
             
          
            
   
          
          
            
            
            
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceInvitation.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }}

    @Override
    public void modifier(long id_amodifier, Invitation t) {
         try {
            PreparedStatement st;
            st=cnx.prepareStatement(
                    "UPDATE `invitation` SET `id_invitation`=?,`id_joueur`=? WHERE id=?");
            st.setInt (1,(int) t.getId_eq());
            st.setInt (2,(int) t.getId_joueur());
            st.setLong(3, id_amodifier);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInvitation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void accepter(long id_amodifier) {
         try {
            PreparedStatement st;
            st=cnx.prepareStatement(
                    "UPDATE `invitation` SET `etat`=? WHERE id_invitation=?");
            st.setString(1,"accepté");
            st.setInt (2,(int)id_amodifier);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInvitation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           public void refuser(long id_amodifier) {
         try {
            PreparedStatement st;
            st=cnx.prepareStatement(
                    "UPDATE `invitation` SET `etat`=? WHERE id_invitation=?");
            st.setString(1,"refusé");
            st.setInt (2,(int)id_amodifier);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInvitation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void supprimer(long id_invitation) {
 try {
            Statement st=cnx.createStatement();
            String query="delete from invitation where id_invitation="+id_invitation;
            st.executeUpdate(query);
            System.out.println("suppression avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInvitation.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Invitation> afficher() {
 List<Invitation> lu=new ArrayList<>();
        try {
            
            Statement st=cnx.createStatement();
            String query="select * from invitation";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Invitation i =new Invitation();
                i.setId_invitation(rs.getInt("id_invitation"));
                i.setId_eq(rs.getInt("id_eq"));
                i.setId_joueur(rs.getInt("id_joueur"));
                i.setEtat(rs.getString("etat"));
                lu.add(i);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    
    }     
    public Invitation findByID_inv(int id_invitation){
        List<Invitation> invitation=afficher();
        Invitation resultat=invitation.stream().filter(i->id_invitation==i.getId_invitation()).findFirst().get();
        return resultat;
    }
    public TreeSet<Invitation> sortByEtat(){
          TreeSet<Invitation> tr=  this.afficher().stream().collect(Collectors.toCollection(() -> new TreeSet<>((f1, f2) -> f1.getEtat().compareTo(f2.getEtat()))));
          return tr;
        
     }
    
}
