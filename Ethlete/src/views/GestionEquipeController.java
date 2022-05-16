/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import models.Equipe;
import models.Utilisateur;
import services.ServiceEquipe;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.User;

/**
 * FXML Controller class
 *
 * @author anasl
 */
public class GestionEquipeController implements Initializable {

              User u=new User(AuthentificationController.idglobal);
 ServiceEquipe t = new ServiceEquipe ();
    /**
     * Initializes the controller class.
     */
 @FXML
    private javafx.scene.control.TextField nom;
    @FXML
    private TableView<Equipe> equipes;
    @FXML
    private TableColumn<Equipe, String> name;
    @FXML
    private JFXButton cb;
    @FXML
    private TextField recherchetf;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher_equipes();
    }    

    @FXML
    private void ajouter_equipe(ActionEvent event) {
        
                  
        StringBuilder errors=new StringBuilder();
        if(nom.getText().trim().isEmpty()){
            errors.append("- Please enter a  Name equipe\n");
        }
        else if (t.existe(nom.getText()))
                
                {                  JOptionPane.showMessageDialog(null, "Nom existe déjà");


                }    
                
        else {
        t.ajouter(new Equipe (nom.getText(),u.getId()));
    }
    }

    @FXML
    private void afficher_equipe(ActionEvent event) {
    
    Equipe e=equipes.getSelectionModel().getSelectedItem();
        nom.setText(e.getNom_equipe());

    
    }
   public void afficher_equipes() {
		ObservableList<Equipe> list =FXCollections.observableArrayList( t.afficher());
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

name.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom_equipe"));
		
		equipes.setItems(list);
	}

    @FXML
    private void supprimer(ActionEvent event) {
          Equipe e=equipes.getSelectionModel().getSelectedItem();
          t.supprimer(e.getId_equipe());
          afficher_equipes();
    }

    @FXML
    private void modifier(ActionEvent event) {
          Equipe e=equipes.getSelectionModel().getSelectedItem();
          e.setNom_equipe(nom.getText());
          t.modifier(e.getId_equipe(), e);
          afficher_equipes();
    }

    @FXML
    private void retour_menu(ActionEvent event) {
        Stage stage = (Stage)cb.getScene().getWindow();
       stage.close(); 
              
    }

    @FXML
    private void recherche(ActionEvent event) {
      Equipe x=  t.findBynom_equipe(recherchetf.getText());
        System.out.println(x.toString());
                 JOptionPane.showMessageDialog(null,x.toString() );

        
    }

    @FXML
    private void trier(ActionEvent event) {
        List<Equipe> x=  t.sortByNom();
        	ObservableList<Equipe> list =FXCollections.observableArrayList(x);
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

name.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom_equipe"));
		
		equipes.setItems(list);
    }
   
}
