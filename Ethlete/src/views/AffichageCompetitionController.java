/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import models.Competition;
import services.competitionservice;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * FXML Controller class
 *
 * @author gaming
 */
public class AffichageCompetitionController implements Initializable {

    @FXML
    private TableView<Competition> comp;
    @FXML
    private TableColumn<Competition, Integer> nb_equipe;
    @FXML
    private TableColumn<Competition, String> nom;
    @FXML
    private TableColumn<Competition, String> date;
    @FXML
    private TableColumn<Competition, String> adresse;
    @FXML
    private Button sup;
    @FXML
    private Button modif;
    @FXML
    private Button add;
    @FXML
    private DatePicker dateE;
    @FXML
    private TextField nomE;
    @FXML
    private TextField nbrE;
    @FXML
    private TextField adrE;
    @FXML
    private TextField textfield;
    ObservableList<Competition> list1;
    @FXML
    private TextField textfield1;
    @FXML
    private Button btn1;
    private Button btn2;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       list = si.find();   
       afficher();     
       nbrE.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));

       nomE.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
    }    
    
    competitionservice si = new competitionservice();
        List<Competition> list = new ArrayList<>();
    public void afficher(){
    list = si.find();
        list1 =FXCollections.observableArrayList(list);          
        nb_equipe.setCellValueFactory(new PropertyValueFactory<Competition, Integer>("nb_equipe"));
        nom.setCellValueFactory(new PropertyValueFactory<Competition, String>("nom"));
        date.setCellValueFactory(new PropertyValueFactory<Competition, String>("date"));
        adresse.setCellValueFactory(new PropertyValueFactory<Competition, String>("adresse"));
    comp.setItems(FXCollections.observableArrayList(list1));
        FilteredList<Competition> filteredData = new FilteredList<>(list1, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		textfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (competition.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
			
				     else  
				    	 return false; // Does not match.
			});
		});
                SortedList<Competition> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(comp.comparatorProperty());
                FilteredList<Competition> filteredData1 = new FilteredList<>(sortedData, b -> true);
		textfield1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData1.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
			if (competition.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1)
				     return true;
			
				     else  
				    	 return false; // Does not match.
			});
		});
                	
		// 3. Wrap the FilteredList in a SortedList. 
	// 3. Wrap the FilteredList in a SortedList. 
		
		   SortedList<Competition> sortedData1 = new SortedList<>(filteredData1);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(comp.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		comp.setItems(sortedData1);

		//comp.setItems(sortedData);
    
}
    

    @FXML

    private void addE(ActionEvent event) {
        System.out.println(nbrE.getText());
        si.ajouter(new Competition(Integer.parseInt(nbrE.getText()),nomE.getText(),dateE.getValue().toString(),adrE.getText()));
        JOptionPane.showMessageDialog(null, "Competition Ajouté");
        Sendmail sn = new Sendmail();
        sn.envoyer("azer.khabthani@esprit.tn", nomE.getText(), nomE.getText()+" is happening at "+dateE.getValue().toString()+" near "+adrE.getText()+". "+nbrE.getText()+" teams will particiapte.");
        afficher();

    }
    @FXML

    private void modifE(ActionEvent event) {
             Competition e = new Competition(comp.getSelectionModel().getSelectedItem().getId_competition(),Integer.parseInt(nbrE.getText()),dateE.getValue().toString(),adrE.getText(),nomE.getText());
             
        System.out.println(e);
             si.modifier(e);
             afficher();
    }
    @FXML

    private void deleteE(ActionEvent event) {
        Competition e = comp.getSelectionModel().getSelectedItem();
        si.supprimer(e);
        afficher();
    }
    private void affE(ActionEvent event) {
        Competition e = comp.getSelectionModel().getSelectedItem();
        DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      // parsing the string to convert it into date
        LocalDate local_date = LocalDate.parse(e.getDate());
        dateE.setValue(local_date);
        nomE.setText(e.getNom());
        adrE.setText(e.getAdresse());
        nbrE.setText(Integer.toString(e.getNb_equipe()));
        
    }
        


 public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
return new EventHandler<KeyEvent>() {
@Override
public void handle(KeyEvent e) {
TextField txt_TextField = (TextField) e.getSource();                
if (txt_TextField.getText().length() >= max_Lengh) {                    
e.consume();
}
if(e.getCharacter().matches("[A-Za-z]")){ 
}else{
e.consume();
}
}
};
}
public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
return new EventHandler<KeyEvent>() {
@Override
public void handle(KeyEvent e) {
TextField txt_TextField = (TextField) e.getSource();                
if (txt_TextField.getText().length() >= max_Lengh) {                    
e.consume();
}
if(e.getCharacter().matches("[0-9.]")){ 
if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
e.consume();
}else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
e.consume(); 
}
}else{
e.consume();
}
}
};
}  
    @FXML

    private void handleButtonAction (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
        if(event.getSource()==btn1){
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("AffichageJourne.fxml"));
        }
        else{
            stage = (Stage) btn2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pdfs(ActionEvent event) throws Exception {
        competitionservice c = new competitionservice();
        c.pdfs();
    }

      @FXML

    private void retour(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    
    }




      
}