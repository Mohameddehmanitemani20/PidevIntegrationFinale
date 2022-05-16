/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import interfaces.I_produit;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Categorie;
import models.Commande;
import models.Produit;
import services.SendEmail;
import services.ServicesCategorie;
import services.ServicesCommande;
import services.ServicesProduit;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterCommandeController implements Initializable {

    @FXML
    private TextField quantite;
    @FXML
    private TextField idcom;
    @FXML
    private TextField date;
    @FXML
    private Label nomproduit;
    @FXML
    private Label total;
    @FXML
    private ComboBox<Produit> prodcombo;
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ServicesProduit sp= new ServicesProduit(); 
        ObservableList<Produit> items = FXCollections.observableArrayList();
        sp.afficherProduits().stream().forEach((p)->{ items.add(p); });
        
        prodcombo.setItems(items);   
        
     
        
    }    

  

    @FXML
    private void GoTopanier(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
     @FXML
    private void ajouterCommande(ActionEvent event) throws Exception {
        
         int idprod=0;
         ServicesProduit sp =new ServicesProduit();
         
           String selectedItem = prodcombo.getSelectionModel().getSelectedItem().toString();
           idprod =sp.getidcprod(selectedItem);
         //System.out.println(idcateg);
        
         ServicesCommande sc= new ServicesCommande();
         sc.ajouterCommande(new Commande(idprod,parseInt(quantite.getText()),"")); 
         
         
         SendEmail.mailing("rmatoussi3@gmail.com","confirmation d'une commande","Vous avez commande " +parseInt(quantite.getText())+" de "+selectedItem+"s");
        
       
    }

   
    


    
    
}
