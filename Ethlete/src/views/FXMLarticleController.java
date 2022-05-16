/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Article;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FXMLarticleController implements Initializable {

    @FXML
    private ListView<Article> listviewarticle;
    ArticleService as=new ArticleService();
    ObservableList<Article> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
    }    

    @FXML
    private void like(ActionEvent event) {
        Article a=new Article();
        a=listviewarticle.getSelectionModel().getSelectedItem();
        if(a!=null){
            int x=listviewarticle.getSelectionModel().getSelectedItem().getNbrLike();
            a.setNbrLike(x+=1);
            as.modifier(a.getIdAticle(), a);
        }
        
        
       
        refreshlist();        
    }
    public void refreshlist(){
        data=FXCollections.observableArrayList(as.sortByNbrLike());
        listviewarticle.setItems(data);
    }

    @FXML
    private void ajouterarticle(ActionEvent event) {
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/FXMLajouterarticle.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("signup");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
