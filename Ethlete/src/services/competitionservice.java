/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import models.Competition;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Journe;
import util.DataSource;
/**
 *
 * @author gaming
 */
public class competitionservice implements iservice<Competition>{
Connection cnx;
     public competitionservice() {
       cnx= DataSource.getInstance().getCnx();
    }
         @Override
    public void ajouter(Competition t) {
        
            try {
          
           String query="INSERT INTO competition(nb_equipe,date,adresse,nom) values(?,?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setInt(1, t.getNb_equipe());  
                smt.setString(2, t.getDate());
                    smt.setString(3, t.getAdresse());

                smt.setString(4, t.getNom());
                

                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }
    
    
    
    @Override
    public void modifier(Competition t) {
         try {
       String query2="UPDATE `competition` SET  `nb_equipe` = ?, `date` = ?, `adresse` = ?, `nom` = ? WHERE `competition`.`id_competition` = ?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                 smt.setInt(1, t.getNb_equipe());  
                smt.setString(3, t.getDate());
                    smt.setString(4, t.getAdresse());

                smt.setString(2, t.getNom());
                smt.setInt(5, t.getId_competition());
                
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    @Override
    public void supprimer(Competition t) {
         try {
       String query2="delete from competition where id_competition=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_competition());
               
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    @Override
    public List<Competition> find() {
     List<Competition> list = new ArrayList<>();        
        try {
       String query2="select * from competition";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Competition p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Competition(rs.getInt("id_competition"),rs.getInt("nb_equipe"),rs.getString("date"),rs.getString("adresse"),rs.getString("nom"));
                System.out.println(p);
                  
                   list.add(p);
                }
                System.out.println(list);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return list;
    
}
         public Competition findByID_inv(int id_competition){
        List<Competition> competition=find();
        Competition resultat=competition.stream().filter(i->id_competition==i.getId_competition()).findFirst().get();
        return resultat;    
    }
    public TreeSet<Competition> sortByEtat(){
          TreeSet<Competition> tr=  this.find().stream().collect(Collectors.toCollection(() -> new TreeSet<>((f1, f2) -> f1.getNom().compareTo(f2.getNom()))));
          return tr;
}
 public ObservableList<Competition> getlist1() {
        ArrayList l=new ArrayList(); 
        
        try { String query2="select * from competition";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Competition p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Competition(rs.getInt("id_competition"),rs.getInt("nb_equipe"),rs.getString("date"),rs.getString("adresse"),rs.getString("nom"));
                System.out.println(p);
                  
                   l.add(p);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
        return FXCollections.observableList(l);

        
    
}
  public void pdfs() throws Exception{
        try {
           
            Statement stmt = cnx.createStatement();
                    /* Define the SQL query */
                    ResultSet query_set = stmt.executeQuery("SELECT *From competition");
                    /* Step-2: Initialize PDF documents - logical objects */
                    Document my_pdf_report = new Document();
                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("azer.pdf"));
                    my_pdf_report.open();            
                    //we have four columns in our table
                    PdfPTable my_report_table = new PdfPTable(4);
                    //create a cell object
                    PdfPCell table_cell;

                    while (query_set.next()) {                
                                    Integer nb_equipe = query_set.getInt("nb_equipe");
                                    table_cell=new PdfPCell(new Phrase(nb_equipe));
                                    my_report_table.addCell(table_cell);
                                    String nom=query_set.getString("nom");
                                    table_cell=new PdfPCell(new Phrase(nom));
                                    my_report_table.addCell(table_cell);
                                    String date=query_set.getString("date");
                                    table_cell=new PdfPCell(new Phrase(date));
                                    my_report_table.addCell(table_cell);
                                    String adresse=query_set.getString("adresse");
                                    table_cell=new PdfPCell(new Phrase(adresse));
                                    my_report_table.addCell(table_cell);
                                    }
                    /* Attach report table to PDF */
                    my_pdf_report.add(my_report_table);  
                    
                    my_pdf_report.close();

                    /* Close all DB related objects */
                    query_set.close();
                    stmt.close(); 



    } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    



}
       }
  
   public List<Competition> display() {
        List<Competition> compets = new ArrayList<>();
        
        try {
        String query = "select * from competition";
        Statement st = null;
        
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Competition f = new Competition(rs.getInt(1));
                
                f.setNb_equipe(rs.getInt(2));
                f.setDate(rs.getString(3));
                f.setAdresse(rs.getString(4));
                f.setNom(rs.getString(5));
                
                compets.add(f);
            }
                return compets;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    
    public Competition retrieveCompetition(int id_competition){
        Competition compet = null;
        
        try {
        String query = "select * from competition where id_competition = "+id_competition+"";
        Statement st = null;
        
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Competition c = new Competition(rs.getInt(1));
                
                c.setNb_equipe(rs.getInt(2));
                c.setDate(rs.getString(3));
                c.setAdresse(rs.getString(4));
                c.setNom(rs.getString(5));
                
                compet = c;
            }
                
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return compet;

    }
  
}
