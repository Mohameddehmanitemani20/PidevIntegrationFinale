/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import models.Competition;
import models.Evenement;
import models.Formation;
import models.Intervenant;
import services.ServiceEvenement;
import services.ServiceFormation;
import services.ServiceIntervenant;
import services.competitionservice;
import sun.print.resources.serviceui;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author 21624
 */

public class AjouterEventController implements Initializable {

    ServiceEvenement se = new ServiceEvenement();
    ServiceFormation sf = new ServiceFormation();
    @FXML
    private Label date_debut;
    @FXML
    private Label date_fin;
    @FXML
    private TextField id_event;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
    @FXML
    private ComboBox<String> typeE;
    
    @FXML
    private Label lieu;
    @FXML
    private ImageView logo;
    @FXML
    private TextField lieuTF;
      
    ObservableList<String> formations;
    @FXML
    private ComboBox<String> eventT;
    @FXML
    private ComboBox<String> inter;
    @FXML
    private TextField prixE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            
           
            Image image1 = new Image(new FileInputStream("C:\\Users\\21624\\Documents\\NetBeansProjects\\Ethlete\\src\\views\\elements\\logo.png"));
            logo.setImage(image1);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
   /*   ServiceIntervenant si= new ServiceIntervenant(); 
        ObservableList<Intervenant> inte = FXCollections.observableArrayList();
        si.afficherIntervenant().stream().forEach((p)->{ inte.add(p); });

        inter.setItems(inte);*/
        
 ObservableList<String> obs_noms1 = FXCollections.observableArrayList();
                 List<String> noms1 =null;  
             List<Intervenant> intervenants = new ArrayList<>();
            intervenants = (new ServiceIntervenant()).display();
            noms1 = intervenants.stream().map(x->x.getNom()).collect(Collectors.toList());
             for(String n : noms1)
                obs_noms1.add(n);
            
        inter.setItems(obs_noms1);
        
        
        ObservableList<String> combo1 = FXCollections.observableArrayList("Formation","Compétition");

//         afficherformation();

        //hot lista f combobox
        
        typeE.setItems(combo1);
        
    }    
    
     private void afficherformation()
            
    {
    ServiceFormation sf=new ServiceFormation();
    //for (int i=0 ; i <= 0;i++){
            
    formations.add(sf.afficher().get(0).getNom_formation());
    //}
    }
     
  public boolean ctrl()
{
    String d=dateD.getValue().toString();
    String f=dateF.getValue().toString();
     String[] d1=d.split("-");
     String[] f1=f.split("-");
     if(Integer.valueOf(d1[0])<=Integer.valueOf(f1[0])  && Integer.valueOf(d1[1])<=Integer.valueOf(f1[1]) && Integer.valueOf(d1[2])<=Integer.valueOf(f1[2]) )
          return true;
     return false;    
    
}
  public ArrayList<Formation>getData() throws SQLException
  {
      ArrayList<Formation> list = new ArrayList<Formation>();
 Connection cnx = DataSource.getInstance().getCnx();                Statement st;
               ResultSet rs;
               
               st= cnx.createStatement();
               rs = st.executeQuery("Select nom_formation from formation");
               
               
               Formation f;
               f=new Formation(rs.getString("nom_formation"));
               
               return list;

  }
  
  /*public void fillCombo()
  {      
  
  
  ObservableList<String> obs_noms1 = FXCollections.observableArrayList();
                 List<String> noms1 =null;  
             List<Intervenant> intervenants = new ArrayList<>();
            intervenants = (new ServiceIntervenant()).display();
            noms1 = intervenants.stream().map(x->x.getNom()).collect(Collectors.toList());
             for(String n : noms1)
                obs_noms1.add(n);
            
        inter.setItems(obs_noms1);
 /* Connection cnx = utils.MaConnexion.getInstance().getCnx();

      String sql= "Select nom_formation from formation";
        try {
      PreparedStatement ps = cnx.prepareStatement(sql);
      ResultSet res = ps.executeQuery();
      
      eventT.addItem(res.getString(sql));
      
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex); }
        }
      */
  
  
    @FXML
    private void ajouterEvent(ActionEvent event) throws IOException {
        



         if (nom.getText().length() == 0 || lieu.getText().length() == 0 || prixE.getText().length() == 0  )
         {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("manque d informations");
            alert.show();

        } 
         
         if (!ctrl())
         {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("La date de Fin doit etre superieure à celle de début");
            alert.show();
         }
         else 
         {    
            ServiceEvenement si= new ServiceEvenement();
            Evenement E=new Evenement(nom.getText(),
                Date.valueOf(dateD.getValue()),
                Date.valueOf(dateF.getValue()),
                typeE.getSelectionModel().getSelectedItem(),
                lieuTF.getText(),
                Float.parseFloat(prixE.getText()));
            ServiceFormation sf=new ServiceFormation();
             System.out.println(E);
        
        rechercheIdIntervenant(inter.getSelectionModel().getSelectedItem(),E);
        
        if(typeE.getSelectionModel().getSelectedItem()=="Formation")
        {            Formation f=sf.getbynom(eventT.getSelectionModel().getSelectedItem());

                  E.setId_formation(f.getId_formation());
             E = si.ajouterEvenement(E);

        
        }
        else
            rechercheIdCompet(eventT.getSelectionModel().getSelectedItem(),E);
        
        //rechercheID1(eventT.getSelectionModel().getSelectedItem());
        //rechercheID1(inter.getSelectionModel().getSelectedItem());


      //eventT.getSelectionModel().getSelectedItem()
        JOptionPane.showMessageDialog(null, "Evénement Ajouté");
               
              FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../views/AfficherEvent.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Liste des Evénements");
            stage.setScene(new Scene(root1));  
            stage.show();

         }
    
         
    }
    
    public void rechercheIdFormation(String nom_formation,Evenement E)
    {         Connection cnx = DataSource.getInstance().getCnx(); 
     
        try {
            String req = "Select `id_formation` from `formation` where `nom_formation`='" +nom_formation+"' ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                updateIdFormation(rs.getInt(1),E);
                System.out.println(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
    
    public void rechercheIdIntervenant(String nom_intervenant,Evenement E)
    {         Connection cnx = DataSource.getInstance().getCnx(); 
     
        try {
            String req = "Select `id_inter` from `intervenant` where `nom`='" +nom_intervenant+"' ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                updateIdIntervenant(rs.getInt(1),E);
                System.out.println(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
    
    public void rechercheIdCompet(String nom_compet,Evenement E)
    {         Connection cnx = DataSource.getInstance().getCnx(); 
     
        try {
            String req = "Select `id_competition` from `competition` where `nom`='" +nom_compet+"' ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                
                updateIdCompetition(rs.getInt(1),E);
       

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
    

     
    
    public void updateIdFormation(int id_formation, Evenement E)
    {
         Connection cnx = DataSource.getInstance().getCnx(); 
        Statement st = null;
        E.setId_formation(id_formation);
        try {

            String req1 = "UPDATE `evenement` SET `id_formation`="+id_formation+" WHERE `id_event`="+E.getId_event()+"";
            st = cnx.createStatement();
            st.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateIdIntervenant(int id_intervenant, Evenement E)
    {
         Connection cnx = DataSource.getInstance().getCnx(); 
         System.out.println(E);
        Statement st = null;
        E.setId_inter(id_intervenant);
        try {

            String req1 = "UPDATE `evenement` SET `id_inter`="+id_intervenant+" WHERE `id_event`="+E.getId_event()+"";
            st = cnx.createStatement();
            st.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateIdCompetition(int id_compet, Evenement E)
    {
          Connection cnx = DataSource.getInstance().getCnx(); 
        Statement st = null;
        E.setId_compet(id_compet);
        try {

            String req1 = "UPDATE `evenement` SET `id_compet`="+id_compet+" WHERE `id_event`="+E.getId_event()+"";
            st = cnx.createStatement();
            st.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   

    @FXML
    private void typeSelected(ActionEvent event) {
        ObservableList<String> obs_noms = FXCollections.observableArrayList();
        List<String> noms =null;
        if(typeE.getSelectionModel().getSelectedItem().equals("Formation")){
            List<Formation> formationss = new ArrayList<>();
            formationss = (new ServiceFormation()).display();
            noms = formationss.stream().map(x->x.getNom_formation()).collect(Collectors.toList());
        }
        else{
            List<Competition> compets = new ArrayList<>();
            compets = (new competitionservice()).display();
            noms = compets.stream().map(x->x.getNom()).collect(Collectors.toList());
        }
        
        for(String n : noms)
                obs_noms.add(n);
            
        eventT.setItems(obs_noms);
    }

    @FXML
    private void interSelected(ActionEvent event) {
        
     /*    ObservableList<String> obs_noms1 = FXCollections.observableArrayList();
                 List<String> noms1 =null;  
             List<Intervenant> intervenants = new ArrayList<>();
            intervenants = (new ServiceIntervenant()).display();
            noms1 = intervenants.stream().map(x->x.getNom()).collect(Collectors.toList());
             for(String n : noms1)
                obs_noms1.add(n);
                     inter.setItems(obs_noms1);*/

    }

    @FXML
    private void GoTOdash(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
   
  
}
