/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import models.Invitation;
import models.Utilisateur;
import services.ServiceEquipe;
import services.ServiceInvitation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.User;

/**
 * FXML Controller class
 *
 * @author anasl
 */
public class ReponseInvitationController implements Initializable {
              User u=new User(AuthentificationController.idglobal);
ServiceInvitation si=new ServiceInvitation();
    ServiceEquipe se=new ServiceEquipe();
    List<String> list1=new ArrayList();
    
 ObservableList<String> eq;
    @FXML
    private TableView<Invitation> invitations;
        @FXML

    private TableColumn<Invitation, String> etat;
    @FXML
    private ComboBox<String> list;
    @FXML
    private JFXButton cb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        // TODO
    }    

    @FXML
    private void refuser(ActionEvent event) {
                   Invitation e=invitations.getSelectionModel().getSelectedItem();
                   si.accepter(e.getId_invitation());

    }

    @FXML
    private void accepter(ActionEvent event) {
                   Invitation e=invitations.getSelectionModel().getSelectedItem();
si.refuser(e.getId_invitation());
    }
     void afficher()
    {
        
       
	ObservableList<Invitation> list4 =FXCollections.observableArrayList( si.afficher());
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

etat.setCellValueFactory(new PropertyValueFactory<Invitation, String>("etat"));
		
    invitations.setItems(list4);

    
    }

    @FXML
    private void retour_menu(ActionEvent event) {
       Stage stage = (Stage)cb.getScene().getWindow();
       stage.close(); 
              
    }
}
