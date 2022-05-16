/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Categorie;
import models.Commande;
import models.Raison;
import models.Reclamation;
import models.User;
import services.ServicesCategorie;
import services.ServicesCommande;
import services.ServicesProduit;
import services.ServicesRaison;
import services.ServicesReclamation;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationController implements Initializable {
     ServicesRaison sr =new ServicesRaison();
     ServicesReclamation srec=new ServicesReclamation();
                   User u=new User(AuthentificationController.idglobal);

    @FXML
    private TextArea reclamtxt;
    @FXML
    private ComboBox<String> raisoncombo;
    @FXML
    private TextField idrec;
    private TextField iduser;
    @FXML
    private TextField daterec;
    @FXML
    private PasswordField pwd;
    @FXML
    private Label labelresponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
          //combobox
       
        ObservableList<String> items = FXCollections.observableArrayList();
        sr.afficherNomRaison().stream().forEach((p)->{items.add(p.getRaisontxt()); });
        
        raisoncombo.setItems(items);
        
        // TODO
    }   
    
    @FXML
    private void gotoconsulterReclamation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("consulterReclamation.fxml"));
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

    

    @FXML
    private void ajouterReclamation(ActionEvent event) {
           int id=0;
           
           String selectedItem =raisoncombo.getSelectionModel().getSelectedItem();
           Raison r=sr.getRaison(selectedItem);
         //  idr =sr.getidraison(selectedItem);
          
           
        
          
          // id=srec.getiduser(pwd.getText());
           System.out.println(r);
           
           
           
           srec.ajouterReclamation(new Reclamation(reclamtxt.getText(), (int) u.getId(),r.getIdraison(),"en cours"));
           
          
           
          
        
    }
    /////boulis
    
     public void clearFields() {
        
        reclamtxt.clear();
        
    }

}
