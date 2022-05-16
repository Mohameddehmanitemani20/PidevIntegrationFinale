/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Commentaire;
import models.Formation;
import models.Reponse_Form;
import models.User;
import models.Utilisateur;
import services.ServiceAffectationFormateur;
import services.ServiceCommentaireImp;
import services.ServiceFormateur;
import services.ServiceFormation;
import services.ServiceParticipation;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ParticipationAdminController implements Initializable {

     @FXML
    private TableView<User> formateurs;
    @FXML
    private TableColumn<User, String> nom_f;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableView<Formation> formations;
    @FXML
    private TableColumn<Formation, String> nom;
       @FXML
    private TableColumn<Formation, String> dis;
    @FXML
    private TableColumn<Formation, Date> debut;
    @FXML
    private TableColumn<Formation, Date> fin;
        @FXML
    private TextArea cmt;
    
    
       ServiceAffectationFormateur af=new ServiceAffectationFormateur();
            ServiceFormation sf=new ServiceFormation();
            ServiceFormateur sf1=new ServiceFormateur();
ServiceParticipation sp=new ServiceParticipation();

ServiceCommentaireImp sc=new ServiceCommentaireImp();
    @FXML
    private JFXButton closeButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showFormation();
        
        
    } 
         public void showFormation() {
		ObservableList<Formation> list =FXCollections.observableArrayList( sf.afficher());
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
		formations.setItems(list);
	}

    @FXML
    private void lister(ActionEvent event) {
   Map<Formation,List<User>> map1=sp.consulter_particiapnts_par_formation();

                   Formation f=formations.getSelectionModel().getSelectedItem();
              
                    List <User> list=new ArrayList();
            for (Formation c:map1.keySet())
            {if(c.getId_formation()==f.getId_formation())
            {list=sp.chercher_part_formations_Part(f);
            
       }     }
            		ObservableList<User> list1 =FXCollections.observableArrayList( list);
                        
                        nom_f.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
                Email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		
		formateurs.setItems(list1);}
       @FXML
    void consulterstatistiques(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/statistiques.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
    @FXML
    void comment(ActionEvent event) {
        System.out.println("1");
                         Formation f=formations.getSelectionModel().getSelectedItem();
  User u=new User();
        cmt.setText("");
        List<Commentaire> comments=new ArrayList();
                System.out.println("1");

        comments=sc.filtrer_par_formation(f);
                System.out.println("1");

      for(int i=0;i<=comments.size();i++)
      {u=sf1.consulte_formateur(comments.get(i).getId_user());
          
          
          cmt.setText("\n"+cmt.getText()+"\n"+comments.get(i).getComments()+":"+u.getEmail());
         }

    }
        @FXML
    void imprimer(ActionEvent event) {
sp.imprimer_liste_participant_par_form();
    }

    @FXML
    private void retour(ActionEvent event) {
           Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }

}
