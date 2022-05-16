/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.AffectationFormateur;
import models.Formation;
import models.User;
import models.Utilisateur;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import services.ServiceAffectationFormateur;
import services.ServiceFormateur;
import services.ServiceFormation;
import services.ServiceReponseImp;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class GererFormateurController implements Initializable {
   ServiceAffectationFormateur af=new ServiceAffectationFormateur();
            ServiceFormation sf=new ServiceFormation();
            ServiceFormateur sf1=new ServiceFormateur();
              User u=new User(AuthentificationController.idglobal);

      @FXML
    private TableView<Formation> formations;
    @FXML
    private TableColumn<Formation, String> nom;
    @FXML
    private TableColumn<Formation, Date>  debut;
    @FXML
    private TableColumn<Formation, Date> fin;
   
    @FXML
    private ComboBox<String> filtrer;
    @FXML
    private Button afficher;
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;
    @FXML
    private TextField tfnom;
    @FXML
    private DatePicker tfdebut;
    @FXML
    private DatePicker tffin;
    @FXML
    private TextField tfdispo;
    @FXML
    private Button consulterProg;
 ObservableList<String> repStatus=FXCollections.observableArrayList("Accepté","non consulté","refusé");
    @FXML
    private JFXButton closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        filtrer.setItems(repStatus);
        showFormation() ;
    }    
   public void showFormation() {
        ObservableList<Formation> list =FXCollections.observableArrayList(af.consulter_toutes_affectation1((int) u.getId()));
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		
		formations.setItems(list);
	}
    @FXML
    private void filter(ActionEvent event) {
        List<AffectationFormateur> list =new ArrayList();
        list=af.afficher_aff_pre((int) u.getId());
        
        ObservableList<Formation> list1 =null;
        
        if(filtrer.getValue().equals("non consulté"))
{		
    
     list1 =FXCollections.observableArrayList(af.filtrer_formations_no_consulté(list));


}
        else {
        
                if(filtrer.getValue().equals("refusé"))
                    
                         list1 =FXCollections.observableArrayList(af.filtrer_formations_refusées(list));
 else
                         list1 =FXCollections.observableArrayList(af.filtrer_formations_acceptées(list));

                    

        }

        
        
        
        
        nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		
		formations.setItems(list1);
        
        
    }

    @FXML
    private void AfficherFormation(ActionEvent event) {
         Formation f=formations.getSelectionModel().getSelectedItem();
        tfnom.setText(f.getNom_formation());
tfdispo.setText(f.getDispositif());
tfdebut.setValue(f.getDate_debut().toLocalDate());
tffin.setValue(f.getDate_fin().toLocalDate());
   }

    @FXML
    private void AccepterFormation(ActionEvent event) {
                        ServiceReponseImp sr=new ServiceReponseImp();
                                      Formation f=formations.getSelectionModel().getSelectedItem();

af.accpter_affectation(u, f);
      JOptionPane.showMessageDialog(null,"Accepté" );

        
    }

    @FXML
    private void RefuserFormation(ActionEvent event) {
                 Formation f=formations.getSelectionModel().getSelectedItem();

af.refuser_affectation(u, f);
      JOptionPane.showMessageDialog(null,"Refusé" );

    }

    @FXML
    private void consulterProg(ActionEvent event) {
              Formation f=formations.getSelectionModel().getSelectedItem();
sf.generer_pdf(f);
	/* PDFViewer m_PDFViewer;
         m_PDFViewer = new PDFViewer();
		BorderPane borderPane = new BorderPane(m_PDFViewer);
		Scene scene = new Scene(borderPane);
                Stage stage = null;
		stage.setTitle("JavaFX PDFViewer - Qoppa Software");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();*/
           
    
    SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //applicationFrame.getContentPane().add(viewerComponentPanel);
        applicationFrame.add(viewerComponentPanel);
        
        controller.openDocument("C:\\Users\\MSI\\Desktop\\PI\\Ethlete\\catalogue"+f.getNom_formation()+".pdf");

        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }

    @FXML
    private void retour(ActionEvent event) {
           Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
}
