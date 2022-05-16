/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pidev.entities.Commentaire;
import com.pidev.entities.Formation;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public class ServiceCommentaire {
     public ArrayList<Formation> formations=new ArrayList();
    
    public static ServiceCommentaire instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCommentaire() {
         req = new ConnectionRequest();
    }

    public static ServiceCommentaire getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;
    }

    public boolean addCommentaire(Commentaire c,int idU, int idF) {
      //  System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = "http://127.0.0.1:8000/"+"commentaire/newJSON/"+idF+"/"+idU;
    
           req.setUrl(url);
            req.setPost(false);
             req.addArgument("contenu",c.getComments());
         


 
          req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

   
 
    
   
   
     
     
     
     
}
