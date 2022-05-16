/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import models.Formation;
import models.Intervenant;
import services.ServiceIntervenant;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class AfficherIntervenantController implements Initializable {
    
    ObservableList<Intervenant> list = FXCollections.observableArrayList();
    ServiceIntervenant si = new ServiceIntervenant();
    

    @FXML
    private ImageView logo;
    @FXML
    private TableView<Intervenant> tableInter;
    @FXML
    private TableColumn<?, ?> idTBL;
    @FXML
    private TableColumn<?, ?> nomTBL;
    @FXML
    private TableColumn<?, ?> prenomTBL;
    @FXML
    private TableColumn<?, ?> emailTBL;
    
    @FXML
    private TextField nomTF;
    @FXML
    private Label idLBL;
    @FXML
    private TableColumn<?, ?> tlphTBL;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField telephoneTF;
    private TextField rechercheTF;
    @FXML
    private JFXButton closeButton;
     
    
     void afficherIntervenant(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("AjouterIntervenant.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
                    updateList(si.afficherIntervenant());

   
        
        

    }
    
    private void updateList1(List<Intervenant> theList){
       Callback<TableColumn<Intervenant, String>, TableCell<Intervenant, String>> cellFactoryU
        =
        ( TableColumn<Intervenant, String> param) -> {
            TableCell<Intervenant, String> cell = new TableCell<Intervenant, String>() {

                ImageView btn = new ImageView();


                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {


                            String im = getTableView().getItems().get(getIndex()).getImage_In(); 
                            File file = new File(im);
                            //System.out.println(u.getById(p.getIdUser()).getNom());
                            System.out.println(im);
                            btn.setImage(new Image(file.toURI().toString()));

                        ObservableList observableList = FXCollections.observableArrayList(si.afficherIntervenant());
                        tableInter.setItems(observableList);
                        // list();


                        setGraphic(btn);
                        setText(null);
                        btn.setFitWidth(20);
                        btn.setFitHeight(20);


                    }
                }


            };

            return cell;
        };
      // imagetbl.setCellFactory(cellFactoryU);
     }

  @FXML
    private void modifierBTN_clicked(ActionEvent event) {
        si.modifierIntervenant(new Intervenant(Integer.parseInt(idLBL.getText()),nomTF.getText(),prenomTF.getText(),emailTF.getText(), Integer.parseInt(telephoneTF.getText())));
        clearCells();
        updateList(si.afficherIntervenant());
    }
    @FXML
    private void deleteBTN_clicked(ActionEvent event) {
        si.supprimerIntervenant(new Intervenant(Integer.parseInt(idLBL.getText())));
        clearCells();
        updateList(si.afficherIntervenant());
    }
    
    private void updateList(List<Intervenant> theList){
        list.clear();
        theList.stream().forEach(p -> list.add(p));
        
        idTBL.setCellValueFactory(new PropertyValueFactory<>("id_inter"));
       // imagetbl.setCellValueFactory(new PropertyValueFactory<>("image_In"));
        nomTBL.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomTBL.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailTBL.setCellValueFactory(new PropertyValueFactory<>("email"));
        tlphTBL.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        
        tableInter.setItems(list);
    }
    
    private void clearCells(){
        nomTF.setText("");
        prenomTF.setText("");
        emailTF.setText("");
        telephoneTF.setText("");
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        Intervenant row = tableInter.getSelectionModel().getSelectedItem();
        idLBL.setText(String.valueOf(row.getId_inter()));
       // urlTF.setText(row.getNom());
        nomTF.setText(row.getNom());
        prenomTF.setText(row.getPrenom());
        emailTF.setText(row.getEmail());
        telephoneTF.setText(String.valueOf(row.getTelephone()));
    }

 

    private void rechercheTextChanged(KeyEvent event) {
        
      updateList(si.rechercherIntervenant(si.afficherIntervenant(), rechercheTF.getText()));
        FilteredList<Intervenant> filteredData = new FilteredList<>(FXCollections.observableList(si.afficherIntervenant()));
           
       tableInter.setItems(filteredData);
      

      
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();}
    

    @FXML
    private void add(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AjouterIntervenant.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
}