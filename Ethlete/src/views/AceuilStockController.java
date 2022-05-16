/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import interfaces.I_produit;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectProperty;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Categorie;
import models.Produit;
import models.Reclamation;
import services.ServicesCategorie;
import services.ServicesProduit;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AceuilStockController implements Initializable {
    
    @FXML
    private TextField rechercherProduit;

    @FXML
    private Hyperlink categoriesHL;
    @FXML
    private ComboBox choixcateg;
    @FXML
    private TextField nomp;
    @FXML
    private TextField prix;
     @FXML
    private GridPane prodgrid;
     
     I_produit interfaceproduit = new ServicesProduit();
    @FXML
    private Label alertlbl;
   

       @FXML
    private void gotoCategories(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("afficherCategorie.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
    
     @FXML
    private void ajouterproduit(ActionEvent event) throws IOException {
         int idcateg=0;
       ServicesCategorie sc =new ServicesCategorie();
         
       String selectedItem = choixcateg.getSelectionModel().getSelectedItem().toString();
       idcateg =sc.getidcateg(selectedItem);
         //System.out.println(idcateg);
         if (nomp.getText().isEmpty()|| prix.getText().isEmpty())
              {
           alertlbl.setText("Veuillez remplir les champs");
              }
           else
           
         { ServicesProduit sp= new ServicesProduit();
        sp.ajouterProduit(new Produit(nomp.getText(),parseFloat(prix.getText()),idcateg)); 
        //affichage
        List<Produit> list = interfaceproduit.afficherProduits();
        updateGrid(list);}
       
    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //combobox
        
        ServicesCategorie scg= new ServicesCategorie(); 
        ObservableList<Categorie> items = FXCollections.observableArrayList();
        scg.afficherNomCategorie().stream().forEach((p)->{items.add(p); });
        
        choixcateg.setItems(items);
        
        //affichage
        List<Produit> list = interfaceproduit.afficherProduits();
        
        updateGrid(list);
        
            }    

    private void updateGrid(List<Produit> list){
        int column = 0;
        int row = 0;
        prodgrid.getChildren().clear();
    
        for (Produit p : list){
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Produit.fxml"));
            
                AnchorPane anchorPane = fxmlLoader.load();
            
            ProduitController produitcontroller = fxmlLoader.getController();
                
            produitcontroller.setData(p);
            
            if(column ==3){
                column = 0;
                row++;
            }
            prodgrid.add(anchorPane,column++,row);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }

        
   }
    
     @FXML
    void TrierProduit(ActionEvent event) {
        List<Produit> list = interfaceproduit.trierProduit();
        
        updateGrid(list);

    }

    
    
    
       @FXML
    void rechercherProduit_clicked(KeyEvent event) {
        List<Produit> list = interfaceproduit.chercherProduit(interfaceproduit.afficherProduits(), rechercherProduit.getText());
        
        updateGrid(list);

    }

    @FXML
    private void gotomodifierproduit(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("modifierProduit.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void gotopanier(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboardStock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}
        
            
       

   
    
    
   

