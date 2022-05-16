/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Evenement;
import models.Formation;
import services.ServiceEvenement;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author 21624
 */

public class AfficherEventController implements Initializable {

ObservableList<Evenement> list = FXCollections.observableArrayList();
    ServiceEvenement se = new ServiceEvenement();
    
    @FXML
    private TableColumn<?, ?> idTBL;
    @FXML
    private TableColumn<?, ?> nomTBL;
    @FXML
    private TableColumn<?, ?> datedTBL;
    @FXML
    private TableColumn<?, ?> datefTBL;
    @FXML
    private Label idLBL;
    @FXML
    private TextField rechercheTF;
    
    
    
    
    
    private TableView<Evenement> tableView;
    @FXML
    private TableView<Evenement> tableEvent;
    @FXML
    private TableColumn<?, ?> lieuTBL;
    @FXML
    private TableColumn<?, ?> typeTBL;
    @FXML
    private TableColumn<?, ?> eventTBL;
    @FXML
    private TableColumn<?, ?> interTBL;
    @FXML
    private TableColumn<?, ?> prixTBL;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateList(se.afficherEvenement());
    }    

  

   
    @FXML
    private void supprimerBTN_clicked(ActionEvent event) {
    //    se.supprimerEvenement(new Evenement(Integer.parseInt(idLBL.getText())));
       // clearCells();
           Evenement f=tableEvent.getSelectionModel().getSelectedItem();

     se.supprimerEvenement(f);
             updateList(se.afficherEvenement());

     
    }

   /*
    public void Convert(int id_formation)
    {        Connection cnx = DataSource.getInstance().getCnx(); 
     
        try {
            String req = "Select `nom_formation` from `evenement` where `id_formation`='" +id_formation+"' ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               // updateIdFormation(rs.getInt(1));
                System.out.println(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
    */
   
    
     private void updateList(List<Evenement> theList){
         list.clear();
        theList.stream().forEach(p -> list.add(p));
        idTBL.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        nomTBL.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        datedTBL.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefTBL.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        lieuTBL.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        typeTBL.setCellValueFactory(new PropertyValueFactory<>("typeE"));
        eventTBL.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
        eventTBL.setCellValueFactory(new PropertyValueFactory<>("id_inter"));
        prixTBL.setCellValueFactory(new PropertyValueFactory<>("prixU"));
        
        tableEvent.setItems(list);
    }
   
   
    
   
    private void clearCells(){
        nomTBL.setText("");
        datedTBL.setText("");
        datefTBL.setText("");
    }
    
    
    @FXML
    private void rowClicked(MouseEvent event) {
      /*  Evenement row = (Evenement) tableEvent.getSelectionModel().getSelectedItem();
        idLBL.setText(String.valueOf(row.getId_inter()));
        nomTF.setText(row.getNom_event());
        datedTF.setDate(row.getDate_debut());
        datefTF.setText(row.getDate_fin());*/
    }

    private void rechercheTextChanged(KeyEvent event) {
        updateList(se.rechercherEvenement(se.afficherEvenement(), rechercheTF.getText()));
    
        
    
    }

    @FXML
    private void rechercheTextChanged(InputMethodEvent event) {
    }

    

    private void retour(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void goToAjouter(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("AjouterEvent.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }
    
  
}

