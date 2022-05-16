/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.User;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class UpdateProfilController implements Initializable {

    @FXML
    private JFXTextField nomtf;
    @FXML
    private JFXTextField numTeltf;
    @FXML
    private JFXTextField emailtf;
    @FXML
    private JFXTextField prenomtf;
    @FXML
    private JFXTextField adressetf;
    @FXML
    private JFXTextField usernametf;
    @FXML
    private JFXDatePicker datetf;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXPasswordField passwordpf;
    long id_modif;
    @FXML
    private JFXButton saveBtn1;
    @FXML
    private JFXButton dahmounrjaa;
    @FXML
    private JFXButton dahmounrjaa1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveBtnOnClick(MouseEvent event) {
           UserService us=new UserService();
           User u=us.findById(AuthentificationController.idglobal);
        
            u.setAdresse(adressetf.getText());
            u.setDate_naissance(java.sql.Date.valueOf(datetf.getValue()));
            u.setEmail(emailtf.getText());
            u.setNom(nomtf.getText());
            u.setNumTel(Integer.parseInt(numTeltf.getText()));
            u.setPassword(passwordpf.getText());
            u.setPrenom(prenomtf.getText());
            u.setUsername(usernametf.getText());
           
            us.modifier(AuthentificationController.idglobal,u);
            TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Update user Success");
            tray.setMessage("You successufuly updated user in ur application");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(1000));
        
    }

    @FXML
    private void fillform(MouseEvent event) {
        
        UserService us=new UserService();
        User u=us.findById(AuthentificationController.idglobal);
        
        
        nomtf.setText(u.getNom());
        prenomtf.setText(u.getPrenom());
        emailtf.setText(u.getEmail());
        //passwordpf.setText(u.getPassword());
        usernametf.setText(u.getUsername());
        adressetf.setText(u.getAdresse());
        //date to localdate
        Instant instant = Instant.ofEpochMilli(u.getDate_naissance().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        datetf.setValue(ldt.toLocalDate());
        numTeltf.setText(Integer.toString(u.getNumTel()));
        String username_modif = u.getUsername();
        
    }

    @FXML
    private void retour(ActionEvent event) {
        
        Stage stage = (Stage) dahmounrjaa.getScene().getWindow();
    stage.close(); 
    }
    
}
