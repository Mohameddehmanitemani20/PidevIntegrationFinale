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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Competition;
import models.Evenement;
import models.Formation;
import services.ServiceEvenement;
import services.ServiceFormation;
import services.competitionservice;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class EvenementFXMLController implements Initializable {
    
    Evenement event = null;
    Formation formation = null;
    Competition competition = null;
    
    ServiceEvenement se = new ServiceEvenement();
    ServiceFormation sf = new ServiceFormation();
    competitionservice sc = new competitionservice();

    @FXML
    private Label nomeventLBL;
    @FXML
    private Label idEventLBL;
    @FXML
    private Label dateDebLBL;
    @FXML
    private Label dateFinLBL;
    @FXML
    private Label typeEventLBL;
    @FXML
    private Label lieuLBL;
    @FXML
    private Label nomCompetFormLBL;
    @FXML
    private Label prixLBL;
    @FXML
    private Label idCompetFormLBL;
    @FXML
    private JFXButton participerJFXBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Evenement e = new Evenement();
      e.setId_event(13);
        setData(e);
    }    
    public void setData(Evenement e){
        this.event = se.retrieveEvent(e.getId_event());
        idEventLBL.setText(String.valueOf(this.event.getId_event()));
        nomeventLBL.setText(this.event.getNom_event());
        dateDebLBL.setText(String.valueOf(this.event.getDate_debut()));
        dateFinLBL.setText(String.valueOf(this.event.getDate_fin()));
        typeEventLBL.setText(this.event.getTypeE());
        lieuLBL.setText(this.event.getLieu());
        prixLBL.setText(String.valueOf(this.event.getPrixU()));
        if(this.event.getTypeE().equals("Formation")){
            this.formation = sf.retrieveFormation(this.event.getId_formation());
            idCompetFormLBL.setText(String.valueOf(this.formation.getId_formation()));
            nomCompetFormLBL.setText(this.formation.getNom_formation());
        }
        if(this.event.getTypeE().equals("Compétition")){
            this.competition = sc.retrieveCompetition(this.event.getId_compet());
            idCompetFormLBL.setText(String.valueOf(this.competition.getId_competition()));
            nomCompetFormLBL.setText(this.competition.getNom());
        }
        
    }

    @FXML
    private void onParticiperClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AcheterBillet.fxml"));
        Parent root = loader.load();
        AcheterBilletController acheterController = loader.getController();
        acheterController.setData(this.event);
        Scene scene=new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
