/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import models.Intervenant;
import services.ServiceIntervenant;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class AjouterIntervenantController implements Initializable {

    File file;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML 
    private TextField id_type;
    @FXML
    private ImageView imgIV;
    @FXML
    private ImageView logo;
    @FXML
    private TextField urlTF;
    @FXML
    private ComboBox<String> typeI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
try {
            
            
            Image image1 = new Image(new FileInputStream("C:\\Users\\21624\\Documents\\NetBeansProjects\\Ethlete\\src\\views\\elements\\logo.png"));
            logo.setImage(image1);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

 ObservableList<String> combo1 = FXCollections.observableArrayList("Invité","Sponsor","Organisateur");

        //hot lista f combobox
        typeI.setItems(combo1);


    }    

    @FXML
    private void ajouterIntervenant(ActionEvent event) throws IOException {
       
        
        
       String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pat = Pattern.compile(emailRegex);
        Matcher controler1 = pat.matcher(email.getText());
        
        String masque= "^[0..9]+[0..9]";
         Pattern pattern = Pattern.compile(masque);
        Matcher controler = pat.matcher(tel.getText());
        
        


         if (nom.getText().length() == 0 || prenom.getText().length() == 0  || email.getText().length() == 0  || tel.getText().length() == 0)
         {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("manque d informations");
            alert.show();

        } 
      /* else if (!controler.matches() || tel.getText().length()!=8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Entrez un num de Telephone valide");
            alert.show();
        }*/
       else if (controler1.matches()==false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("l'email n'est pas valide");
            alert.show();
        }
           else  {
            ServiceIntervenant si= new ServiceIntervenant();
        si.ajouterIntervenant(new Intervenant(urlTF.getText(),nom.getText(),prenom.getText(),email.getText(), Integer.parseInt(tel.getText()),typeI.getSelectionModel().getSelectedItem()));
        JOptionPane.showMessageDialog(null, "Intervenant Ajouté");
               
              FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/AfficherIntervenant.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Liste des Intervenant");
            stage.setScene(new Scene(root1));  
            stage.show();
        
    }
      
    }

    @FXML
    private void upload(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename =f.getAbsolutePath();
        urlTF.setText(filename);
   /*   Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage().getScaledInstance(urlTF.getWidth(), urlTF.getHeight(), Image.SCALE_SMOOTH);
        urlTF.setIcon(icon);*/
    }
}

