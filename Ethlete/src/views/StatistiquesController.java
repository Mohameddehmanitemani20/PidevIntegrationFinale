/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.Formation;
import models.User;
import models.Utilisateur;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import services.ServiceFormateur;
import services.ServiceFormation;
import services.ServiceParticipation;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class StatistiquesController implements Initializable {

    @FXML
    private ImageView stat1;
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
    private Button stat;
    @FXML
    private JFXButton closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showFormation();
    }    
    ServiceParticipation sp=new ServiceParticipation();
ServiceFormateur sf1=new ServiceFormateur();
                ServiceFormation sf=new ServiceFormation();

 public void showFormation() {
		ObservableList<Formation> list =FXCollections.observableArrayList( sf.afficher());
                //id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));

nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom_formation"));
		debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
		fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
		dis.setCellValueFactory(new PropertyValueFactory<Formation, String>("dispositif"));
		formations.setItems(list);
	}
   
    @FXML
    private void consulterStat(ActionEvent event) throws IOException {
        
           Formation f=formations.getSelectionModel().getSelectedItem();
           Map<Formation, List<User>> map1=new HashMap();

            map1=sp.consulter_particiapnts_par_formation();
            
        List<User> list=map1.get(f);
            DefaultCategoryDataset dataset1=new DefaultCategoryDataset();
for(Formation f1:map1.keySet())
    
{
   
    dataset1.addValue(map1.get(f1).size(), f1.getNom_formation(),"");

}
              JFreeChart chart1=ChartFactory.createBarChart3D(" : ", "formations", "nbparticipant", dataset1, PlotOrientation.VERTICAL, true, true, true);
  CategoryPlot p=chart1.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.gray);
        p.setBackgroundPaint(Color.white); 
       ChartPanel linechartPanel= new ChartPanel(chart1);
          File file1=new File("./stat.jpg");
    ChartUtilities.saveChartAsJPEG(file1, chart1, 450, 300);
      JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //applicationFrame.getContentPane().add(viewerComponentPanel);
           
    JDialog ratioHommeFemmeJdialog = new JDialog();
            ratioHommeFemmeJdialog.setTitle("NB Participants");
              final ChartPanel cPanel = new ChartPanel(chart1);
    ChartUtilities.saveChartAsJPEG(file1, chart1, 450, 300);

            ratioHommeFemmeJdialog.getContentPane().add(cPanel, CENTER);

            ratioHommeFemmeJdialog.pack();
            ratioHommeFemmeJdialog.setVisible(true);
    
    }
    
      @FXML
    void hommefemme(ActionEvent event) throws IOException {
 int nbh=0;
        int nbf=0;
                 Map<Formation,List<User>> map1=sp.consulter_particiapnts_par_formation();

                   Formation f=formations.getSelectionModel().getSelectedItem();
                   System.out.println(map1);
                    System.out.println(f);
                    List <User> list=new ArrayList();
            for (Formation c:map1.keySet())
            {if(c.getId_formation()==f.getId_formation())
            {list= sp.chercher_part_formations_Part(f);
            
            
            for (int i=0;i<list.size();i++)
{ if(list.get(i).getGenre().equals("homme"))
    nbh++;
else
    nbf++;

}
            }


            }
                
                
                

DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Femme", nbf);
            pieDataset.setValue("Homme", nbh);
        JDialog ratioHommeFemmeJdialog = new JDialog();
            ratioHommeFemmeJdialog.setTitle("Ratio H/F");
 JFreeChart chart2 = ChartFactory.createPieChart(      
         "HF",   // chart title 
         pieDataset,          // data    
         true,             // include legend   
         true, 
         false);
        //JFreeChart chart2=ChartFactory.createMultiplePieChart3D("f", dataset1, TableOrder.BY_ROW, true, true, true);
     // JFreeChart chart1=ChartFactory.createStackedBarChart("Graphe 1.2 : Recouverts=f(nom)", "Nom", "Reouverts", dataset1, PlotOrientation.VERTICAL, true, true, false);
    File file1=new File("./HF+"+f.getNom_formation()+".jpeg");
    ChartUtilities.saveChartAsJPEG(file1, chart2, 450, 300);
    ImageIcon img1=new ImageIcon("./HF.jpeg");
  
             final JFreeChart pieChart = ChartFactory.createPieChart("Ratio H/F", pieDataset, true, false, false);
            final ChartPanel cPanel = new ChartPanel(pieChart);
    ChartUtilities.saveChartAsJPEG(file1, pieChart, 450, 300);

            ratioHommeFemmeJdialog.getContentPane().add(cPanel, CENTER);

            ratioHommeFemmeJdialog.pack();
            ratioHommeFemmeJdialog.setVisible(true);
    }



      @FXML
    void age(ActionEvent event) throws IOException  {
        int nbjeune=0;
        int nbagéé=0;
        String s;
                 Map<Formation,List<User>> map1=sp.consulter_particiapnts_par_formation();

                   Formation f=formations.getSelectionModel().getSelectedItem();
                   System.out.println(map1);
                    System.out.println(f);
                    List <User> list=new ArrayList();
            for (Formation c:map1.keySet())
            {if(c.getId_formation()==f.getId_formation())
            {list= sp.chercher_part_formations_Part(f);
            
            
            for (int i=0;i<list.size();i++)
{ 
    s=list.get(i).getDate_naissance().toString().substring(0, 4);
    if(2022-Integer.valueOf(s)>30)
    nbagéé++;
else
    nbjeune++;

}
            }


            }
                
                
                

DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Agée", nbagéé);
            pieDataset.setValue("Jeune", nbjeune);
        JDialog ratioHommeFemmeJdialog = new JDialog();
            ratioHommeFemmeJdialog.setTitle("Ratio AGE");
 JFreeChart chart2 = ChartFactory.createPieChart(      
         "Statistiques par age",   // chart title 
         pieDataset,          // data    
         true,             // include legend   
         true, 
         false);
        //JFreeChart chart2=ChartFactory.createMultiplePieChart3D("f", dataset1, TableOrder.BY_ROW, true, true, true);
     // JFreeChart chart1=ChartFactory.createStackedBarChart("Graphe 1.2 : Recouverts=f(nom)", "Nom", "Reouverts", dataset1, PlotOrientation.VERTICAL, true, true, false);
    File file1=new File("./Age+"+f.getNom_formation()+".jpeg");
    ChartUtilities.saveChartAsJPEG(file1, chart2, 450, 300);
   // ImageIcon img1=new ImageIcon("./HF.jpeg");
  
             final JFreeChart pieChart = ChartFactory.createPieChart("PAR AGE", pieDataset, true, false, false);
            final ChartPanel cPanel = new ChartPanel(pieChart);
    ChartUtilities.saveChartAsJPEG(file1, pieChart, 450, 300);

            ratioHommeFemmeJdialog.getContentPane().add(cPanel, CENTER);

            ratioHommeFemmeJdialog.pack();
            ratioHommeFemmeJdialog.setVisible(true);
       

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();}
    
    
}
