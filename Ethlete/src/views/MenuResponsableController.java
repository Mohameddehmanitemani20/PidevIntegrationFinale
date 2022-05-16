/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class MenuResponsableController implements Initializable {

    @FXML
    private JFXButton feedback;
    @FXML
    private JFXButton aff;
    @FXML
    private JFXButton part;
    @FXML
    private JFXButton feedback1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void feedback(ActionEvent event) throws IOException {
                  FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/GestionAvis.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Avis");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void aff(ActionEvent event) throws IOException {
                  FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/GestionEquipe.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MAJ PROFIL");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void part(ActionEvent event) throws IOException {
                      FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/ReponseInvitation.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MAJ PROFIL");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void MAJPROFIL(ActionEvent event) throws IOException {
                      FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/UpdateProfil.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MAJ PROFIL");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
}
