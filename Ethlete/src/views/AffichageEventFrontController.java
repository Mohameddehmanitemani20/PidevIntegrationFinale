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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Evenement;
import services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class AffichageEventFrontController implements Initializable {
    
    ServiceEvenement se = new ServiceEvenement();

    @FXML
    private GridPane evGP;
    @FXML
    private ScrollPane scrollPaneID;
    @FXML
    private JFXButton closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadGP();
    }
     @FXML
    private void retour(ActionEvent event) {
     Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();  }
    
    //LOAD DATA IN GRIDPANE
    private void loadGP(){
        int maxColumns = 2;
        /*if(ajoutModifPanel.isVisible()){
            maxColumns = 2;
            
        }else{
            maxColumns = 3;
            
        }*/      
        int column = 0;
        int row = 0;
        
        evGP.getChildren().clear();
    
        for (Evenement ev : se.afficherEvenement()){
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../views/evenementFXML.fxml"));
            
                AnchorPane anchorPane = fxmlLoader.load();
                
            
            EvenementFXMLController evController = fxmlLoader.getController();
            evController.setData(ev);
            
            
            if(column == maxColumns){
                column = 0;
                row++;
            }
            
            
            evGP.add(anchorPane,column,row);
            column++;
            
                
            } catch (IOException ex) {
                
                ex.printStackTrace();
            }
            
        }
//        for(int i=0;i<evGP.getRowConstraints().size();i++)
//            evGP.getRowConstraints().get(i).setMinHeight(250);

    }

    @FXML
    private void onGP_clicked(MouseEvent event) {
//        Node source = (Node)event.getTarget() ;
//        
//        if(source.getParent() instanceof JFXButton){
//            
//            Evenement targetPoint = getDataFromScene(source.getParent());
//            System.out.println(targetPoint);
//        }
        
        
    }
    
    private Evenement getDataFromScene(Parent scene){
        Evenement e = new Evenement();
        Label idEventLBL = (Label) scene.lookup("#idEventLBL");
        e.setId_event(Integer.parseInt(idEventLBL.getText()));
        
        return e;
    }
    
}
