/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import ethlete.PIDEVBIS;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import t2s.son.LecteurTexte;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class DashboardControllerMO implements Initializable {

    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
            
        // TODO
    }    

    @FXML
    private void GestionParticipation1(ActionEvent event) throws IOException {
                FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/participation.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Suivi Participation");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void GestionAffectation1(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/GererFormateur.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Suivi Participation");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void GestionFormation(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/GererFormation.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Suivi Participation");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void Gestioncommentaire1(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/ParticipationAdmin.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Suivi Participation");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void GestionAffectation2(ActionEvent event) throws IOException {
             FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/GererAff.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Suivi Participation");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void Gestioncommentaire2(ActionEvent event) throws IOException {
                FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/participation.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Suivi Participation");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void suivi(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/ParticipationAdmin.fxml"));

     
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Suivi Participation");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
}
