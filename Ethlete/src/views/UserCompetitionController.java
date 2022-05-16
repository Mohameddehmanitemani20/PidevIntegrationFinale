/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Competition;
import models.Journe;
import services.competitionservice;
import services.journeservice;

/**
 * FXML Controller class
 *
 * @author gaming
 */
public class UserCompetitionController implements Initializable {

    @FXML
    private TableView<Competition> competition;
    ObservableList<Competition> list1;
    @FXML
    private TextField textfield;
    @FXML
    private TextField textfield1;
    @FXML
    private TableColumn<Competition, Integer> neq;
    @FXML
    private TableColumn<Competition, String> nomc;
    @FXML
    private TableColumn<Competition, String> datec;
    @FXML
    private TableColumn<Competition, String> addc;
    @FXML
    private Button btn2;
    @FXML
    private Button btn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     list = si.find();

       
         afficher();

    }    
    competitionservice si = new competitionservice();
        List<Competition> list = new ArrayList<>();
public void afficher(){
    list = si.find();
    System.out.println(list);
    
        list1 =FXCollections.observableArrayList(list);          
        neq.setCellValueFactory(new PropertyValueFactory<Competition, Integer>("nb_equipe"));
        nomc.setCellValueFactory(new PropertyValueFactory<Competition, String>("nom"));
        datec.setCellValueFactory(new PropertyValueFactory<Competition, String>("date"));
        addc.setCellValueFactory(new PropertyValueFactory<Competition, String>("adresse"));

        
      competition.setItems(FXCollections.observableArrayList(list1));
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
				
				if (competition.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} 
			
				     else  
				    	 return false; // Does not match.
			});
		});
                SortedList<Competition> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(competition.comparatorProperty());
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
		sortedData1.comparatorProperty().bind(competition.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		competition.setItems(sortedData1);
}
    @FXML

    private void handleButtonAction (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
        if(event.getSource()==btn1){
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../views/Authentification.fxml"));
        }
        else{
            HolderClass.setCompId(competition.getSelectionModel().getSelectedItem().getId_competition());
            stage = (Stage) btn2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("UserJourne.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
