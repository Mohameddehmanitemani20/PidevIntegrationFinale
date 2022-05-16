/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.pidev.entities.Commentaire;
import com.pidev.entities.Formation;
import com.pidev.services.ServiceCommentaire;
import com.pidev.services.ServiceFormation;
import java.util.ArrayList;
import java.util.Date;
import javafx.util.Duration;


/**
 *
 * @author pc
 */
public class Participer   extends SideMenuBaseForm {
            Form current;

     public Participer(Form previous,Resources res) {
                  super(BoxLayout.y());

        setTitle("Liste des formations");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("logoviolet.png");
            ImageViewer img1 = new ImageViewer(profilePic);

        profilePic.scaled(5, 5);
        //Image mask = res.getImage("round-mask.png");
       // profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
       // Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
       /// profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label("", ""),
                        new Label(" ", "")
                );
        //remainingTasks.setUIID("");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("", ""),
                        new Label(" ", "")
        );
       // completedTasks.setUIID("");

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(" ", ""),
                                    new Label("", "")
                                )
                            ).add(BorderLayout.WEST, img1),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        
       // add(new Label("Today", "TodayTitle"));
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
      
       
        setupSideMenu(res);
        
           current=this; //Back 
     //   setTitle("Home");
      //  setLayout(BoxLayout.y());
        String[] abc = new String[] {"A", "B", "C"};
         Container list = new Container(BoxLayout.y());
list.setScrollableY(true);

      ServiceFormation f=new ServiceFormation();
     //  f.getAllFormations();
       SpanLabel sp = new SpanLabel();
         ArrayList<Formation> l=f.getAllFormations();
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
            Button b = new Button("Partciper");
          //     Button b1 = new Button("update");
                 b.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                         

                            try {
                                f.participerformation(nb, 18);
                                Dialog.show("Success", "vous êtes participé à"+s,new Command("OK"));
                     
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
           
                                
                                
                                

                                   f.PDFFormation(nb); 
                 

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

                 
                                  Button b2=new Button("Commenter");

                 
                 
              b2.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                  

                            try {
                                
                                
                                      Form CFORM=new Form(BoxLayout.y());
                         TextField comment = new TextField("", "Entrer Commentaire", 20, TextArea.ANY);
                                                 comment.getAllStyles().setFgColor(000000);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
Button b=new Button("valider");

b.addPointerPressedListener(new ActionListener() {
                          
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                   if ((comment.getText().length()==0))
                    Dialog.show("Alert", "Le commentaire ne doit pas être vide", new Command("OK"));
                else
                {

                            try {
                                
                                
                                
                                Commentaire c=new Commentaire(comment.getText(),11,nb);
                                ServiceCommentaire sc =new ServiceCommentaire();
                                sc.addCommentaire(c, 11, nb);
/*                              TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Nextec application ");
        tray.setMessage("Merci pour votre commentaire.");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(5000));*/
                                                Dialog.show("Alert", "Merci pour votre commentaire", new Command("OK"));
                            
                            }
                            catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }  }}
                });

           CFORM.   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());


                                 /// getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

CFORM.addAll(comment,b);
CFORM.show();
                  
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                                              refreshTheme();

                    }
                });
                                             //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());


                 
                 
                  list1.addAll(b,b1,b2);
             Label lab=new Label(l.get(i).getNom_formation());
            list.addAll(lab,list1);
         
         }
         
         
        add(sp);
         add(list);
    }
 @Override
    protected void showOtherForm(Resources res) {
        //new HomeFormation(res).show();
    }
 
}
