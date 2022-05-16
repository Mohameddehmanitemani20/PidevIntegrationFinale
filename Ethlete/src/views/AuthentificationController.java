/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Role;
import models.User;
import services.CryptWithMD5;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.Smsapi;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AuthentificationController implements Initializable {

    @FXML
    private AnchorPane mainLayer;
    
    @FXML
    private TextField emailTF;
    
    @FXML
    private TextField adresseTF;
    
    private JFXButton uploadLogoBtn;
    private JFXButton nextBtn;
    @FXML
    private TextField loginTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Label settingLB1;
    @FXML
    private JFXButton valSignInBtn;
    @FXML
    private Label closeLB;
    @FXML
    private ImageView userIcon;
    @FXML
    private ImageView passwordIcon;
    @FXML
    private ImageView numTel2Icon;
    @FXML
    private ImageView matFIcon;
    @FXML
    private ImageView deviseIcon;
    @FXML
    private ImageView numtel1Icon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private ImageView paysIcon;
    @FXML
    private ImageView adresseIcon;
    @FXML
    private ImageView codePostalIcon;
    @FXML
    private ImageView villeIcon;
    @FXML
    private ImageView rsIcon;
    @FXML
    private Circle reduceBtn;
    @FXML
    private Circle closeBtn;
    private Label fileName;
    @FXML
    private AnchorPane animLayer;
    @FXML
    private Label varLB;
    @FXML
    private JFXButton signUpBtn;
    private JFXButton signInBtn;
    @FXML
    private Label helloLB;
    @FXML
    private DatePicker dpdate;
    @FXML
    private AnchorPane defaultLayer;
    UserService us=new UserService();

    /**
     * Initializes the controller class.
     */
     ObservableList<String> genreStatus=FXCollections.observableArrayList("homme","femme");

    boolean x=false;
    @FXML
    private JFXButton swapbtn;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField numTelTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField usernametf;
    @FXML
    private JFXButton signupbtn;
    @FXML
    private Button btnfp;
    public static long idglobal;
    @FXML
    private ComboBox<Role> comborole1;
    @FXML
    private ComboBox<String> combogenre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                comborole1.getItems().setAll(Role.values());
combogenre.setItems(genreStatus);
        varLB.setVisible(false);
        loginTF.setVisible(false);
        passwordTF.setVisible(false);
        valSignInBtn.setVisible(false);
        btnfp.setVisible(false);
        userIcon.setVisible(false);
        passwordIcon.setVisible(false);
        signUpBtn.setVisible(false);
        swapbtn.setText("Login");
    }    

private void resetSignUpTF() {
        usernametf.setText("");
        numTelTF.setText("");
        emailTF.setText("");
        nomtf.setText("");
        adresseTF.setText("");
        prenomtf.setText("");
        dpdate.setValue(null);
        passwordPF.setText("");
        

    }

    @FXML
    private void closeScene(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void reduceOnClick(MouseEvent event) {
        Stage currentStage;
        currentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow().getScene().getWindow();
        currentStage.setIconified(true);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Nextec application ");
        tray.setMessage("Application is still running at the backround.");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(5000));
    }

    @FXML
    private void closeOnClick(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void signUpForm(MouseEvent event) {
    }
    private void resetSignInTF() {
           loginTF.setText("");
           passwordTF.setText("");
       }
    private void setIconVisibility(boolean state) {
        rsIcon.setVisible(state);
        numTel2Icon.setVisible(state);
        matFIcon.setVisible(state);
        deviseIcon.setVisible(state);
        numtel1Icon.setVisible(state);
        emailIcon.setVisible(state);
        paysIcon.setVisible(state);
        adresseIcon.setVisible(state);
        codePostalIcon.setVisible(state);
        villeIcon.setVisible(state);

    }
    @FXML
    private void swapForm(MouseEvent event) {
       
        if(x==false)
        {
            
            System.out.println("form swapped");
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(animLayer);

            mainLayer.setTranslateX(-400);

            //Resetting textFields
            resetSignInTF();

            //Hiding Icons
            setIconVisibility(false);

            //TODO : Hide old cmpts show login cpts
            swapbtn.setText("SignUp");
            settingLB1.setText("Login");
            nomtf.setVisible(false);
            numTelTF.setVisible(false);
            dpdate.setVisible(false);
            emailTF.setVisible(false);
            usernametf.setVisible(false);
            adresseTF.setVisible(false);
            prenomtf.setVisible(false);
            passwordPF.setVisible(false);
            signupbtn.setVisible(false);
            varLB.setVisible(true);
            helloLB.setVisible(false);
comborole1.setVisible(false);
combogenre.setVisible(false);
            //Login and password TF
           
            userIcon.setVisible(true);
            passwordIcon.setVisible(true);
            loginTF.setVisible(true);
            passwordTF.setVisible(true);
            valSignInBtn.setVisible(true);
            btnfp.setVisible(true);


            slide.setToX(689);
            slide.play();
            slide.setOnFinished((e -> {

            }));
            x=true;
        }
        else{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(animLayer);

            mainLayer.setTranslateX(0);

            resetSignUpTF();

            //Shwoing Icons
            setIconVisibility(true);
            swapbtn.setText("Login");
            settingLB1.setText("SignUP");

            signUpBtn.setVisible(false);
            usernametf.setVisible(true);
            numTelTF.setVisible(true);

            emailTF.setVisible(true);
            nomtf.setVisible(true);
            adresseTF.setVisible(true);
            prenomtf.setVisible(true);
            passwordPF.setVisible(true);
            signupbtn.setVisible(true);
            dpdate.setVisible(true);
            varLB.setVisible(false);
            helloLB.setVisible(true);
comborole1.setVisible(true);
combogenre.setVisible(true);
            
            loginTF.setVisible(false);
            passwordTF.setVisible(false);
            valSignInBtn.setVisible(false);
            btnfp.setVisible(false);
            userIcon.setVisible(false);
            passwordIcon.setVisible(false);
            //fileName.setVisible(true);

            //applyValidators();

            slide.setToX(0);

            slide.play();

            slide.setOnFinished((e -> {

            }));
            x=false;
        }
        
    }
    private void makeStageDragable() {
       
    }

    @FXML
    private void performLogIn(MouseEvent event) {
        /*if (rs.getString("email").equals(t.getEmail()) && BCrypt.checkpw(t.getMotpasse(), rs.getString("motpasse")) == true) {*/
        /*if(us.checklogin(loginTF.getText(), CryptWithMD5.cryptWithMD5(passwordTF.getText())))*/
        if(us.checklogin(loginTF.getText(), CryptWithMD5.cryptWithMD5(passwordTF.getText()))){
            //7ell interface
            User u=us.findByUsername(loginTF.getText());
            idglobal=u.getId();
            if(u.getRole().equals(Role.ROLE_ADMIN)){
                try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Dashbord Admin");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }
            else if(u.getRole().equals(Role.ROLE_JOUEUR) || u.getRole().equals(Role.ROLE_USER)){
                       try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/MenuJoueur.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Article");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        }
            else if(u.getRole().equals(Role.ROLE_FORMATEUR)){
                         try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/MenuFormateur.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Dashbord Formateur");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        } 
                   else if(u.getRole().equals(Role.ROLE_RESPONSABLE)){
                         try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/MenuResponsable.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Dashbord Responsable");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        } 
          /*  else if(u.getRole().equals(Role.JOUEUR)){
                        System.out.println("bienveunue joueur");
                        }*/
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login fail");
            alert.setContentText("Username or password invalid");
            alert.showAndWait();
        }
    }

    @FXML
    private void performeSignup(MouseEvent event) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        StringBuilder errors=new StringBuilder();
        if(nomtf.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name\n");//string s --- s+="erreur"
        }
        if(prenomtf.getText().trim().isEmpty()){
           
            errors.append("- Please enter a Last Name\n");
        }
        if(emailTF.getText().trim().isEmpty()){
            errors.append("- Please enter a Email\n");
        }
        if (pat.matcher(emailTF.getText()).matches() == false){
            errors.append("- please enter a valid email");
        }
        
        if(usernametf.getText().trim().isEmpty()){
            errors.append("- Please enter a Username\n");
        }
        if(passwordPF.getText().trim().isEmpty()){
            errors.append("- Please enter a Password\n");
        }
        if(adresseTF.getText().trim().isEmpty()){
            errors.append("- Please enter Adress\n");
        }
        if(numTelTF.getText().trim().isEmpty()){
            errors.append("- Please enter a Phone number\n");
        }
        if(dpdate.getValue()==null){
            errors.append("- Please enter a Birthday\n");
        }
        try{
            Integer.parseInt(numTelTF.getText());
        }catch(NumberFormatException e){
            errors.append("- Please enter a valid number\n");
        }
        if(us.usernameExist(usernametf.getText())){
            errors.append("- Username already exist");
        }
       
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
            User u =new User();
            u.setAdresse(adresseTF.getText());
            u.setDate_naissance(Date.valueOf(dpdate.getValue()));
            u.setEmail(emailTF.getText());
            u.setNom(nomtf.getText());
            u.setNumTel(Integer.parseInt(numTelTF.getText()));
            u.setPassword(passwordPF.getText());
            u.setPrenom(prenomtf.getText());
            u.setUsername(usernametf.getText());
            u.setRole(comborole1.getValue());
            u.setGenre(combogenre.getValue());
            us.ajouter(u);
           // Smsapi.sendSMS("", "Bienvenue chez nextec");
            TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Signup Success");
            tray.setMessage("You successufuly signin in our application");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            
        }
        
    }

    @FXML
    private void forgotpassword(ActionEvent event) {
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/FXMLforgotpassword.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("reset password");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
