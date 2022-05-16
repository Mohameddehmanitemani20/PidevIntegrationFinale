/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;
import models.Categorie;
import services.ServicesCategorie;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherCategorieController implements Initializable {
    private Categorie categorie;
    @FXML
    private TableView<Categorie> tablecateg;
    @FXML
    private TableColumn<Categorie,String> nomcateg;

    ObservableList<Categorie> List= FXCollections.observableArrayList();
    ObservableList<Categorie> Listtriee= FXCollections.observableArrayList();
   
    @FXML
    private TableColumn<Categorie,Integer> idcateg;
    
    @FXML
    private TextField tfnom;
    @FXML
    private Button ajoutCategorie;
    @FXML
    private Button supprimerCategorie;
    @FXML
    private Button trierCategorie;
    @FXML
    private TextField edittfnom;
    @FXML
    private TextField rechcateg;
    @FXML
    private Label idcateglbl;
    @FXML
    private Label alertlabel;
    
    
    
    
    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException {
        if (tfnom.getText().isEmpty())
              {
           alertlabel.setText("Veuillez remplir le champs");
              }
        else{
        ServicesCategorie scg= new ServicesCategorie();
        scg.ajouterCategorie(new Categorie(tfnom.getText())); 
        
        Categorie categorie=new Categorie(tfnom.getText());
        ObservableList<Categorie> categs=tablecateg.getItems();
        categs.add(categorie);
        tablecateg.setItems(categs);
        }
    
        
        
    }
    
    
    
    @FXML
    void selectcategorie(MouseEvent event) {
      categorie= tablecateg.getSelectionModel().getSelectedItem();
      edittfnom.setText(categorie.getNomcateg());
      idcateglbl.setText(String.valueOf(categorie.getIdcateg()));
    }
    
    
     public void clearFields() {
        edittfnom.clear();}
    
     
    @FXML
    private void RemoveCategorie(ActionEvent event) throws IOException{
      
        ServicesCategorie scg= new ServicesCategorie();
        Categorie cg=new Categorie();
        ObservableList categories, Pointage;
        categories = tablecateg.getItems();
        Pointage = tablecateg.getSelectionModel().getSelectedItems();
         cg=tablecateg.getSelectionModel().getSelectedItems().get(0);

        scg.supprimerCategorie(new Categorie(Integer.parseInt(idcateglbl.getText()),cg.getNomcateg()));
        
        Pointage.forEach(categories::remove);
        
        
        
        
        
      
    }
    
    
    private void UpdateCategorie(ActionEvent event) throws IOException{
     Categorie selected=tablecateg.getSelectionModel().getSelectedItem();
     edittfnom.setText(selected.getNomcateg());
             ServicesCategorie scg = new ServicesCategorie();
             selected.setNomcateg(edittfnom.getText());
             scg.modifierCategorie(selected);
             
               scg.afficherCategorie().stream().forEach((p)->{List.add(p);});
        //idcateg.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
        nomcateg.setCellValueFactory(new PropertyValueFactory<>("nomcateg"));
       
        tablecateg.setItems(List);

     
    }
    
     @FXML
    private void SaveCateg(ActionEvent event) throws IOException{
        categorie.setNomcateg(edittfnom.getText());
        ServicesCategorie scg = new ServicesCategorie();
        scg.modifierCategorie(new Categorie(Integer.parseInt(idcateglbl.getText()),edittfnom.getText()));
        new Alert(Alert.AlertType.INFORMATION,categorie.getNomcateg() + " Modifier !!", ButtonType.APPLY.CLOSE).show();
        clearFields();
         List.set(tablecateg.getSelectionModel().getFocusedIndex(),categorie);
        
     }
    
    
    
    @FXML
    private void TrierCategorie(ActionEvent event) throws IOException{
        ServicesCategorie scg= new ServicesCategorie();
        Listtriee.clear();
        scg.trierCategorie().stream().forEach((p)->{Listtriee.add(p);});
        //idcateg.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
        nomcateg.setCellValueFactory(new PropertyValueFactory<>("nomcateg"));
        tablecateg.setItems(Listtriee);
     
     
    }
    
    
     @FXML
    private void gotoStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AceuilStock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    
    
    
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //afficher Liste des categories
        ServicesCategorie scg= new ServicesCategorie();
        scg.afficherCategorie().stream().forEach((p)->{List.add(p);});
        //idcateg.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
        nomcateg.setCellValueFactory(new PropertyValueFactory<>("nomcateg"));
        
        tablecateg.setItems(List);
     
        
        
       
        
    }    

    @FXML
    void rechercherTextChanged(KeyEvent event) {
        ServicesCategorie scg= new ServicesCategorie();
        Listtriee.clear();
        scg.chercherCategorie(List, rechcateg.getText()).stream().forEach((p)->{Listtriee.add(p);});
        //idcateg.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
        nomcateg.setCellValueFactory(new PropertyValueFactory<>("nomcateg"));
        tablecateg.setItems(Listtriee);

    }
   

   
    
    
    
}
