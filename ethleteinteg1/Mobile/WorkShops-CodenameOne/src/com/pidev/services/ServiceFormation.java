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
import com.pidev.entities.Formation;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 *
 * @author pc
 */
public class ServiceFormation {
       public ArrayList<Formation> formations=new ArrayList();
    
    public static ServiceFormation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFormation() {
         req = new ConnectionRequest();
    }

    public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }

    public boolean addFormation(Formation t) {
      //  System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = "http://127.0.0.1:8000/"+"formation/addFormationsJSON/";
    
           req.setUrl(url);
            req.setPost(false);
             req.addArgument("nomFormation", t.getNom_formation());
              req.addArgument("programme", t.getProgramme());
              String pattern = "yyyy-MM-dd";

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
String date = simpleDateFormat.format(t.getDate_debut());

String date1 = simpleDateFormat.format(t.getDate_fin());
       

              req.addArgument("dateDebut", date);
             
              req.addArgument("dateFin", date1);
              req.addArgument("dispositif", t.getDispositif());

 
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

    public ArrayList<Formation> parseFormations(String jsonText) {
        formations=new ArrayList<Formation>();
        try {
         
   
            JSONParser j = new JSONParser();
            Map<String,Object> formationsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)formationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Formation t = new Formation();
                int id = (int)obj.get("idFormation");
                t.setId_formation(1);
                t.setProgramme("");
                                t.setNom_formation("");
                t.setDispositif("");
    // t.setDate_debut(new Date());   
  
   //  t.setDate_fin(new Date());
               /* if (obj.get("name")==null)
              t.setName("null");
                else
                    t.setName(obj.get("name").toString());*/
               
                formations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return formations;
    }
    
    public ArrayList<Formation>  getAllFormations(){
   formations=new ArrayList<Formation>();
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = "http://127.0.0.1:8000/"+"formation/FormationsJSON";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
                try {
                    
    
                   // System.out.println(new String(req.getResponseData()));
                    JSONParser j = new JSONParser();
                    String jsonText =new String(req.getResponseData());
              Map<String,Object> formationsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                 List<Map<String,Object>> list = (List<Map<String,Object>>)formationsListJson.get("root");
                 //   formations= parseFormations(new String(req.getResponseData()));
                  //  System.out.println(formationsListJson);
                                 //       System.out.println(list);
                //    System.out.println(list);
             //Formation t =new Formation();
//  formations.add(new Formation(1,"",new Date(),new Date(),"",""));
        for(Map<String,Object> obj : list){
                            //    System.out.println(obj);
                       Formation t =new Formation();

       // System.out.println("test");
    //  obj=list.get(i).keySet();
  //  t.setId_formation((int)obj.get("idFormation"));
            //    System.out.println((int)Float.parseFloat(obj.get("idFormation").toString()));
 t.setId_formation((int)Float.parseFloat(obj.get("idFormation").toString()));                   
                
  //System.out.println(obj.get("dateDebut").toString());
               // System.out.println((int)Float.parseFloat(obj.get("idFormation").toString()));
             //   System.out.println((int)Float.parseFloat(obj.get("idFormation").toString()));

                                
         //  t = new Formation((int)Float.parseFloat(obj.get("idFormation").toString()),obj.get("nomFormation").toString(),obj.get("dispositif").toString(),
             // obj.get("programme").toString()
               
              //) ;

        //   t=new Formation();
              //  int id = (int)obj.get(0);
                //t.setId_formation(1);
                //t.setProgramme("");
                               // t.setNom_formation("");
             //   t.setDispositif("");
    // t.setDate_debut(new Date());   
  
   //  t.setDate_fin(new Date());
               /* if (obj.get("name")==null)
              t.setName("null");
                else
                    t.setName(obj.get("name").toString());*/
                  t.setProgramme(obj.get("programme").toString());
                                t.setNom_formation(obj.get("nomFormation").toString());
                t.setDispositif(obj.get("dispositif").toString());
                
                String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

Date date = simpleDateFormat.parse(obj.get("dateDebut").toString());
             Date date1 = simpleDateFormat.parse(obj.get("dateFin").toString());
   
 t.setDate_debut(date);
   t.setDate_fin(date1);
      //    System.out.println(t.toString());
                formations.add(t);
              //  System.out.println(formations);
               // System.out.println(t);
            }
                } catch (Exception ex) {
                    System.out.println("error1");
                }
            
                 
                req.removeResponseListener(this);
            }
        });
                           // formations= parseFormations(new String(req.getResponseData()));

        NetworkManager.getInstance().addToQueueAndWait(req);
     //   System.out.println(formations);
       return formations;
    }
    
    public void deleteformation(int t) {
      //  System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = "http://127.0.0.1:8000/"+ "formation/deleteFormationsJSON/"+t;
    
           req.setUrl(url);
            req.setPost(false);
            

 
          req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return resultOK;
    }
    
     public Formation  getById(int id){
        
                       Formation t =new Formation();

         req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = "http://127.0.0.1:8000/"+"formation/getFormationsJSON/"+id;
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
                try {
                    
    
                   // System.out.println(new String(req.getResponseData()));
                    JSONParser j = new JSONParser();
                    String jsonText =new String(req.getResponseData());
              Map<String,Object> formationsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                 List<Map<String,Object>> list = (List<Map<String,Object>>)formationsListJson.get("root");
          
      
         
        for(Map<String,Object> obj : list){

 t.setId_formation((int)Float.parseFloat(obj.get("idFormation").toString()));                   
                
  
                  t.setProgramme(obj.get("programme").toString());
                                t.setNom_formation(obj.get("nomFormation").toString());
                t.setDispositif(obj.get("dispositif").toString());
                
                String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

Date date = simpleDateFormat.parse(obj.get("dateDebut").toString());
             Date date1 = simpleDateFormat.parse(obj.get("dateFin").toString());
   
 t.setDate_debut(date);
   t.setDate_fin(date1);

            
          
            }
                } catch (Exception ex) {
                    System.out.println("error1");
                }
            
                 
                req.removeResponseListener(this);
            }
        });
                           // formations= parseFormations(new String(req.getResponseData()));

        NetworkManager.getInstance().addToQueueAndWait(req);
       return t;
    }
     public boolean UpdateFormation(Formation t,int id) {
      //  System.out.println(t);
        System.out.println("********");
         System.out.println(t.toString());
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = "http://127.0.0.1:8000/"+"formation/updateFormationsJSON/"+id+"/";
    
           req.setUrl(url);
            req.setPost(false);
             req.addArgument("nomFormation", t.getNom_formation());
            //  req.addArgument("programme", t.getProgramme());
              String pattern = "yyyy-MM-dd";

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
String date = simpleDateFormat.format(t.getDate_debut());

String date1 = simpleDateFormat.format(t.getDate_fin());
       

          req.addArgument("dateDebut", date);
             
              req.addArgument("dateFin", date1);
              req.addArgument("dispositif", t.getDispositif());

 
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
     
     
     public boolean PDFFormation(int id) {
      //  System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = "http://127.0.0.1:8000/"+"formation/pdfJSON/"+id+"/";
    
           req.setUrl(url);
            req.setPost(false);
           



 
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
     
     
     
     
     
        public void participerformation(int idF,int idU) {
      //  System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = "http://127.0.0.1:8000/"+ "participation/participerJSON/"+idF+"/"+idU;
    
           req.setUrl(url);
            req.setPost(false);
            

 
          req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return resultOK;
    }
        
   public ArrayList<Formation>  rechercher(String nom){
   formations=new ArrayList<Formation>();
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = "http://127.0.0.1:8000/"+"formation/rechercheJSON/"+nom+"/";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
                try {
                    
    
                    JSONParser j = new JSONParser();
                    String jsonText =new String(req.getResponseData());
              Map<String,Object> formationsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                 List<Map<String,Object>> list = (List<Map<String,Object>>)formationsListJson.get("root");
        
        for(Map<String,Object> obj : list){
                            //    System.out.println(obj);
                       Formation t =new Formation();

   
 t.setId_formation((int)Float.parseFloat(obj.get("idFormation").toString()));                   
                
 
                  t.setProgramme(obj.get("programme").toString());
                                t.setNom_formation(obj.get("nomFormation").toString());
                t.setDispositif(obj.get("dispositif").toString());
                
                String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

Date date = simpleDateFormat.parse(obj.get("dateDebut").toString());
             Date date1 = simpleDateFormat.parse(obj.get("dateFin").toString());
   
 t.setDate_debut(date);
   t.setDate_fin(date1);
                formations.add(t);
            
            }
                } catch (Exception ex) {
                    System.out.println("error1");
                }
            
                 
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    
       return formations;
    }  
    
}
