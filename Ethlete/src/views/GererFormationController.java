/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.qoppa.pdfViewerFX.PDFViewer;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.io.File;
import models.Formation;
import services.ServiceFormation;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class GererFormationController implements Initializable {
 ObservableList<String> dispoStatus=FXCollections.observableArrayList("En_Ligne","Présentiel");
  ObservableList<String> triStatus=FXCollections.observableArrayList("Date de Début","Nom");
  ObservableList<Integer> pageStatus=FXCollections.observableArrayList(1,2,3,4,5);

    @FXML
    private TableView<Formation> formations;
    @FXML
    private TableColumn<Formation, String> nom;
    @FXML
    private TableColumn<Formation, Date>  debut;
    @FXML
    private TableColumn<Formation, Date> fin;
    @FXML
    private TableColumn<Formation, String> dis;
    @FXML
    private TableColumn<Formation, String> prog;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprog;
    @FXML
    private DatePicker tfdebut;
    @FXML
    private DatePicker tffin;
    @FXML
    private ComboBox<String> tfdispo;
    
    @FXML
    private Button pdf;
    /**
     * Initializes the controller class.
     */
            ServiceFormation sf=new ServiceFormation();
    @FXML
    private ComboBox<String> tftri;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private CheckBox selectAll;
    @FXML
    private Button afficher;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXTextField search;
    @FXML
    private Pagination paginate;
    @FXML
    private JFXButton test;
    @FXML
    private ComboBox<Integer> pagination1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
int numOfPages = 5;
        paginate=new Pagination ((numOfPages), 0);
      //  paginate.setMaxPageIndicatorCount(3);
        
pagination1.setItems(pageStatus);
        tfdispo.setItems(dispoStatus);
                tftri.setItems(triStatus);
        showFormation();
    //   System.out.println(paginateProducts(2,2)); 
        //       paginate.setPageFactory(this::createPage);    
        formations.setEditable(true);
        nom.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
                prog.setEditable(true);

        prog.setCellFactory(TextFieldTableCell.forTableColumn());
                             prog.setOnEditCommit(           
t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setProgramme(t.getNewValue())

);
                        dis.setEditable(true);
                        nom.setOnEditCommit(           
t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setNom_formation(t.getNewValue())

);
dis.setCellFactory(ComboBoxTableCell.forTableColumn("En_Ligne","Présentiel"));
                         dis.setOnEditCommit(           
t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDispositif(t.getNewValue())

);
//debut.setCellFactory(TextFieldTableCell.forTableColumn());
       
    }    

    @FXML
    private void ModifierFormation(ActionEvent event) {
        Formation f=formations.getSelectionModel().getSelectedItem();



    

//f.setDate_debut(Date.valueOf(tfdebut.getValue()));
/*f.setNom_formation(tfnom.getText());
f.setProgramme(tfprog.getText());
f.setDispositif(tfdispo.getValue());*/
//f.setDate_fin(Date.valueOf(tffin.getValue()));
 sf.modifier(f);
       JOptionPane.showMessageDialog(null,"Formation modifié" );
  showFormation() ;
        
    }
    @FXML
    private void AfficherFormation(ActionEvent event) 
    {
    
    Formation f=formations.getSelectionModel().getSelectedItem();
        tfnom.setText(f.getNom_formation());
tfprog.setText(f.getProgramme());
tfdispo.setValue(f.getDispositif());
tfdebut.setValue(f.getDate_debut().toLocalDate());
tffin.setValue(f.getDate_fin().toLocalDate());



     
    

    }
    
    
    public void showFormation() {
		ObservableList<Formation> list =FXCollections.observableArrayList( sf.afficher());
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
		formations.setItems(list);
                
	}
    @FXML
    public void AjouterFormation(ActionEvent event)
    {if (tfnom.getText().equals("") || tfdebut.getValue().toString().equals("")|| tffin.getValue().toString().equals("")|| tfdispo.getValue().toString().equals("")
            || tfprog.getText().equals(""))
        
              JOptionPane.showMessageDialog(null,"il ya des champs vides" );
    else 
    {  if(!ctrl_unique())
        
        
        JOptionPane.showMessageDialog(null, "Nom existe déjà");
    else 
    {  if(!ctrl_date())
        
        
        JOptionPane.showMessageDialog(null, "Date fin est inférieur à la date début");
     else
    {
    
    Formation f=new Formation(tfnom.getText(),Date.valueOf(tfdebut.getValue()),Date.valueOf(tffin.getValue()),tfdispo.getValue(),tfprog.getText());
    
    sf.ajouter(f);
      JOptionPane.showMessageDialog(null,"Formation Ajouté" );
    showFormation() ;
    }
    
    }
    
    }

        
        
     
        
    
    }
    public boolean ctrl_unique()
    {int i=0;
    List<Formation> list2=new ArrayList();
    list2=sf.afficher();
    boolean exist=false;
    
for(i=0;i<list2.size();i++)
{if(list2.get(i).getNom_formation().equals(tfnom.getText()))
    return false;

}
return true;
      
    }

  
public boolean ctrl_date()
{String d=tfdebut.getValue().toString();String f=tffin.getValue().toString();
     String[] d1=d.split("-");
     String[] f1=f.split("-");
     if(Integer.valueOf(d1[0])<=Integer.valueOf(f1[0])  && Integer.valueOf(d1[1])<=Integer.valueOf(f1[1]) && Integer.valueOf(d1[2])<=Integer.valueOf(f1[2]) )
          return true;
     return false;
    

}

    @FXML
    private void supprimerFormation(ActionEvent event) {
      Formation f=formations.getSelectionModel().getSelectedItem();
      sf.supprimer(f);
         JOptionPane.showMessageDialog(null,"Formation supprimé" );
        // System.out.println( ctrl());
    showFormation() ;
      
    }
    @FXML
   private void trierForm(ActionEvent event) {
if(tftri.getValue().equals("Date de Début"))
{		ObservableList<Formation> list =FXCollections.observableArrayList( sf.trier_date_debut());
nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
		formations.setItems(list);

}
else
{	ObservableList<Formation> list =FXCollections.observableArrayList( sf.trier_par_nom());
nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
		formations.setItems(list);}
    
    
    }
    
      @FXML
    void consulterpdf(ActionEvent event) {
      Formation f=formations.getSelectionModel().getSelectedItem();
sf.generer_pdf(f);
	/* PDFViewer m_PDFViewer;
         m_PDFViewer = new PDFViewer();
		BorderPane borderPane = new BorderPane(m_PDFViewer);
		Scene scene = new Scene(borderPane);
                Stage stage = null;
		stage.setTitle("JavaFX PDFViewer - Qoppa Software");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();*/
           
    
    SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //applicationFrame.getContentPane().add(viewerComponentPanel);
        applicationFrame.add(viewerComponentPanel);
        
        controller.openDocument("C:\\Users\\MSI\\Desktop\\PI\\Ethlete\\catalogue"+f.getNom_formation()+".pdf");

        applicationFrame.pack();
        applicationFrame.setVisible(true);
    
    }

    @FXML
    private void retour(ActionEvent event) {
     Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();  }

    private void rechercher(ActionEvent event) {
        FilteredList<Formation> filteredData = new FilteredList<>(FXCollections.observableList(sf.afficher()));
        formations.setItems(filteredData);


        /*Formation f=sf.consulter_formation(search.getText());
          tfnom.setText(f.getNom_formation());
tfprog.setText(f.getProgramme());
tfdispo.setValue(f.getDispositif());
tfdebut.setValue(f.getDate_debut().toLocalDate());
tffin.setValue(f.getDate_fin().toLocalDate());*/
        
    }
    
    


    @FXML
    private void rechercheTextChanged(KeyEvent event) {
                updateList(sf.rechercherFormation(sf.afficher(), search.getText()));

    }
   private void updateList(List<Formation> theList){
       ObservableList<Formation> list =FXCollections.observableArrayList();
        list.clear();
        theList.stream().forEach(p -> list.add(p));
     
nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
        
        formations.setItems(list);
    }
        private final static int rowsPerPage = 3;    

   
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
              ObservableList<Formation> list =FXCollections.observableArrayList(sf.afficher());

        int toIndex = Math.min(fromIndex + rowsPerPage,list.size());
               List<Formation> list1=list.subList(fromIndex, toIndex);
                             ObservableList<Formation> list2 =FXCollections.observableArrayList(list1);
nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
        formations.setItems(list2);
        return formations;
    }
    
    
    
  
    

    @FXML
    private void test(ActionEvent event) {
        
        
        
    try{  final String m="hello";
        
       Voice v;
       VoiceManager vm=VoiceManager.getInstance();
       v=vm.getVoice(m);

       v.allocate();
      
            v.speak(tfnom.getText());}
        catch(Exception e)
        {System.out.println("erreur");
      
        }
    
    
    }

    @FXML
    private void page(MouseEvent event) {
        
        
                   paginate.setPageFactory(this::createPage);    

    
    }
public List<Formation> paginateFormations( int num,int size) { 
    int SKIP_COUNT = (num - 1) * size;     
    
    return sf.afficher()
            .stream()         .skip(SKIP_COUNT)         .limit(size).collect(Collectors.toList());  
}    
    @FXML
    private void pager(ActionEvent event) {
        List<Formation> list6=this.paginateFormations(pagination1.getValue(), 3)  ;
         ObservableList<Formation> list7 =FXCollections.observableArrayList(list6);
    
nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		prog.setCellValueFactory(new PropertyValueFactory<Formation, String>("programme"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
        
        formations.setItems(list7);
    }
   
}
