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
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Categorie;
import models.Commande;
import models.CommandeProduit;
import models.Raison;
import models.Reclamation;
import models.ReclamationUtilisateur;
import services.SendEmail;
import services.ServicesCategorie;
import services.ServicesCommande;
import services.ServicesReclamation;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ConsulterReclamationController implements Initializable {
     
     ServicesReclamation srec=new ServicesReclamation();
     ObservableList<ReclamationUtilisateur> list = FXCollections.observableArrayList();
     ObservableList<ReclamationUtilisateur> listtriee= FXCollections.observableArrayList();
      private ReclamationUtilisateur recuser;
     @FXML
    private TableColumn<ReclamationUtilisateur,Integer> idrec;
    @FXML
    private TableColumn<ReclamationUtilisateur, String> contenu;
     @FXML
    private TableColumn<ReclamationUtilisateur, Integer> iduser;
    @FXML
    private TableColumn<ReclamationUtilisateur, String> daterec;
     @FXML
    private TableColumn<ReclamationUtilisateur, Integer> idraison;
     @FXML
    private TableColumn<ReclamationUtilisateur, String> nom;
     @FXML
    private TableColumn<ReclamationUtilisateur, String> prenom;
    @FXML
    private TableColumn<ReclamationUtilisateur, String> username;
    @FXML
    private TableColumn<ReclamationUtilisateur, String> email;
    @FXML
    private TableColumn<ReclamationUtilisateur, Integer> tel;
     @FXML
    private TableColumn<ReclamationUtilisateur, String> pwd;
    @FXML
    private TableView<ReclamationUtilisateur> tablerec;
    @FXML
    private TableColumn<ReclamationUtilisateur, String> etatcl;
    @FXML
    private TextField idrtf;
    @FXML
    private TextField contenutf;
    @FXML
    private TextField daterectf;
    @FXML
    private TextField idtf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField passwordtf;
    @FXML
    private TextField teltf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField idRaisontf;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField etattf;
    @FXML
    private ComboBox<String> etatcombo;
    @FXML
    private TextField rechrec;
    @FXML
    private Label nombrec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int nomb= srec.nombreReclamation();
    ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Traitee");
   etatcombo.setItems(options);
        
        afficherReclamation();
        
        nombrec.setText(Integer.toString(nomb));
        
        
        // TODO
    } 
      
    
    
    
    

    private void goToreclamation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reclamation.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void gotodashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
    /////
     private void afficherReclamation()
    {
        
        
    
        srec.afficherReclamationUser().stream().forEach((p)->{list.add(p);});
        idrec.setCellValueFactory(new PropertyValueFactory<>("idr"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        iduser.setCellValueFactory(new PropertyValueFactory<>("id"));
        daterec.setCellValueFactory(new PropertyValueFactory<>("daterec"));
        idraison.setCellValueFactory(new PropertyValueFactory<>("idRaison"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        pwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        etatcl.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
      
        
        
        tablerec.setItems(list);
    }
     
     
      private void afficherReclamationTriee()
    {
        
        
    
        srec.trierReclamationUtilisateur().stream().forEach((p)->{listtriee.add(p);});
        idrec.setCellValueFactory(new PropertyValueFactory<>("idr"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        iduser.setCellValueFactory(new PropertyValueFactory<>("id"));
        daterec.setCellValueFactory(new PropertyValueFactory<>("daterec"));
        idraison.setCellValueFactory(new PropertyValueFactory<>("idRaison"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        pwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        etatcl.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
      
        
        
        tablerec.setItems(listtriee);
    }
     @FXML
    private void selectReclamation(MouseEvent event) {
     
     ReclamationUtilisateur selected=tablerec.getSelectionModel().getSelectedItem();
       idrtf.setText(String.valueOf(selected.getIdr()));
       contenutf.setText(selected.getContenu());
       daterectf.setText(selected.getDaterec());
       idtf.setText(String.valueOf(selected.getId()));
       prenomtf.setText(selected.getPrenom());
       nomtf.setText(selected.getNom()); 
       passwordtf.setText(selected.getPassword());
       teltf.setText(String.valueOf(selected.getTelephone()));
       emailtf.setText(selected.getEmail());
       idRaisontf.setText(String.valueOf(selected.getIdRaison()));
       usernametf.setText(selected.getUsername());
       etattf.setText(selected.getEtat());
        
    }

    @FXML
    private void modifierReclamation(ActionEvent event) throws Exception {
        String selectedItem = etatcombo.getSelectionModel().getSelectedItem().toString();
        ReclamationUtilisateur ru=new ReclamationUtilisateur();
        ru=tablerec.getSelectionModel().getSelectedItem();
        ru.setIdr(Integer.parseInt(idrtf.getText()));
        ru.setContenu(contenutf.getText());
        ru.setDaterec(daterectf.getText());
        ru.setId(Integer.parseInt(idtf.getText()));
        ru.setPrenom(prenomtf.getText());
        ru.setNom(nomtf.getText());
        ru.setPassword(passwordtf.getText());
        ru.setTelephone((Integer.parseInt(teltf.getText())));
        ru.setEmail(emailtf.getText());
        ru.setIdRaison(Integer.parseInt(idRaisontf.getText()));
        ru.setUsername(usernametf.getText());
        ru.setEtat(selectedItem);
        list.set(tablerec.getSelectionModel().getFocusedIndex(),ru);
        
        srec.modifierReclamation(new Reclamation( ru.getIdr(),ru.getContenu(),ru.getId(), ru.getDaterec(),ru.getIdRaison(),selectedItem));
        list.clear();
        afficherReclamation();
        SendEmail.mailing(emailtf.getText(),"Confirmation de Reclamation","Cher "+usernametf.getText()+" votre Reclamation été bien traitée"); 
        
        
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
         ReclamationUtilisateur ru =new ReclamationUtilisateur();
          ObservableList listcp , Pointage;
          listcp = tablerec.getItems();
           Pointage = tablerec.getSelectionModel().getSelectedItems();
          ru=tablerec.getSelectionModel().getSelectedItems().get(0);

        srec.supprimerReclamation(new Reclamation (Integer.parseInt(idrtf.getText()),contenutf.getText(),Integer.parseInt(idtf.getText()),daterectf.getText(),Integer.parseInt(idRaisontf.getText()), etattf.getText()));
    
        Pointage.forEach(listcp::remove);
        
        clearFields();
        
         int nomb= srec.nombreReclamation();
         nombrec.setText(Integer.toString(nomb));
         
    }
    
    
    public void clearFields() {
        
         idrtf.clear();
         contenutf.clear();
         daterectf.clear();
         idtf.clear();
         prenomtf.clear();
         nomtf.clear();
         passwordtf.clear();
         teltf.clear();
         emailtf.clear();
         idRaisontf.clear();
         usernametf.clear();
         etattf.clear();
        
     }

    @FXML
    private void rechercherReclamation(KeyEvent event) {
        
    
        list.clear();
        srec.chercherReclamationUse(rechrec.getText()).stream().forEach((p)->{list.add(p);});
        tablerec.setItems(list);
    }

    @FXML
    private void trieeReclamation(ActionEvent event) {
        listtriee.clear();
       afficherReclamationTriee();
      
           
    }

    @FXML
    private void goToReclamations(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("reclamation.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    private void gotoRaisons(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("raisons.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
                
    }

    @FXML
    private void imprimer(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
            Connection cnx = DataSource.getInstance().getCnx();

         
                Statement stmt = cnx.createStatement();
                /* Define the SQL query */
                ResultSet query_set = stmt.executeQuery("Select utilisateur.username,utilisateur.email, utilisateur.telephone,reclamation.contenu,reclamation.daterec,reclamation.etat from utilisateur left join reclamation on utilisateur.id=reclamation.id");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("ReclamationRapport.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                
                PdfPTable my_report_table = new PdfPTable(6);
                my_pdf_report.add(new Paragraph("Rapport de details de Reclamations",FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.RED)));
                my_pdf_report.add(new Paragraph(new Date().toString()));
                my_pdf_report.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));
                my_report_table.addCell("Username");
                my_report_table.addCell("Contenu");
                my_report_table.addCell("Email");
                my_report_table.addCell("Date");
                my_report_table.addCell("numero");
                my_report_table.addCell("Etat");
                
               //create a cell object
                PdfPCell table_cell;
               
                while (query_set.next()) {                
                                String dept_id = query_set.getString("username");
                                table_cell=new PdfPCell(new Phrase(dept_id));
                                my_report_table.addCell(table_cell);
                                String dept_name=query_set.getString("contenu");
                                table_cell=new PdfPCell(new Phrase(dept_name));
                                my_report_table.addCell(table_cell);
                                String manager_id=query_set.getString("email");
                                table_cell=new PdfPCell(new Phrase(manager_id));
                                my_report_table.addCell(table_cell);
                                String location_id=query_set.getString("daterec");
                                table_cell=new PdfPCell(new Phrase(location_id));
                                my_report_table.addCell(table_cell);
                                String v1 = query_set.getString("telephone");
                                table_cell=new PdfPCell(new Phrase(v1));
                                my_report_table.addCell(table_cell);
                                String v2 = query_set.getString("etat");
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
}