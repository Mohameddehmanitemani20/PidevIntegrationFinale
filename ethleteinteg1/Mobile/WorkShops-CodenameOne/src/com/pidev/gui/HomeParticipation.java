/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;

/**
 *
 * @author pc
 */
public class HomeParticipation extends SideMenuBaseForm {
        Form current;

    public HomeParticipation() {
    }

     public HomeParticipation(Resources res) {
     super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("logoviolet.png");
        profilePic.scaled(5, 5);
            ImageViewer img1 = new ImageViewer(profilePic);

        //Image mask = res.getImage("round-mask.png");
       // profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
      //  Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
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
        
      

        
     
                Button btn1 = new Button("Participer des Formations");
        
     ;
                btn1.addActionListener(e-> new Participer(current,res).show());

        addAll(btn1);
    }
   @Override
    protected void showOtherForm(Resources res) {
        //new HomeFormation(res).show();
    }
    
}
