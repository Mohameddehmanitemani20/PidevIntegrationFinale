/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import models.Invitation;
import models.User;
import models.Equipe;
import services.ServiceEquipe;
import services.ServiceInvitation;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author anasl
 */
public class InvitationUIController implements Initializable {

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
        // TODO
         
     lister();
    
    	
  afficher();
   
   
   
    }    
    //tzid 2 update lil etat
    // wahda tbadel etat lil
    // update invitation set etat ="" wher id_invitation =id
    

    @FXML
    private void ajouter_inv(ActionEvent event) {
        Equipe e=se.findBynom_equipe(list.getValue());
        si.ajouter(new Invitation("non_consulté",e.getId_equipe(),u.getId()));
          afficher();

        
    }

    @FXML
    private void supprimer(ActionEvent event) {
           Invitation e=invitations.getSelectionModel().getSelectedItem();
          se.supprimer(e.getId_invitation());
            afficher();

    }
    
    void lister()
    {for(int i=0;i<se.afficher().size();i++)
        list1.add(se.afficher().get(i).getNom_equipe());
    
    
    
      eq=FXCollections.observableArrayList(list1);
     list.setItems(eq);
    }
    void afficher()
    {

	ObservableList<Invitation> list4 =FXCollections.observableArrayList( si.afficher());
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

etat.setCellValueFactory(new PropertyValueFactory<Invitation, String>("etat"));
		
    invitations.setItems(list4);

    
    }

    @FXML
    private void retour_menu(ActionEvent event) throws IOException {
               FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/MenuJoueur.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Invitation");
            stage.setScene(new Scene(root1));  
            stage.show();
        
    }
    
}
