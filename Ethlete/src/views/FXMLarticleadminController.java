/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import models.Article;
import services.ArticleService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FXMLarticleadminController implements Initializable {

    @FXML
    private ListView<Article> listviewarticle;
    @FXML
    private TextField tfrecherche;
    ArticleService as=new ArticleService();
    ObservableList<Article> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
        recherche_avance();
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        as.supprimer(listviewarticle.getSelectionModel().getSelectedItem().getIdAticle());
         TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Delete Success");
            tray.setMessage("Article is deleted");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(1000));
        refreshlist();
    }
    public void refreshlist(){
        data=FXCollections.observableArrayList(as.afficher());
        listviewarticle.setItems(data);
    }
    public void recherche_avance(){
        FilteredList<Article> filtereddata=new FilteredList<>(data,b->true);
        tfrecherche.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(article->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(article.getContenu().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(article.getDescription().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(article.getTitre().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(article.getNbrLike()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
     
                else{
                    return false;
                }
                
            });
        });
        
        listviewarticle.setItems(filtereddata);
    }
}
