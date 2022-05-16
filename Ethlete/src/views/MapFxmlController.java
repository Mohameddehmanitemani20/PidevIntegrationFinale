/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class MapFxmlController implements Initializable {

    @FXML
    private WebView webmap;
    private WebEngine webengine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  webengine = webmap.getEngine();

        url = this.getClass().getResource("map/index.html");
        webengine.load(url.toString());

    }    

    @FXML
    private void Btn(ActionEvent event) {
        
        Object latitude = (Object) webmap.getEngine().executeScript("lat");
        Object longitude = (Object) webmap.getEngine().executeScript("lon");
           
             
         System.out.println("Lat AjoutCom: " + latitude);
         System.out.println("LOn AjoutCom: " + longitude);
    }
    
}
