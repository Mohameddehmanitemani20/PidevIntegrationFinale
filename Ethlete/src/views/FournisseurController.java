/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Categorie;
import models.Commande;
import models.CommandeProduit;
import models.Fournisseur;
import models.FournisseurProduit;
import models.Produit;
import services.ServicesCommande;
import services.ServicesFournisseur;
import services.ServicesProduit;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FournisseurController implements Initializable {

    @FXML
    private TextField nomf;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField numf;
    @FXML
    private TextField emailf;
    @FXML
    private TextField adressef;
    @FXML
    private ComboBox<Produit> prodf;
    @FXML
    private TableView<FournisseurProduit> tablef;
    @FXML
    private TableColumn<FournisseurProduit,String> nom;
    @FXML
    private TableColumn<FournisseurProduit,String> prenom;
    @FXML
    private TableColumn<FournisseurProduit,String> num;
    @FXML
    private TableColumn<FournisseurProduit,String> email;
    @FXML
    private TableColumn<FournisseurProduit,String> adresse;
    @FXML
    private TableColumn<FournisseurProduit,Integer> idprod;
    @FXML
    private TableColumn<FournisseurProduit,String> nomp;
    @FXML
    private TableColumn<FournisseurProduit, Float> prixf;
    @FXML
    private TableColumn<FournisseurProduit,Integer> idf;
    ObservableList<FournisseurProduit> listfp = FXCollections.observableArrayList();
    ObservableList<FournisseurProduit> Listtriee= FXCollections.observableArrayList();
    @FXML
    private TextField idp;
    @FXML
    private TextField prix;
    @FXML
    private TextField idfour;
    private TextField rechF;
    @FXML
    private ComboBox<Produit> rechcombo;
    @FXML
    private Label alertlabel;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //combox1
         afficherFournisseur();
        
         ServicesProduit sp= new ServicesProduit(); 
        ObservableList<Produit> items = FXCollections.observableArrayList();
        sp.afficherProduits().stream().forEach((p)->{items.add(p);});
        
        prodf.setItems(items);  
        
        
        //combobox2
         ObservableList<Produit> items1= FXCollections.observableArrayList();
        sp.afficherProduits().stream().forEach((p)->{items1.add(p); });
        
        rechcombo.setItems(items1);  
        
        
        
        
        
    }    

    @FXML
    private void ajouterFournisseur(ActionEvent event) {
        
      
        
        ServicesFournisseur sf= new ServicesFournisseur();
        int idprod=0;
         ServicesProduit sp =new ServicesProduit();
         
           String selectedItem = prodf.getSelectionModel().getSelectedItem().toString();
           idprod =sp.getidcprod(selectedItem);
         //System.out.println(idcateg);
         
           emailFormat(emailf, alertlabel, "email format incorrect");
           sf.ajouterfournisseur(new Fournisseur(nomf.getText(),prenomf.getText(),emailf.getText(),parseInt(numf.getText()),adressef.getText(),idprod)); 
   
         
    
    }
    
    
    //implemented methods
     private void afficherFournisseur()
    {
        
        ServicesFournisseur sf= new ServicesFournisseur();
        sf.afficherFournisseurProduit().stream().forEach((p)->{listfp.add(p);});
        idprod.setCellValueFactory(new PropertyValueFactory<>("idp"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomf"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenomf"));
        num.setCellValueFactory(new PropertyValueFactory<>("telf"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idf.setCellValueFactory(new PropertyValueFactory<>("idf"));
        nomp.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        prixf.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
        
        tablef.setItems(listfp);
    }

    @FXML
    private void refresh(ActionEvent event) {
        
        listfp.clear();
        afficherFournisseur();  
    }

    @FXML
    private void modifierFournisseur(ActionEvent event) {
         
         // get the id of the selection
         int idprod=0;
         ServicesProduit sp =new ServicesProduit();
         ServicesFournisseur sf = new ServicesFournisseur();
         
        String selectedItem = prodf.getSelectionModel().getSelectedItem().toString();
         idprod =sp.getidcprod(selectedItem);
           
         //set the proper attributes  
        FournisseurProduit fp=new FournisseurProduit();
        fp=tablef.getSelectionModel().getSelectedItem();
        fp.setIdf(Integer.parseInt(idfour.getText()));
        fp.setIdp(Integer.parseInt(idp.getText()));
        fp.setNomf(nomf.getText());
        fp.setPrix(Float.parseFloat(prix.getText()));
        fp.setPrenomf(prenomf.getText());
        fp.setAdresse(adressef.getText());
        fp.setTelf(Integer.parseInt(numf.getText()));
        
        
         clearFields();
         listfp.set(tablef.getSelectionModel().getFocusedIndex(),fp);
        
        
        //modify commande in database
       
        sf.modifierFournisseur(new Fournisseur(fp.getIdf(),fp.getNomf(),fp.getPrenomf(),fp.getEmail(),fp.getTelf(),fp.getAdresse(),idprod));
        
        
    }

    @FXML
    private void selectFournisseur(MouseEvent event) {
        //
       FournisseurProduit selected=tablef.getSelectionModel().getSelectedItem();
       nomf.setText(selected.getNomf());
       prenomf.setText(selected.getPrenomf());
       numf.setText(String.valueOf(selected.getTelf()));
       emailf.setText(selected.getEmail());
       adressef.setText(selected.getAdresse());
       idfour.setText(String.valueOf(selected.getIdf()));
       idp.setText(String.valueOf(selected.getIdp()));
       prix.setText(String.valueOf(selected.getPrix()));
  
    }
    
    public void clearFields() {
        idfour.clear();
        idp.clear();
        nomf.clear();
        prix.clear();
        prenomf.clear();
        adressef.clear(); 
        numf.clear();
        emailf.clear();
        idp.clear();
        
     }

    @FXML
    private void SupprimerFournisseur(ActionEvent event) {
        
        
         ServicesProduit sp =new ServicesProduit();
         ServicesFournisseur sf = new ServicesFournisseur();
         FournisseurProduit fp =new FournisseurProduit();
 
        ObservableList listcp, Pointage;
       listcp = tablef.getItems();
        Pointage = tablef.getSelectionModel().getSelectedItems();
         fp=tablef.getSelectionModel().getSelectedItems().get(0);

        sf.supprimerFournisseur(new Fournisseur(Integer.parseInt(idfour.getText()),nomf.getText(),prenomf.getText(),email.getText(),Integer.parseInt(numf.getText()),adressef.getText(),Integer.parseInt(idp.getText())));
        
        Pointage.forEach(listcp::remove);
        
        clearFields();
    }

   

    @FXML
    private void trierCateg(ActionEvent event) {
        
         Listtriee.clear();
           afficherFournisseurTriee();
    }
    
     private void afficherFournisseurTriee()
    {
        
        ServicesFournisseur sc= new ServicesFournisseur();
    
        sc.trierFournisseurProduit().stream().forEach((p)->{Listtriee.add(p);});
        idprod.setCellValueFactory(new PropertyValueFactory<>("idp"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomf"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenomf"));
        num.setCellValueFactory(new PropertyValueFactory<>("telf"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idf.setCellValueFactory(new PropertyValueFactory<>("idf"));
        nomp.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        prixf.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
        tablef.setItems(Listtriee);
    }

    @FXML
    private void chercherFournisseur(ActionEvent event) {
        
        String selectedItem = rechcombo.getSelectionModel().getSelectedItem().toString();
        listfp.clear();
        ServicesFournisseur sf = new ServicesFournisseur();
        sf.chercherFournisseurPorduit(selectedItem).stream().forEach((p)->{listfp.add(p);});
        idprod.setCellValueFactory(new PropertyValueFactory<>("idp"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomf"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenomf"));
        num.setCellValueFactory(new PropertyValueFactory<>("telf"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idf.setCellValueFactory(new PropertyValueFactory<>("idf"));
        nomp.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        prixf.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tablef.setItems(listfp);
    }

    @FXML
    private void gotodashboard(MouseEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("dashboardStock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void gotoProduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AceuilStock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    private void gotoCommandes(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ajouterCommande.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void generatePDF(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
            Connection cnx = DataSource.getInstance().getCnx();

                Statement stmt = cnx.createStatement();
                /* Define the SQL query */
                ResultSet query_set = stmt.executeQuery("Select fournisseur.nomf,fournisseur.prenomf,fournisseur.email,fournisseur.telf,fournisseur.adresse,produit.nomp from fournisseur left join produit on fournisseur.idp=produit.idp");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("FournisseurRapport.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                
                PdfPTable my_report_table = new PdfPTable(6);
                my_pdf_report.add(new Paragraph("Rapport de details de fournisseurs",FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.RED)));
                my_pdf_report.add(new Paragraph(new Date().toString()));
                my_pdf_report.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));
                my_report_table.addCell("Nom");
                my_report_table.addCell("Prenom");
                my_report_table.addCell("Numero");
                my_report_table.addCell("Email");
                my_report_table.addCell("Adresse");
                my_report_table.addCell("Produit");
                
               //create a cell object
                PdfPCell table_cell;
               
                while (query_set.next()) {                
                                String dept_id = query_set.getString("nomf");
                                table_cell=new PdfPCell(new Phrase(dept_id));
                                my_report_table.addCell(table_cell);
                                String dept_name=query_set.getString("prenomf");
                                table_cell=new PdfPCell(new Phrase(dept_name));
                                my_report_table.addCell(table_cell);
                                String manager_id=query_set.getString("telf");
                                table_cell=new PdfPCell(new Phrase(manager_id));
                                my_report_table.addCell(table_cell);
                                String location_id=query_set.getString("email");
                                table_cell=new PdfPCell(new Phrase(location_id));
                                my_report_table.addCell(table_cell);
                                String v1 = query_set.getString("adresse");
                                table_cell=new PdfPCell(new Phrase(v1));
                                my_report_table.addCell(table_cell);
                                String v2 = query_set.getString("nomp");
                                table_cell=new PdfPCell(new Phrase(v2));
                                my_report_table.addCell(table_cell);
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
                /* Close all DB related objects */
                query_set.close();
                stmt.close(); 
                             
                    
                    
        
        
    }

    public static boolean emailFormat(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isEmail = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
            isEmail = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isEmail;

    }
    

       
}
