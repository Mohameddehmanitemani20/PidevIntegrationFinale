/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import util.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import models.AffectationFormateur;
import models.Formation;
import models.Impression;
import models.Utilisateur;
import interfaces.IService;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import lombok.NoArgsConstructor;

/**
 *
 * @author pc
 */
@NoArgsConstructor
public class ServiceFormation implements IService <Formation> {
    Connection cnx = DataSource.getInstance().getCnx();
		
    @Override
    public void ajouter(Formation t) {
       PreparedStatement pst=null;
            try {
             
    
            String req1 = "INSERT INTO Formation (nom_formation,date_debut,date_fin,dispositif,programme) VALUES (?,?,"
                    + "?,?,?)";
   pst = cnx.prepareStatement(req1);           
   pst.setString(1, t.getNom_formation());

              pst.setDate(2,   t.getDate_debut());          
            pst.setDate(3,   t.getDate_fin());

            pst.setString(4, t.getDispositif());
            pst.setString(5,t.getProgramme());
          
           
            pst.executeUpdate();
           
			
			
		
            System.out.println("Formation bien ajoutée !");
            }	
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  /*finally {			
                  try {
                      if (input != null) {
                          try {
                              input.close();
                          } catch (IOException ex) {
                              Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
                          }
                      }
                      			
                     cnx.close();pst.close();
                  } catch (SQLException ex) {
                      Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
                  }
		}*/
    }
    
    

    @Override
    public void supprimer(Formation t) {

 try {
            String req = "DELETE FROM formation WHERE id_formation=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId_formation());
            pst.executeUpdate();
            System.out.println("Formation suprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void modifier(Formation t) {
        		

  try { 

                
    
            String req = "UPDATE formation SET nom_formation=?,date_debut=?,date_fin=?,dispositif=?,programme=? WHERE id_formation=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(6, t.getId_formation());
             pst.setString(1, t.getNom_formation());
            pst.setDate(2,   t.getDate_debut());          
           
            
            pst.setDate(3,   t.getDate_fin());

            pst.setString(4, t.getDispositif());
            pst.setString(5,t.getProgramme());
            pst.executeUpdate();
            System.out.println("Formation modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
//afficher toute les formations
    @Override
    public List<Formation> afficher() {
List<Formation> list = new ArrayList<>();
        
        
        try{    String req = "SELECT id_formation,nom_formation,date_debut,date_fin,dispositif,programme FROM formation ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Formation(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6)));
                
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;    }
    //télécharger programme 
   /* public void telecharger_programme(int id )
    {
    	
PreparedStatement pst =null;
		InputStream input = null;
		FileOutputStream output = null;
                   
    

		try {
                    
			
			String req = "select programme from formation where id_formation=?";
 pst = cnx.prepareStatement(req);
             pst.setInt(1,id);

            ResultSet rs = pst.executeQuery();	
         
			File theFile = new File("programme.pdf");
			output = new FileOutputStream(theFile);

			if (rs.next()) {

System.out.print("avant");
				input = rs.getBinaryStream("programme"); 
				System.out.println("En train de lecture du programme...");
				
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				
                                }
								
			}
System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
				
				System.out.println("\nCompleted successfully!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (output != null) {
                        try {
                            output.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
		}
	}*/

	public Formation consulter_formation(int id)
        {Formation f =new Formation();
        
	f=this.afficher().stream().filter(e->e.getId_formation()==id).findFirst().get();
                return f;
        }
    public Formation consulter_formation(String id)
        {Formation f =new Formation();
        
	f=this.afficher().stream().filter(e->e.getNom_formation().equals(id)).findFirst().get();
                return f;
        }
           public List<Formation> trier_date_debut() {
            List<Formation> list = new ArrayList<>();

            list=this.afficher();
                return list.stream()
                .sorted((a, b) -> a.getDate_debut().compareTo(b.getDate_debut()))
               .collect(Collectors.toList());
            
            
        }
           
           	public void afficher_formation(int id)
                { List<Formation> list = new ArrayList<>();

            list=this.afficher();
                 list.stream()
                .filter(f->f.getId_formation()==id).forEach(System.out::println);
                }
        
                
      /*  public void imprimer_programme()
        {
          PrinterJob job = PrinterJob.getPrinterJob();
      HashPrintRequestAttributeSet printRequestSet = new HashPrintRequestAttributeSet();
      
      printRequestSet.add(OrientationRequested.LANDSCAPE);
      
      job.setPrintable(new Impression());
      if (job.printDialog(printRequestSet)){
         try {
            job.print();
         } catch (PrinterException ex) {
            ex.printStackTrace();
         }
      }
        }*/
        public Formation rechercher_formation(Formation f)
        {return this.afficher().stream().filter(s->s.getId_formation()==f.getId_formation()).findFirst().get();}
         public void catalogue()
         {List<Formation> list = new ArrayList<>();
list=this.afficher();
 try{              FileWriter fw=new FileWriter("C:\\Users\\pc\\OneDrive\\Bureau\\catalogue.txt");    
fw.write("Bienvenue au catalogue des formation\n");
     
     for(Formation e:list)
       
     
     {    fw.write("\n"+e.toString());
              }fw.close(); 
          }catch(Exception e){System.out.println(e);}    
          System.out.println("Success...");             
         }
                public TreeSet<Formation> trier_par_nom() {
           
TreeSet<Formation> list = this.afficher().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>((f1, f2) -> f1.getNom_formation().compareTo(f2.getNom_formation()))));
         
            return list;
            
        }
                         public List<Formation> filtrer_form_En_Ligne() {
            List<Formation> list = new ArrayList<>();

            list=this.afficher();
                return list.stream()
               .filter(f->f.getDispositif().equals("En_Ligne"))
               .collect(Collectors.toList());
            
            
        }
                                        public List<Formation> filtrer_form_Presentiel() {
            List<Formation> list = new ArrayList<>();

            list=this.afficher();
                return list.stream()
               .filter(f->f.getDispositif().equals("En_Ligne")==false)
               .collect(Collectors.toList());
            
            
        }
         public void generer_pdf(Formation f)
         {
         try {
             
             
             
             String FILE = "C:\\Users\\MSI\\Desktop\\PI\\Ethlete\\catalogue"+f.getNom_formation()+".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
             document.add(new Paragraph("Bienvenue à la formation "+f.getNom_formation()));
                 document.add(new Paragraph("Vous trouverz ci-joint le programme de cette formation du"+f.getDate_debut()+"à"+f.getDate_fin()));
            document.add(new Paragraph(f.getProgramme()));
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         }
             public List<Formation> rechercherFormation(List<Formation> initialList, String input) {
    List<Formation> strList = initialList.stream()
                           .map( Formation::concat )
                           .filter(pt -> pt.toLowerCase().contains(input.toLowerCase()))
                           .map(pt -> new Formation(Integer.parseInt(pt.split("/@/")[0]),pt.split("/@/")[1],    Date.valueOf(pt.split("/@/")[2])
,    Date.valueOf(pt.split("/@/")[3])
,pt.split("/@/")[4],pt.split("/@/")[5]))
                           .collect( Collectors.toList() );
        
        return strList;
    }    
         
      public List<Formation> display() {
        List<Formation> formations = new ArrayList<>();
        
        try {
        String query = "select * from formation";
        Statement st = null;
        
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Formation f = new Formation(rs.getInt(1));
                
                f.setNom_formation(rs.getString(2));
                f.setDate_debut(rs.getDate(3));
                f.setDate_fin(rs.getDate(4));
                f.setDispositif(rs.getString(5));
                f.setProgramme(rs.getString(6));
                
                formations.add(f);
            }
                return formations;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    
    public Formation retrieveFormation(int id_formation){
        Formation formation = null;
        
        try {
        String query = "select * from formation where id_formation = "+id_formation+"";
        Statement st = null;
        
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Formation f = new Formation(rs.getInt(1));
                
                f.setNom_formation(rs.getString(2));
                f.setDate_debut(rs.getDate(3));
                f.setDate_fin(rs.getDate(4));
                f.setDispositif(rs.getString(5));
                f.setProgramme(rs.getString(6));
                
                formation = f;
            }
                
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return formation;

    }  
     public Formation getbynom(String s)
     {
     return this.afficher().stream().filter(e->e.getNom_formation().equals(s)).findFirst().get();
     }
    
}
