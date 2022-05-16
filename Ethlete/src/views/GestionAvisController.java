/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import static java.awt.BorderLayout.CENTER;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import models.Avis;
import models.Formation;
import models.User;
import models.Utilisateur;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import services.ServiceAvis;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class GestionAvisController implements Initializable {

    private ComboBox<Integer> list;
    @FXML
    private ComboBox<?> pwd;
    @FXML
    private JFXButton cb;
    
  
      void lister()
    {List<Integer> list1=new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
     ObservableList<Integer> eq;

    
    
      eq=FXCollections.observableArrayList(list1);
     list.setItems(eq);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lister();
        
    }    
    User u = new User (AuthentificationController.idglobal);

    @FXML
    private void ajouter_avis(ActionEvent event) {
        
        sa.ajouter(new Avis(u.getId(),list.getValue()));
        
        
    }

 
    ServiceAvis sa=new ServiceAvis();
  
    
    private void stat_avis(ActionEvent event) throws IOException {
           
    
}

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void retour_menu(ActionEvent event) {
             Stage stage = (Stage)cb.getScene().getWindow();
       stage.close(); 
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
          int nb1=0;
                          int nb2=0;

             int nb3=0;

                          int nb4=0;

                                       int nb5=0;
String s;
                

List<Avis> av=new ArrayList();
av=sa.afficher();

            for (Avis a:av)
            if(a.getnote()==1)
            nb1++;
            else
                if(a.getnote()==2)
                    nb2++;
              else
                if(a.getnote()==3)
                    nb3++;
            else
                if(a.getnote()==4)
                    nb4++;
                else nb5++;
            DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Note=1", nb1);
            pieDataset.setValue("Note=2", nb2);
                        pieDataset.setValue("Note=3", nb3);
                           pieDataset.setValue("Note=4", nb4);
                                   pieDataset.setValue("Note=5", nb5);



        JDialog AvisJdialog = new JDialog();
            AvisJdialog.setTitle("Stat des avis");
 JFreeChart chart2 = ChartFactory.createPieChart(      
         "Stat des avis",   // chart title 
         pieDataset,          // data    
         true,             // include legend   
         true, 
         false);
        //JFreeChart chart2=ChartFactory.createMultiplePieChart3D("f", dataset1, TableOrder.BY_ROW, true, true, true);
     // JFreeChart chart1=ChartFactory.createStackedBarChart("Graphe 1.2 : Recouverts=f(nom)", "Nom", "Reouverts", dataset1, PlotOrientation.VERTICAL, true, true, false);
    File file1=new File("./Stat des avis.jpeg");
    ChartUtilities.saveChartAsJPEG(file1, chart2, 450, 300);
    ImageIcon img1=new ImageIcon("./Stat des avis.jpeg.jpeg");
  
             final JFreeChart pieChart = ChartFactory.createPieChart("Stat des avis", pieDataset, true, false, false);
            final ChartPanel cPanel = new ChartPanel(pieChart);
    ChartUtilities.saveChartAsJPEG(file1, pieChart, 450, 300);

            AvisJdialog.getContentPane().add(cPanel, CENTER);

            AvisJdialog.pack();
            AvisJdialog.setVisible(true);

    }
}
