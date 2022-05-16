/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ethlete;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import t2s.son.LecteurTexte;

/**
 *
 * @author pc
 */
public class PIDEVBIS extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       FXMLLoader loader =new FXMLLoader(getClass().getResource("../views/Authentification.fxml"));
//oueslati formateur
//yasmine joueur

//admin admin
// anas responsable
     Parent root=loader.load();
     Scene scene=new Scene(root);
     primaryStage.setTitle("NEXTEX ");
     primaryStage.setScene(scene);
     primaryStage.show();
     
       /* FXMLLoader loader =new FXMLLoader(getClass().getResource("../views/GererAff.fxml"));

     Parent root=loader.load();
     Scene scene=new Scene(root);
     primaryStage.setTitle("moatez");
     primaryStage.setScene(scene);
     primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
      }
    
}
