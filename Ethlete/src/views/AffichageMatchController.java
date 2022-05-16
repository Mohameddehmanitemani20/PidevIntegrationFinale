/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import models.Competition;
import models.Match;
import services.journeservice;
import services.matchservice;
import models.Journe;
/**
 * FXML Controller class
 *
 * @author gaming
 */
public class AffichageMatchController implements Initializable {

    @FXML
    private TableColumn<Match, String> equipe1;
    @FXML
    private TableColumn<Match, String> equipe2;
    @FXML
    private TableColumn<Match, String> etat;
    @FXML
    private Button sup;
    @FXML
    private Button modif;
    @FXML
    private Button add;
    @FXML
    private TextField equipe1M;
    @FXML
    private TextField equipe2M;
    @FXML
    private ComboBox<String> etatM;
    @FXML
    private TableView<Match> matchv;
    private ObservableList<String> listE = FXCollections.observableArrayList("Fini","En cours");
    ObservableList<Match> list1;
    @FXML
    private TextField textfield;
    @FXML
    private TextField textfield1;
    @FXML
    private Button btn1;
    @FXML
    private ComboBox<Journe> journep;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            journeservice jms = new journeservice();


                 list = si.find();

        afficher();
         //numvald
       equipe2M.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
       equipe1M.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
        
       //combobox
       journep.setItems(FXCollections.observableList(jms.getlist()));
       journep.setConverter(new StringConverter<Journe>() {

    @Override
    public String toString(Journe object) {
        return Integer.toString(object.getNumJourne());
    }

    @Override
    public Journe fromString(String string) {
        return journep.getItems().stream().filter(ap -> 
            Integer.toString(ap.getNumJourne()).equals(string)).findFirst().orElse(null);
    }
});
        journep.valueProperty().addListener((obs, oldval, newval) -> {
    if(newval != null)
        System.out.println("Selected Journée: " + newval.getNumJourne()
            + ". ID: " + newval.getId_journe());
});
       etatM.setItems(listE);
        etatM.setValue("Fini");
        etatM.setValue("En cours");
        // TODO
    }       
    
    matchservice si = new matchservice();
        List<Match> list = new ArrayList<>();
public void afficher(){
    list = si.find();
    System.out.println(list);
    list1 =FXCollections.observableArrayList(list);
    equipe1.setCellValueFactory(new PropertyValueFactory<Match, String>("equipe1"));
    equipe2.setCellValueFactory(new PropertyValueFactory<Match, String>("equipe2"));
    etat.setCellValueFactory(new PropertyValueFactory<Match, String>("etat"));
   
    
    matchv.setItems(FXCollections.observableArrayList(list1));
        FilteredList<Match> filteredData = new FilteredList<>(list1, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		textfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (competition.getEquipe1().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (competition.getEquipe2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
			
				     else  
				    	 return false; // Does not match.
			});
		});
                SortedList<Match> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(matchv.comparatorProperty());
                FilteredList<Match> filteredData1 = new FilteredList<>(sortedData, b -> true);
		textfield1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData1.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
			if (competition.getEquipe2().toLowerCase().indexOf(lowerCaseFilter) != -1)
				     return true;
			
				     else  
				    	 return false; // Does not match.
			});
		});
                	
		// 3. Wrap the FilteredList in a SortedList. 
	// 3. Wrap the FilteredList in a SortedList. 
		
		   SortedList<Match> sortedData1 = new SortedList<>(filteredData1);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(matchv.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		matchv.setItems(sortedData1);

		//comp.setItems(sortedData);    
}
       @FXML
 
    private void addM(ActionEvent event) {
        si.ajouter(new Match(equipe1M.getText(),equipe2M.getText(),journep.getValue().getId_journe()));
        Sendmail sn = new Sendmail();
        sn.envoyer("azer.khabthani@esprit.tn","Ethlete Invitation to Match","Your Team "+equipe1M.getText()+" will be playing against "+equipe2M.getText()+" Good Luck .");
        JOptionPane.showMessageDialog(null, "Match Ajouté");
        afficher();

    }
        @FXML

    private void modifM(ActionEvent event) {
             System.out.println(etat.getText());
        Match e = new Match(matchv.getSelectionModel().getSelectedItem().getId_match(),equipe1M.getText(),equipe2M.getText(),etatM.getValue().toString());
             
        
             si.modifier(e);
             afficher();
    }
        @FXML

    private void deleteM(ActionEvent event) {
        Match e = matchv.getSelectionModel().getSelectedItem();
        si.supprimer(e);
        afficher();
    }
    private void affM(ActionEvent event) {
        Match e = matchv.getSelectionModel().getSelectedItem();
        DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      // parsing the string to convert it into date
        
        equipe1.setText(e.getEquipe1());
        equipe2.setText(e.getEquipe2());
        etat.setText(e.getEtat());


        
    }
    
    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
return new EventHandler<KeyEvent>() {
@Override
public void handle(KeyEvent e) {
TextField txt_TextField = (TextField) e.getSource();                
if (txt_TextField.getText().length() >= max_Lengh) 
{
    e.consume();
    JOptionPane.showMessageDialog(null, "Maximum length is 8 caracters");
}
if(e.getCharacter().matches("[A-Za-z]")){ }
else{
    e.consume();
    JOptionPane.showMessageDialog(null, "Team name does not contain numbers");

}
}};}
    @FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
        if(event.getSource()==btn1){
            
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("AffichageJourne.fxml"));
              Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
       
      
    
}
}

