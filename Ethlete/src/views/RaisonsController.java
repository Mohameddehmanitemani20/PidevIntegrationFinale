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
import services.ServicesCategorie;
import services.ServicesRaison;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RaisonsController implements Initializable {
    private Raison reason;
    @FXML
    private TableColumn<Raison,Integer> idraison;
    @FXML
    private TableColumn<Raison,String> raison;
    @FXML
    private TextField raisontf;
    @FXML
    private TextField idrtf;
    @FXML
    private TableView<Raison> tableraisons;
    ObservableList<Raison> list= FXCollections.observableArrayList();
    ObservableList<Raison> listtriee= FXCollections.observableArrayList();
    ServicesRaison sr= new ServicesRaison();
    @FXML
    private TextField rechraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //afficher Liste des categories
       ServicesRaison sr= new ServicesRaison();
       sr.afficherRaisons().stream().forEach((p)->{list.add(p);});
       idraison.setCellValueFactory(new PropertyValueFactory<>("idraison"));
       raison.setCellValueFactory(new PropertyValueFactory<>("raisontxt"));
       tableraisons.setItems(list);
    }    
    
    
    
    

    @FXML
    private void gotoreclamation(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("reclamation.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void ajouterRaison(ActionEvent event) {
        ServicesRaison sr= new ServicesRaison();
        sr.ajouterRaison(new Raison(raisontf.getText())); 
        
        Raison raison=new Raison(raisontf.getText());
        ObservableList<Raison> raisons=tableraisons.getItems();
        raisons.add(raison);
        tableraisons.setItems(raisons);
        
        clearFields();
       
    }

    @FXML
    private void selectRaison(MouseEvent event) {
        reason= tableraisons.getSelectionModel().getSelectedItem();
     raisontf.setText(reason.getRaisontxt());
     idrtf.setText(String.valueOf(reason.getIdraison()));
    }

    @FXML
    private void modifierRaison(ActionEvent event) {
         //fillout my tableview with input
        Raison r=new Raison();
        r=tableraisons.getSelectionModel().getSelectedItem();
        r.setIdraison(Integer.parseInt(idrtf.getText()));
        r.setRaisontxt(raisontf.getText());
        clearFields();
        list.set(tableraisons.getSelectionModel().getFocusedIndex(),r);
        
        
        //modify command in database
       
        sr.modifierRaison(r);
        
        
    }
    
    @FXML
    private void supprimerRaison(ActionEvent event) {
      
        Raison r=new Raison();
        ObservableList raisons, Pointage;
        raisons = tableraisons.getItems();
        Pointage = tableraisons.getSelectionModel().getSelectedItems();
         r=tableraisons.getSelectionModel().getSelectedItems().get(0);

        sr.supprimerRaison(new Raison(Integer.parseInt(idrtf.getText()),r.getRaisontxt()));
        
        Pointage.forEach(raisons::remove);
        
        clearFields();
        
        
    }
    
    
    @FXML
    private void rechercherRaisons(KeyEvent event) {
        
    listtriee.clear();
        sr.chercherRaison(list,rechraison.getText()).stream().forEach((p)->{listtriee.add(p);});
        idraison.setCellValueFactory(new PropertyValueFactory<>("idraison"));
        raison.setCellValueFactory(new PropertyValueFactory<>("raisontxt"));
        tableraisons.setItems(listtriee);
           
     
    }

    @FXML
    private void trierRaisons(ActionEvent event) {
   

         listtriee.clear();
        sr.trierRaison().stream().forEach((p)->{listtriee.add(p);});
        idraison.setCellValueFactory(new PropertyValueFactory<>("idraison"));
        raison.setCellValueFactory(new PropertyValueFactory<>("raisontxt"));
        tableraisons.setItems(listtriee);
    }
    
    
    
    
    // boulis
    
    
    public void clearFields() {
        idrtf.clear();
        raisontf.clear();
        
    }

    

    
}
