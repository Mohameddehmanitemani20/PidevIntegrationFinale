/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import util.DataSource;
import models.AffectationFormateur;
import models.Formation;
import models.Impression;
import models.Participation;
import models.Utilisateur;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import lombok.NoArgsConstructor;
import models.User;

/**
 *
 * @author pc
 */@NoArgsConstructor

public class ServiceParticipation {
      Connection cnx = DataSource.getInstance().getCnx();
    public void Participer_Une_Formation(Formation f  , User u){
        
         try {
            String req = "INSERT INTO participation (id_participant,formation_id,date_participation) VALUES (?,?,?)";
                                PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, (int) u.getId());
                       pst.setInt(2,f.getId_formation());
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY" );
    SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");

    long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        System.out.println(date);
       
 String str="205-03-31";  
    //Date date=Date.valueOf(formattedDate);
                           pst.setDate(3,date);

            pst.executeUpdate();
            System.out.println("Participation ajoutée !"+u.getNom());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    
    public void annuler_participation_précise(User f,Formation f1)
    {try {
             String req = "DELETE FROM participation WHERE id_participant=? and formation_id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, (int) f.getId());            pst.setInt(2,f1.getId_formation());

            pst.executeUpdate();
            System.out.println("Participation supprimée de  !"+f.getNom());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public List<Utilisateur> consulter_participants(Formation f) {
           List<Utilisateur> list = new ArrayList<>();
        
        
        try{    String req = "SELECT * FROM User where id in (select id_participant"
                + "from participation where formation_id=?) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,f.getId_formation());
            ResultSet rs = pst.executeQuery();
           while(rs.next()) {
                    list.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(7),rs.getString(5),rs.getString(6)
                ,rs.getString(10),rs.getString(4),rs.getString(11))
                
                );
                
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;    }
       public int nb_participant(Formation f)
       {return this.consulter_participants(f).size();
       }
         public List<Formation> consulter_formation(User f) {
           List<Formation> list = new ArrayList<>();
        
        
        try{    String req = "SELECT * FROM formation where id_formation in (select formation_id"
                + "from participation where id_participant=?) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, (int) f.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Formation(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6)));
                
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }return list;
         }
         
         
             public List<Participation> afficher() {
List<Participation> list = new ArrayList<>();
        
        
        try{    String req = "SELECT * FROM participation ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Participation(rs.getInt(2),rs.getInt(3),rs.getTimestamp(4)));
                
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;    }
             
                
              public List<User> chercher_part_formations_Part(Formation f) {
           List<User> list = new ArrayList<>();
           ServiceFormateur sf=new ServiceFormateur();
                List<Integer> formations_id=this.afficher().stream().filter(e->e.getFormation_id()==f.getId_formation()).map(e->e.getId_participant()).collect(Collectors.toList());
for (Integer x: formations_id)
{list.add(sf.findbyId(x));
}
return list;
              }
             
             
              public List<Formation> consulter_formations_Part(User f) {
           List<Formation> list = new ArrayList<>();
           ServiceFormation sf=new ServiceFormation();
                List<Integer> formations_id=this.afficher().stream().filter(e->e.getId_participant()==f.getId()).map(q->q.getFormation_id()).collect(Collectors.toList());
for (Integer x: formations_id)
{list.add(sf.consulter_formation(x));
}
return list;
              }
              
              
              public Map<Formation, List<User>> consulter_particiapnts_par_formation()
              {ServiceFormateur sf1=new ServiceFormateur();
             Map<Integer, List<Participation>> map=this.afficher().stream().collect(Collectors.groupingBy(e -> e.getFormation_id()));
            Map<Formation, List<User>> map1=new HashMap();
                       ServiceFormation sf=new ServiceFormation();


            for(Integer x:map.keySet())
            {
                  List<User> list = new ArrayList<>();
                for(int i=0;i<map.get(x).size();i++)
                
            {list.add(sf1.findbyId(map.get(x).get(i).getId_participant()));}
                    
            map1.put(sf.consulter_formation(x),list);}
            
             
             return map1;
              }
              public List<Participation> trier_par_date()
              {
              return this.afficher().stream()
                       .sorted((a, b) -> a.getDate_participation().compareTo(b.getDate_participation()))
               .collect(Collectors.toList());}
               
              
              public void fichier_participation()
         { Map<Formation, List<User>> map1=new HashMap();
map1=this.consulter_particiapnts_par_formation();
 try{              FileWriter fw=new FileWriter("C:\\Users\\pc\\OneDrive\\Bureau\\partParform.txt");    
fw.write("Bienvenue \n");
     
     for(Formation e:map1.keySet())
       
     
     {    fw.write("\n La formation "+e.toString());
       fw.write("\n La liste des participants à cette formation \n ");
          for (int i=0;i<map1.get(e).size();i++)
            fw.write("\nParticipant:  "+(i+1)+"  "+map1.get(e).get(i));

       
              }fw.close(); 
          }catch(Exception e){System.out.println(e);}    
          System.out.println("Success...");             
         }
           
          public void imprimer_liste_participant_par_form()
        {
          PrinterJob job = PrinterJob.getPrinterJob();
      HashPrintRequestAttributeSet printRequestSet = new HashPrintRequestAttributeSet();
      
      printRequestSet.add(OrientationRequested.LANDSCAPE);
     Impression i= new Impression();
      job.setPrintable(new Impression());
      if (job.printDialog(printRequestSet)){
         try {
            job.print();
            
         } catch (PrinterException ex) {
            ex.printStackTrace();
         }
      }
        }
         
}
