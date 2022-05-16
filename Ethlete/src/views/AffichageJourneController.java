/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import services.journeservice;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import models.Competition;
import models.Journe;
import models.Match;
import services.competitionservice;

/**
 * FXML Controller class
 *
 * @author gaming
 */
public class AffichageJourneController implements Initializable {

    @FXML
    private TableColumn<Journe, Integer> numj;
    @FXML
    private TableColumn<Journe, String> datej;
    @FXML
    private Button sup;
    @FXML
    private Button modif;
    @FXML
    private DatePicker datejA;
    @FXML
    private TableView<Journe> journe;
    @FXML
    private TextField numjA;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private TextField textfield1;
    @FXML
    private TextField textfield;
    @FXML
    private ComboBox<Competition> journep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 competitionservice jcs = new competitionservice();

        afficher();
        //numvald
        numjA.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
        
        //combobox
        journep.setItems(FXCollections.observableList(jcs.getlist1()));
        journep.setConverter(new StringConverter<Competition>() {

    @Override
    public String toString(Competition object) {
        return (object.getNom());
    }

    @Override
    public Competition fromString(String string) {
        return journep.getItems().stream().filter(ap -> 
            (ap.getNom()).equals(string)).findFirst().orElse(null);
    }
});
        journep.valueProperty().addListener((obs, oldval, newval) -> {
    if(newval != null)
        System.out.println("Selected Competition: " + newval.getNom()
            + ". ID: " + newval.getId_competition());
});

        // TODO
    }       
    
    journeservice si = new journeservice();
        List<Journe> list = new ArrayList<>();
public void afficher(){
    list = si.find();
    System.out.println(list);
    ObservableList<Journe> list1 =FXCollections.observableArrayList(list);
        numj.setCellValueFactory(new PropertyValueFactory<Journe, Integer>("numJourne"));
        datej.setCellValueFactory(new PropertyValueFactory<Journe, String>("date_journe"));
         journe.setItems(FXCollections.observableArrayList(list1));
        FilteredList<Journe> filteredData = new FilteredList<>(list1, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		textfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(competition.getNumJourne()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} 
			
				     else  
				    	 return false; // Does not match.
			});
		});
                SortedList<Journe> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(journe.comparatorProperty());
                FilteredList<Journe> filteredData1 = new FilteredList<>(sortedData, b -> true);
		textfield1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData1.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
			if (competition.getDate_journe().toLowerCase().indexOf(lowerCaseFilter) != -1)
				     return true;
			
				     else  
				    	 return false; // Does not match.
			});
		});
                	
		// 3. Wrap the FilteredList in a SortedList. 
	// 3. Wrap the FilteredList in a SortedList. 
		
		   SortedList<Journe> sortedData1 = new SortedList<>(filteredData1);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(journe.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		journe.setItems(sortedData1);
    
}
    
    @FXML

    private void addM(ActionEvent event) {
        si.ajouter(new Journe(Integer.parseInt(numjA.getText()),datejA.getValue().toString(),journep.getValue().getId_competition()));
            Sendmail sn = new Sendmail();
        sn.envoyer("azer.khabthani@esprit.tn","the "+numjA.getText(),numjA.getText()+" th Journe starts at "+datejA.getValue().toString()+" Be ready .");
        JOptionPane.showMessageDialog(null, "Journée Ajouté");
        afficher();

    }
    @FXML

    private void modifM(ActionEvent event) {
             Journe e = new Journe(journe.getSelectionModel().getSelectedItem().getId_journe(),Integer.parseInt(numjA.getText()),datejA.getValue().toString());
             
        System.out.println(e);
             si.modifier(e);
             afficher();
    }
    @FXML

    private void deleteM(ActionEvent event) {
        Journe e = journe.getSelectionModel().getSelectedItem();
        si.supprimer(e);
        afficher();
    }
    private void affM(ActionEvent event) {
        Journe e = journe.getSelectionModel().getSelectedItem();
        DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      // parsing the string to convert it into date
        LocalDate local_date = LocalDate.parse(e.getDate_journe());
        datejA.setValue(local_date);
        
        numjA.setText(Integer.toString(e.getNumJourne()));
        
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
            root = FXMLLoader.load(getClass().getResource("AffichageCompetition.fxml"));
        }
        else{
            stage = (Stage) btn2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("AffichageMatch.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
}
}

