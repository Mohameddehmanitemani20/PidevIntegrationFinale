/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.pidev.entities.Formation;
import com.pidev.services.ServiceFormation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author pc
 */
public class ListerFormation  extends Form {
     public ListerFormation(Form previous) {
        setTitle("Liste des formations");
        String[] abc = new String[] {"A", "B", "C"};
         Container list = new Container(BoxLayout.y());
list.setScrollableY(true);
TextField searchField = new TextField("", " Rechercher ...."); // <1>

      ServiceFormation f=new ServiceFormation();
     //  f.getAllFormations();
       SpanLabel sp = new SpanLabel();
                       ArrayList<Formation> l=new ArrayList<>();

           l=f.getAllFormations();

       
       
       //  System.out.println(l);
         for (int i=0;i<l.size();i++)
         {             Date d=l.get(i).getDate_debut();

             Date d1=l.get(i).getDate_fin();
             int nb =l.get(i).getId_formation();
             String s =l.get(i).getNom_formation();
                          String s1 =l.get(i).getDate_debut().toString();
             String s2 =l.get(i).getDate_fin().toString();
             String s3 =l.get(i).getDispositif();
             String s4 =l.get(i).getProgramme();

          Container list1 = new Container(BoxLayout.x());
            Button b = new Button("delete");
          //     Button b1 = new Button("update");
                  Button b2 = new Button("Détails");
                 b.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                         //      f.deleteformation(l.get(i).getId_formation());    

                          
                        Form hi2 = new Form(BoxLayout.y());
                     /*   hi2.getToolbar().addMaterialCommandToLeftBar(
                                "Back", FontImage.MATERIAL_ARROW_BACK
                                , new ActionListener<ActionEvent>() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                         ListerFormation lf=new ListerFormation(current);
                            }
                        });*/

                            try {
                                
                                Dialog.show("Success",s + "est supprimée",new Command("OK"));
                           f.deleteformation(nb);  
                            SpanLabel sp = new SpanLabel(s + "est supprimée");
                          //  sp.setText(l.get(i).getNom_formation());
                     previous.showBack();
                        //    hi2.add(sp);
                         //   hi2.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                                              refreshTheme();

                    }
                });
                 
                      b2.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Form hi2 = new Form(BoxLayout.y());
                        hi2.   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                        try {
                          Label l1=new Label("Nom de la formation:");
                          
                                      Label l2=new Label("Date Début:");
                          Label l3=new Label("Date Fin:");
        ComboBox<String> dispo =new   ComboBox<String> (s3,"Presentiel","En_Ligne") ;

                                                    Label l4=new Label("Dispositif:");
                                                    TextField t1=new TextField(s);
                                                     t1.getAllStyles().setFgColor(000000);

                                      TextField t2=new TextField(s1);
                                       t2.getAllStyles().setFgColor(000000);

 Picker tfdeb = new Picker();
 tfdeb.setDate(d);
 tfdeb.getAllStyles().setFgColor(000000);
  Picker tffin = new Picker();
 tffin.setDate(d1);
  tffin.getAllStyles().setFgColor(000000);

        TextField t3=new TextField(s2);
         t3.getAllStyles().setFgColor(000000);

              TextField t4=new TextField(s3);
               t4.getAllStyles().setFgColor(000000);

              Formation f4=new Formation();
                  Container c1 = new Container(BoxLayout.x());
                  Container c2 = new Container(BoxLayout.x());
                  Container c3 = new Container(BoxLayout.x());
                  Container c4 = new Container(BoxLayout.x());
c1.addAll(l1,t1);
     c2.addAll(l2,tfdeb);c3.addAll(l3,tffin);
     
c4.addAll(l4,dispo);

Button b=new Button("update");
  b.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                         //      f.deleteformation(l.get(i).getId_formation());    

                          
                       // Form hi2 = new Form(BoxLayout.y());
                     /*   hi2.getToolbar().addMaterialCommandToLeftBar(
                                "Back", FontImage.MATERIAL_ARROW_BACK
                                , new ActionListener<ActionEvent>() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                         ListerFormation lf=new ListerFormation(current);
                            }
                        });*/

                            try {
                                f4.setId_formation(nb);
                                                                f4.setNom_formation(t1.getText());
                                                                f4.setDate_debut(tfdeb.getDate());
                                                                f4.setDate_fin(tffin.getDate());
                                                                f4.setDispositif(dispo.getSelectedItem());
                                                                f4.setProgramme(s4);

                                   f.UpdateFormation(f4,nb); 
                                Dialog.show("Success",s + "updated",new Command("OK"));
                         
                          previous.showBack();
                          //  sp.setText(l.get(i).getNom_formation());
                     
                        //    hi2.add(sp);
                         //   hi2.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                                              refreshTheme();

                    }
                });
Button b1=new Button("PDF");

 b1.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                  

                            try {
                              		//Desktop desktop = java.awt.Desktop.getDesktop();

                                //   f.PDFFormation(nb); 
                          //       BrowserComponent browser = new BrowserComponent();
                              /*  SwingController controller = new SwingController();

                                SwingViewBuilder factory = new SwingViewBuilder(controller);

                                JPanel viewerComponentPanel = factory.buildViewerPanel();

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

                                JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //applicationFrame.getContentPane().add(viewerComponentPanel);
        applicationFrame.add(viewerComponentPanel);
        
        controller.openDocument("http://127.0.0.1:8000/"+"formation/pdfJSON/"+nb+"/");

        applicationFrame.pack();
        applicationFrame.setVisible(true);*/
         //   browser.setURLHierarchy( "http://127.0.0.1:8000/"+"formation/pdfJSON/"+nb+"/");

                     Form hi = new Form("Browser", new BorderLayout());
BrowserComponent browser = new BrowserComponent();
browser.setURL("http://127.0.0.1:8000/"+"formation/pdfJSON/"+nb+"/");
hi.add(BorderLayout.CENTER, browser);
                        hi.   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

hi.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                                              refreshTheme();

                    }
                });


hi2.addAll(c1,c2,c3,c4,b,b1);
                       
                           // hi2.add(img1);
                            hi2.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                 
                 
                 
                 
                 
                 
                  list1.addAll(b2,b);
             Label lab=new Label(l.get(i).getNom_formation());
            list.addAll(lab,list1);
   // b.addActionListener(e -> f.deleteformation(l.get(i).g));
         
         }
        
         
                         searchField.getAllStyles().setFgColor(000000);

         
         
        searchField.addActionListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Form fi2=new Form(BoxLayout.y());
                            setTitle("Liste des formations");
              Container list6 = new Container(BoxLayout.y());
list6.setScrollableY(true);
        

      ServiceFormation f=new ServiceFormation();
                       ArrayList<Formation> l=new ArrayList<>();

           l=f.rechercher(searchField.getText());
                        System.out.println(l);
       
       
       //  System.out.println(l);
         for (int i=0;i<l.size();i++)
         {             Date d=l.get(i).getDate_debut();

             Date d1=l.get(i).getDate_fin();
             int nb =l.get(i).getId_formation();
             String s =l.get(i).getNom_formation();
                          String s1 =l.get(i).getDate_debut().toString();
             String s2 =l.get(i).getDate_fin().toString();
             String s3 =l.get(i).getDispositif();
             String s4 =l.get(i).getProgramme();

          Container list1 = new Container(BoxLayout.x());
            Button b = new Button("delete");
                  Button b2 = new Button("Détails");
                 b.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                         //      f.deleteformation(l.get(i).getId_formation());    

                          
                        Form hi2 = new Form(BoxLayout.y());
                     /*   hi2.getToolbar().addMaterialCommandToLeftBar(
                                "Back", FontImage.MATERIAL_ARROW_BACK
                                , new ActionListener<ActionEvent>() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                         ListerFormation lf=new ListerFormation(current);
                            }
                        });*/

                            try {
                                
                                Dialog.show("Success",s + "est supprimée",new Command("OK"));
                           f.deleteformation(nb);  
                            SpanLabel sp = new SpanLabel(s + "est supprimée");
                          //  sp.setText(l.get(i).getNom_formation());
                     previous.showBack();
                        //    hi2.add(sp);
                         //   hi2.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                                              refreshTheme();

                    }
                });
                 
                 
                        b2.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Form hi2 = new Form(BoxLayout.y());
                        hi2.   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                        try {
                          Label l1=new Label("Nom de la formation:");
                          
                                      Label l2=new Label("Date Début:");
                          Label l3=new Label("Date Fin:");
        ComboBox<String> dispo =new   ComboBox<String> (s3,"Presentiel","En_Ligne") ;

                                                    Label l4=new Label("Dispositif:");
                                                    TextField t1=new TextField(s);
                                                     t1.getAllStyles().setFgColor(000000);

                                      TextField t2=new TextField(s1);
                                       t2.getAllStyles().setFgColor(000000);

 Picker tfdeb = new Picker();
 tfdeb.setDate(d);
 tfdeb.getAllStyles().setFgColor(000000);
  Picker tffin = new Picker();
 tffin.setDate(d1);
  tffin.getAllStyles().setFgColor(000000);

        TextField t3=new TextField(s2);
         t3.getAllStyles().setFgColor(000000);

              TextField t4=new TextField(s3);
               t4.getAllStyles().setFgColor(000000);

              Formation f4=new Formation();
                  Container c1 = new Container(BoxLayout.x());
                  Container c2 = new Container(BoxLayout.x());
                  Container c3 = new Container(BoxLayout.x());
                  Container c4 = new Container(BoxLayout.x());
c1.addAll(l1,t1);
     c2.addAll(l2,tfdeb);c3.addAll(l3,tffin);
     
c4.addAll(l4,dispo);

Button b=new Button("update");
  b.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                         //      f.deleteformation(l.get(i).getId_formation());    

                          
                       // Form hi2 = new Form(BoxLayout.y());
                     /*   hi2.getToolbar().addMaterialCommandToLeftBar(
                                "Back", FontImage.MATERIAL_ARROW_BACK
                                , new ActionListener<ActionEvent>() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                         ListerFormation lf=new ListerFormation(current);
                            }
                        });*/

                            try {
                                f4.setId_formation(nb);
                                                                f4.setNom_formation(t1.getText());
                                                                f4.setDate_debut(tfdeb.getDate());
                                                                f4.setDate_fin(tffin.getDate());
                                                                f4.setDispositif(dispo.getSelectedItem());
                                                                f4.setProgramme(s4);

                                   f.UpdateFormation(f4,nb); 
                                Dialog.show("Success",s + "updated",new Command("OK"));
                         
                          previous.showBack();
                          //  sp.setText(l.get(i).getNom_formation());
                     
                        //    hi2.add(sp);
                         //   hi2.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                                              refreshTheme();

                    }
                });
Button b1=new Button("PDF");

 b1.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                  

                            try {
                              		//Desktop desktop = java.awt.Desktop.getDesktop();

                                //   f.PDFFormation(nb); 
                          //       BrowserComponent browser = new BrowserComponent();
                              /*  SwingController controller = new SwingController();

                                SwingViewBuilder factory = new SwingViewBuilder(controller);

                                JPanel viewerComponentPanel = factory.buildViewerPanel();

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

                                JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //applicationFrame.getContentPane().add(viewerComponentPanel);
        applicationFrame.add(viewerComponentPanel);
        
        controller.openDocument("http://127.0.0.1:8000/"+"formation/pdfJSON/"+nb+"/");

        applicationFrame.pack();
        applicationFrame.setVisible(true);*/
         //   browser.setURLHierarchy( "http://127.0.0.1:8000/"+"formation/pdfJSON/"+nb+"/");

                     Form hi = new Form("Browser", new BorderLayout());
BrowserComponent browser = new BrowserComponent();
browser.setURL("http://127.0.0.1:8000/"+"formation/pdfJSON/"+nb+"/");
hi.add(BorderLayout.CENTER, browser);
hi.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                                              refreshTheme();

                    }
                });


hi2.addAll(c1,c2,c3,c4,b,b1);
                       
                           // hi2.add(img1);
                            hi2.show();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                 
                 
                 
                 
                 
                  list1.addAll(b2,b);
             Label lab=new Label(l.get(i).getNom_formation());
            list6.addAll(lab,list1);
        
         }
        
           fi2.   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
   fi2.add(list6);
       
                                    fi2.show();
            
                 
                    }
                });
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
       // sp.setText(f.getAllFormations().toString());
       AutoCompleteTextField ac = new AutoCompleteTextField(f.getAllFormations().toString());
       Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");
       FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
         add(searchField);

ac.setMinimumElementsShownInPopup(5);
        add(sp);
         add(list);
   

}}
