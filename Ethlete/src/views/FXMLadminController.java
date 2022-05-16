/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package views;



import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.print.Paper.C;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Role;
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
public class FXMLadminController implements Initializable {

    @FXML
    private TableView<User> tableviewuser;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField addresstf;
    @FXML
    private PasswordField passwordpf;
    @FXML
    private TextField numerotf;
    @FXML
    private ComboBox<Role> comborole;
    @FXML
    private TextField recherchetf;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private DatePicker dpdate;
    ObservableList<User> data=FXCollections.observableArrayList();
    UserService us =new UserService();
    @FXML
    private TableColumn<User, String> nom_col;
    @FXML
    private TableColumn<User, String> prenom_col;
    @FXML
    private TableColumn<User, String> email_col;
    @FXML
    private TableColumn<User, String> username_col;
    private TableColumn<User, String> password_col;
    @FXML
    private TableColumn<User, String> add_col;
    @FXML
    private TableColumn<User, Date> date_col;
    @FXML
    private TableColumn<User, Integer> numero_col;
    @FXML
    private TableColumn<User, Role> role_col;
    long id_modif;
    String username_modif;
    @FXML
    private Button btntri;
    ObservableList<String> ss=FXCollections.observableArrayList();
    @FXML
    private Button dahmoun;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ss.add("Par nom");
        ss.add("Par date");
        // TODO
        comborole.getItems().setAll(Role.values());
        combotri.setItems(ss);
        refreshlist();
        recherche_avance();
        
    }    
   
    public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(us.afficher());
        nom_col.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        
        email_col.setCellValueFactory(new PropertyValueFactory<>("Email"));
        username_col.setCellValueFactory(new PropertyValueFactory<>("Username"));
        
        add_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        date_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        numero_col.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("Role"));
        tableviewuser.setItems(data);
    }
    @FXML
    private void ajouterUser(ActionEvent event) {
        
        
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
        if(emailtf.getText().trim().isEmpty()){
            errors.append("- Please enter a Email\n");
        }
        if(usernametf.getText().trim().isEmpty()){
            errors.append("- Please enter a Username\n");
        }
        if(passwordpf.getText().trim().isEmpty()){
            errors.append("- Please enter a Password\n");
        }
        if(addresstf.getText().trim().isEmpty()){
            errors.append("- Please enter Adress\n");
        }
        if(numerotf.getText().trim().isEmpty()){
            errors.append("- Please enter a Phone number\n");
        }
        if(dpdate.getValue()==null){
            errors.append("- Please enter a Birthday\n");
        }
        if (pat.matcher(emailtf.getText()).matches() == false){
            errors.append("- please enter a valid email");
        }
        try{
            Integer.parseInt(numerotf.getText());
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
            u.setAdresse(addresstf.getText());
            u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setEmail(emailtf.getText());
            u.setNom(nomtf.getText());
            u.setNumTel(Integer.parseInt(numerotf.getText()));
            u.setPassword(passwordpf.getText());
            u.setPrenom(prenomtf.getText());
            u.setUsername(usernametf.getText());
            u.setRole(comborole.getValue());
            us.ajouter(u);
            TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Added user Success");
            tray.setMessage("You successufuly added user in ur application");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        }
        refreshlist();
    }

    @FXML
    private void modifierUser(ActionEvent event) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        User u=new User();
        StringBuilder errors=new StringBuilder();
        if(nomtf.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name\n");//string s --- s+="erreur"
        }
        if(prenomtf.getText().trim().isEmpty()){
           
            errors.append("- Please enter a Last Name\n");
        }
        if(emailtf.getText().trim().isEmpty()){
            errors.append("- Please enter a Email\n");
        }
         if (pat.matcher(emailtf.getText()).matches() == false){
            errors.append("- please enter a valid email");
        }
        
        if(usernametf.getText().trim().isEmpty()){
            errors.append("- Please enter a Username\n");
        }
        if(passwordpf.getText().trim().isEmpty()){
            errors.append("- Please enter a Password\n");
        }
        if(addresstf.getText().trim().isEmpty()){
            errors.append("- Please enter Adress\n");
        }
        if(numerotf.getText().trim().isEmpty()){
            errors.append("- Please enter a Phone number\n");
        }
        if(dpdate.getValue()==null){
            errors.append("- Please enter a Birthday\n");
        }
        try{
            Integer.parseInt(numerotf.getText());
        }catch(NumberFormatException e){
            errors.append("- Please enter a valid number\n");
        }
       /* if(us.usernameExist(usernametf.getText()) && usernametf.getText().equals(username_modif)){
            errors.append("- Username already exist");
        }*/
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
            u.setAdresse(addresstf.getText());
            u.setDate_naissance(java.sql.Date.valueOf(dpdate.getValue()));
            u.setEmail(emailtf.getText());
            u.setNom(nomtf.getText());
            u.setNumTel(Integer.parseInt(numerotf.getText()));
            u.setPassword(passwordpf.getText());
            u.setPrenom(prenomtf.getText());
            u.setUsername(usernametf.getText());
            u.setRole(comborole.getValue());
            us.modifier(id_modif,u);
            TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Update user Success");
            tray.setMessage("You successufuly updated user in ur application");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(1000));
        }
        refreshlist();
        
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
        
        String username=tableviewuser.getSelectionModel().getSelectedItem().getUsername();
        User u=us.findByUsername(username);
        us.supprimer(u.getId());
        TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Delete Success");
            tray.setMessage("User is deleted");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(1000));
            refreshlist();
    }


    
    @FXML
    private void fillforum() {
        
        String username=tableviewuser.getSelectionModel().getSelectedItem().getUsername();
        User u=us.findByUsername(username);
        id_modif=u.getId();
        
        nomtf.setText(u.getNom());
        prenomtf.setText(u.getPrenom());
        emailtf.setText(u.getEmail());
        //passwordpf.setText(u.getPassword());
        usernametf.setText(u.getUsername());
        addresstf.setText(u.getAdresse());
        //date to localdate
        Instant instant = Instant.ofEpochMilli(u.getDate_naissance().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        dpdate.setValue(ldt.toLocalDate());
        numerotf.setText(Integer.toString(u.getNumTel()));
        comborole.setValue(u.getRole());
        username_modif=u.getUsername();
        
        
    }
    public void recherche_avance(){
        FilteredList<User> filtereddata=new FilteredList<>(data,b->true);
        recherchetf.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(user->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(user.getNom().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getPrenom().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getAdresse().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getEmail().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(user.getNumTel()).indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getRole().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getUsername().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getDate_naissance().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else{
                    return false;
                }
                
            });
        });
        SortedList<User> sorteddata=new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewuser.comparatorProperty());
        tableviewuser.setItems(filtereddata);
        
    }

    
    @FXML
    private void trilist(ActionEvent event) {
        if(combotri.getValue().equals("Par nom")){
            ObservableList<User> tri1=FXCollections.observableArrayList();
            tri1=FXCollections.observableArrayList(us.sortByNom());
            tableviewuser.setItems(tri1);
            
        }
        else if(combotri.getValue().equals("Par date")){
            ObservableList<User> tri2=FXCollections.observableArrayList();
            tri2=FXCollections.observableArrayList(us.sortByDate());
            tableviewuser.setItems(tri2);
        }
    }

    @FXML
    private void consulterArticle(ActionEvent event) {
          try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/FXMLarticleadmin.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Dashbord Admin");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consulterPosition(ActionEvent event) {
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("../views/MapFxml.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Dashbord Admin");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void GoToDashboard(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) dahmoun.getScene().getWindow();
    stage.close(); 
    }

  
    
}
