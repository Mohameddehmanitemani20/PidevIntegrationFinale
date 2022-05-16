/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.AffectationFormateur;
import models.Commentaire;
import models.Formation;
import models.Participation;
import models.User;
import models.Utilisateur;
import services.ServiceCommentaireImp;
import services.ServiceFormateur;
import services.ServiceFormation;
import services.ServiceParticipation;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ParticipationController implements Initializable {
            ServiceFormation sf=new ServiceFormation();
ServiceParticipation sp=new ServiceParticipation();
ServiceFormateur sf1=new ServiceFormateur();
User u=new User(AuthentificationController.idglobal);
@FXML
    private Button supp;
      @FXML
    private TableView<Formation> formations;
    @FXML
    private TableColumn<Formation, String> nom;
    @FXML
    private TableColumn<Formation, Date>  debut;
    @FXML
    private TableColumn<Formation, Date> fin;
    @FXML
    private TableColumn<Formation, String> dis;
    @FXML
    private TableColumn<Formation, String> prog;
    @FXML
    private ComboBox<String> filtrer;
    @FXML
    private Button participer;
  ObservableList<String> filterStatus=FXCollections.observableArrayList("Mes Participations","En_ligne","Présentiel");
    @FXML
    private Button comments;
    @FXML
    private JFXButton closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showFormation();   filtrer.setItems(filterStatus);
    }    
     public void showFormation() {
		ObservableList<Formation> list =FXCollections.observableArrayList( sf.afficher());
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
		formations.setItems(list);
	}
      public static final String ACCOUNT_SID = "AC215414247f26d9a12eaae32bfa5b07fa"; 
    public static final String AUTH_TOKEN = "e08afeca8078877fd224934efb9b83cb"; 
    @FXML
    void participerForm(ActionEvent event) {
        Formation f=formations.getSelectionModel().getSelectedItem();
        
        if(!exist(f))
        {sp.Participer_Une_Formation(f, u);
/*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
             

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21624030100"),
                new com.twilio.type.PhoneNumber("+19803755136"),
                "Vous êtes inscrits à   "+f.getNom_formation())
            .create();

        System.out.println(message.getSid());
 */
         JOptionPane.showMessageDialog(null,"succés" );}
        else
        {  TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Erreur");
            tray.setMessage("Déjà participé");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1000));}

    }
     public boolean exist(Formation f)
            
    { List<Participation> list2=new ArrayList();
    list2=sp.afficher();
        
        for (int i=0;i<list2.size();i++)
        if(list2.get(i).getId_participant()==u.getId() && list2.get(i).getFormation_id()==f.getId_formation())
            return true;
        return false;
        
    }
    
      
       @FXML
    void filter(ActionEvent event) {
        ObservableList<Formation> list ;
        
if(filtrer.getValue().equals("Mes Participations"))
{list =FXCollections.observableArrayList( sp.consulter_formations_Part(u));

supp.setDisable(false);
}
else
   
{ if(filtrer.getValue().equals("En_ligne"))
            list =FXCollections.observableArrayList( sf.filtrer_form_En_Ligne());
   else
            list =FXCollections.observableArrayList( sf.filtrer_form_Presentiel());}

nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
		formations.setItems(list);
                

    }
    
    @FXML
    void supprimerpart(ActionEvent event) {
        Formation f=formations.getSelectionModel().getSelectedItem();
sp.annuler_participation_précise(u, f);
         JOptionPane.showMessageDialog(null,"Participation supprimé" );
         showFormation();

    }
      @FXML
    private TextArea comment;
    
     @FXML
    void commenter(ActionEvent event) {
        Formation f=formations.getSelectionModel().getSelectedItem();
ServiceCommentaireImp sc=new ServiceCommentaireImp();
sc.ajouter(new Commentaire(comment.getText(), (int) u.getId(),f.getId_formation()));
         JOptionPane.showMessageDialog(null,"Commentaire Envoyé" );



    }

    @FXML
    private void retour(ActionEvent event) {
           Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
      
}
