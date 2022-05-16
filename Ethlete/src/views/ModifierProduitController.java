/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import models.Categorie;
import models.Produit;
import models.ProduitCategorie;
import services.ServicesCategorie;
import services.ServicesProduit;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierProduitController implements Initializable {
   ObservableList<ProduitCategorie> listcp = FXCollections.observableArrayList();
    ServicesCategorie scg= new ServicesCategorie(); 
     ServicesProduit sp= new ServicesProduit();
    @FXML
    private TableView<ProduitCategorie> tableprodcateg;
    @FXML
    private TableColumn<ProduitCategorie,String> prodcl;
    @FXML
    private TableColumn<ProduitCategorie,String> categcl;
    @FXML
    private TableColumn<ProduitCategorie,Float> prixcl;
    @FXML
    private TableColumn<ProduitCategorie,Integer> idpcl;
    @FXML
    private TableColumn<ProduitCategorie,Integer> idccl;
    @FXML
    private ComboBox<Categorie> prodcategcombo;
    @FXML
    private TextField prodtf;
    @FXML
    private TextField prixtf;
    @FXML
    private TextField categtf;
    @FXML
    private TextField idptf;
    @FXML
    private TextField idcategtf;
   
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //Fill up my combobox
        

        ObservableList<Categorie> items = FXCollections.observableArrayList();
        scg.afficherNomCategorie().stream().forEach((p)->{items.add(p); });
        
        prodcategcombo.setItems(items);
     
        
        
        
        afficherProduitCategorie();
        
        
        
        
    }    

    @FXML
    private void modifierproduit(ActionEvent event) {
          //fillout my tableview with input
        ProduitCategorie c=new ProduitCategorie();
        c=tableprodcateg.getSelectionModel().getSelectedItem();
         c.setIdp(Integer.parseInt(idptf.getText()));
         c.setPrix(Float.parseFloat( prixtf.getText()));
         c.setNomp(prodtf.getText());
         c.setIdcateg(Integer.parseInt(idcategtf.getText()));
         c.setNomcateg(categtf.getText());
         
        
          clearFields();
         listcp.set(tableprodcateg.getSelectionModel().getFocusedIndex(),c);
         
      
        
        
        //modify command in database
       
        sp.modifierProduit(new Produit(c.getIdp(),c.getNomp(),c.getPrix(),c.getIdcateg()));
       
        
    }

    @FXML
    private void deleteproduit(ActionEvent event) {
       ProduitCategorie cp =new ProduitCategorie();
        ObservableList listcp , Pointage;
          listcp = tableprodcateg.getItems();
          Pointage = tableprodcateg.getSelectionModel().getSelectedItems();
         cp=tableprodcateg.getSelectionModel().getSelectedItems().get(0);

        sp.supprimerProduit(new Produit(Integer.parseInt( idptf.getText()),prodtf.getText(),Float.parseFloat(prixtf.getText()),Integer.parseInt(idcategtf.getText())));
        
        Pointage.forEach(listcp::remove);
        
        clearFields();
        
    }

    @FXML
    private void selectproduit(MouseEvent event) {
       ProduitCategorie selected=tableprodcateg.getSelectionModel().getSelectedItem();
        prodtf.setText(selected.getNomp());
        prixtf.setText(String.valueOf(selected.getPrix()));
        categtf.setText(selected.getNomcateg());
        idptf.setText(String.valueOf(selected.getIdp()));
        idcategtf.setText(String.valueOf(selected.getIdcateg()));
       
    }
    
    
    
         private void afficherProduitCategorie()
    {
            
        sp.afficherProduitCateg().stream().forEach((p)->{listcp.add(p);});
       prodcl.setCellValueFactory(new PropertyValueFactory<>("nomp"));
       categcl.setCellValueFactory(new PropertyValueFactory<>("nomcateg"));
        prixcl.setCellValueFactory(new PropertyValueFactory<>("prix"));
         idpcl.setCellValueFactory(new PropertyValueFactory<>("idp"));
        idccl.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
     
        
        
        tableprodcateg.setItems(listcp);
    }
         
          public void clearFields() {
           prodtf.clear();
           prixtf.clear();
           categtf.clear();
           idptf.clear();
           idcategtf.clear();
        }

    @FXML
    private void chercherProduit(ActionEvent event) {
         String selectedItem = prodcategcombo.getSelectionModel().getSelectedItem().toString();
        listcp.clear();
     
        sp.chercherProduitCategorie(selectedItem).stream().forEach((p)->{listcp.add(p);});
        prodcl.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        idpcl.setCellValueFactory(new PropertyValueFactory<>("idp"));
        idccl.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
       categcl.setCellValueFactory(new PropertyValueFactory<>("nomcateg"));
       idccl.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
        tableprodcateg.setItems(listcp);
    } 

    @FXML
    private void Refresh(ActionEvent event) {
         listcp.clear();
         afficherProduitCategorie();
        
    }

    @FXML
    private void GotoStockAcceuil(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AceuilStock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void imprimertableau(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
        Connection cnx = DataSource.getInstance().getCnx();

                Statement stmt = cnx.createStatement();
                /* Define the SQL query */
                ResultSet query_set = stmt.executeQuery("Select produit.idp,produit.nomp,produit.prix,categorie.nomcateg from produit left join categorie on produit.idcateg=categorie.idcateg");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("ProduitRapport.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                
                PdfPTable my_report_table = new PdfPTable(4);
                my_pdf_report.add(new Paragraph("Rapport de details de produits",FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.ORANGE)));
                my_pdf_report.add(new Paragraph(new Date().toString()));
                my_pdf_report.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));
                my_report_table.addCell("ID Produit");
                my_report_table.addCell("Produit");
                my_report_table.addCell("Prix");
                my_report_table.addCell("Categorie");
                
               //create a cell object
                PdfPCell table_cell;
               
                while (query_set.next()) {                
                                String dept_id = query_set.getString("idp");
                                table_cell=new PdfPCell(new Phrase(dept_id));
                                my_report_table.addCell(table_cell);
                                String dept_name=query_set.getString("nomp");
                                table_cell=new PdfPCell(new Phrase(dept_name));
                                my_report_table.addCell(table_cell);
                                String manager_id=query_set.getString("prix");
                                table_cell=new PdfPCell(new Phrase(manager_id));
                                my_report_table.addCell(table_cell);
                                String location_id=query_set.getString("nomcateg");
                                table_cell=new PdfPCell(new Phrase(location_id));
                                my_report_table.addCell(table_cell);
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
                /* Close all DB related objects */
                query_set.close();
                stmt.close(); 
                               
                    
                    
        
    }
    
}
        
