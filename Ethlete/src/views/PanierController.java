/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Categorie;
import models.Commande;
import models.CommandeProduit;
import models.Produit;
import services.ServicesCategorie;
import services.ServicesCommande;
import services.ServicesProduit;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierController implements Initializable {
   
    @FXML
    private TableColumn<CommandeProduit,Integer> idprod;
    
    @FXML
    private TableColumn<CommandeProduit,String> nomprod;
    @FXML
    private TableColumn<CommandeProduit,Float> prixprod;
    @FXML
    private TableColumn<CommandeProduit,Integer> idcom;
    @FXML
    private TableColumn<CommandeProduit,Integer> quantitecom;
    @FXML
    private TableColumn<CommandeProduit,String> datecom;
     ObservableList<CommandeProduit> listcp = FXCollections.observableArrayList();
     ObservableList<CommandeProduit> Listtriee= FXCollections.observableArrayList();
    @FXML
    private TableView<CommandeProduit> tablecp;
   
    @FXML
    private TextField idedit;
    //@FXML
   // private ComboBox<Produit> prodedit;
    @FXML
    private TextField quantiteedit;
    @FXML
    private TextField prixedit;
    @FXML
    private TextField datedit;
   
    @FXML
    private TextField idprodedit;
    @FXML
    private AnchorPane dateedit;
    @FXML
    private TextField nomprodedit;
    
    
     ServicesCommande sc= new ServicesCommande();
    @FXML
    private TextField recherchef;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherCommande();
    }    

    
    
    
    @FXML
    private void Selectcommande(MouseEvent event ) {
        
       CommandeProduit selected=tablecp.getSelectionModel().getSelectedItem();
       idedit.setText(String.valueOf(selected.getIdcom()));
       idprodedit.setText(String.valueOf(selected.getIdp()));
       quantiteedit.setText(String.valueOf(selected.getQuantite()));
       prixedit.setText(String.valueOf(selected.getPrix()));
       datedit.setText(String.valueOf(selected.getDatecom()));
       nomprodedit.setText(selected.getNomp()); 
    }
    
    


    @FXML
    private void ModiferCommande(ActionEvent event) {
        //fillout my tableview with input
        CommandeProduit c=new CommandeProduit();
        c=tablecp.getSelectionModel().getSelectedItem();
        c.setIdcom(Integer.parseInt(idedit.getText()));
        c.setIdp(Integer.parseInt(idprodedit.getText()));
        c.setDatecom(datedit.getText());
        c.setQuantite(Integer.parseInt(quantiteedit.getText()));
        c.setPrix(Float.parseFloat(prixedit.getText()));
        c.setNomp(nomprodedit.getText());
        clearFields();
         listcp.set(tablecp.getSelectionModel().getFocusedIndex(),c);
        
        
        //modify command in database
       
        sc.modifierCommande(new Commande(c.getIdcom(), c.getIdp(), c.getQuantite(),c.getDatecom()));
        
   }
    
    
     @FXML
    private void RemoveCommand(ActionEvent event) {
        
        
        CommandeProduit cp =new CommandeProduit();
        ObservableList listcp , Pointage;
       listcp = tablecp.getItems();
        Pointage = tablecp.getSelectionModel().getSelectedItems();
         cp=tablecp.getSelectionModel().getSelectedItems().get(0);

        sc.supprimerCommande(new Commande(Integer.parseInt(idedit.getText()),Integer.parseInt(idprodedit.getText()),Integer.parseInt(quantiteedit.getText()), datedit.getText()));
        
        Pointage.forEach(listcp::remove);
        
        clearFields();
        
    }
    
    
    
     @FXML
    private void TrierCommandes(ActionEvent event) {
         
           
           Listtriee.clear();
           afficherCommandeTriee();
      }

    @FXML
    private void Refresh(ActionEvent event) {
        listcp.clear();
        afficherCommande();  
       
    }
    
    @FXML
    private void Cherchercommande(KeyEvent event) {
      
        Listtriee.clear();
        sc.chercherCommandePorduit(listcp,recherchef.getText()).stream().forEach((p)->{Listtriee.add(p);});
        idprod.setCellValueFactory(new PropertyValueFactory<>("idp"));
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        prixprod.setCellValueFactory(new PropertyValueFactory<>("prix"));
        idcom.setCellValueFactory(new PropertyValueFactory<>("idcom"));
        quantitecom.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        datecom.setCellValueFactory(new PropertyValueFactory<>("datecom"));
     
        tablecp.setItems(Listtriee);
    }
    
     @FXML
    private void GotoajouterCommande(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("ajouterCommande.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    

    //implemented methods
    
      private void afficherCommande()
    {
        
        ServicesCommande sc= new ServicesCommande();
    
        sc.afficherCommandeProduit().stream().forEach((p)->{listcp.add(p);});
        idprod.setCellValueFactory(new PropertyValueFactory<>("idp"));
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        prixprod.setCellValueFactory(new PropertyValueFactory<>("prix"));
        idcom.setCellValueFactory(new PropertyValueFactory<>("idcom"));
        quantitecom.setCellValueFactory(new PropertyValueFactory<>("quantite"));
       datecom.setCellValueFactory(new PropertyValueFactory<>("datecom"));
        
        
        tablecp.setItems(listcp);
    }
      
      
      private void afficherCommandeTriee()
    {
        
        ServicesCommande sc= new ServicesCommande();
    
        sc.trierCommandeProduit().stream().forEach((p)->{Listtriee.add(p);});
        idprod.setCellValueFactory(new PropertyValueFactory<>("idp"));
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        prixprod.setCellValueFactory(new PropertyValueFactory<>("prix"));
        idcom.setCellValueFactory(new PropertyValueFactory<>("idcom"));
        quantitecom.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        datecom.setCellValueFactory(new PropertyValueFactory<>("datecom"));
        
        
        tablecp.setItems(Listtriee);
    }
    
    
     public void clearFields() {
        idedit.clear();
        idprodedit.clear();
        quantiteedit.clear();
        prixedit.clear();
        datedit.clear();
        nomprodedit.clear();  
        
     }

    @FXML
    private void gotoacceuilStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AceuilStock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
}

   
