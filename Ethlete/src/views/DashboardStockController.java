/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardStockController implements Initializable {

    @FXML
    private Circle c2;
    @FXML
    private Circle c3;
    @FXML
    private Circle c1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rotate(c2);
      Rotate(c3);
      Rotate(c1);
      
    }

    @FXML
    private void gotoproduit(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AceuilStock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    private void gotocommande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajouterCommande.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void gotofournisseurs(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("fournisseur.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void gotodashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
public void Rotate(Circle c){
       RotateTransition rt = new RotateTransition(Duration.millis(3000),c);
     rt.setByAngle(180);
     rt.setCycleCount(10);
     rt.setAutoReverse(true);
 
     rt.play();

}
    
    
}
