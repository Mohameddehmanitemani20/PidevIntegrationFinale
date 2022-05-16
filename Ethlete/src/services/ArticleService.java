/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IService;
import interfaces.IService1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Article;
import util.DataSource;

/**
 *
 * @author MSI
 */
public class ArticleService implements IService1<Article> {
    
    Connection cnx;

    public ArticleService() {
        cnx=DataSource.getInstance().getCnx();
    }
    

    @Override
    public void ajouter(Article t) {
        try {
            Statement st;
            st=cnx.createStatement();
            String query="INSERT INTO `article`( `titre`, `contenu`, `description`, `nbrLike`, `idUser`) VALUES ('"+t.getTitre()+"','"+t.getContenu()+"','"+t.getDescription()+"','"+t.getNbrLike()+"','"+t.getIdUser()+"')";
            st.executeUpdate(query);
            System.out.println("article ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(long id_amodifier, Article t) {
       try {
            Statement st;
            st=cnx.createStatement();
            String query="UPDATE `article` SET `titre`='"+t.getTitre()+"',`contenu`='"+t.getContenu()+"',`description`='"+t.getDescription()+"',`nbrLike`='"+t.getNbrLike()+"' WHERE idArticle="+id_amodifier ;
            if(st.executeUpdate(query)==1){
            System.out.println("article modifiee avec success");
            }else {
                System.out.println("article n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(long id) {
         try {
            Statement st;
            st=cnx.createStatement();
            String query="delete from article where idArticle="+id;
            if(st.executeUpdate(query)==1){
            System.out.println("suppression avec success");
            }else {
                System.out.println("article n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Article> afficher() {
        List<Article> la=new ArrayList<>();
        try {
            
            Statement st=cnx.createStatement();
            String query="select * from article";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Article a =new Article();
                a.setIdAticle(rs.getLong("idArticle"));
                a.setContenu(rs.getString("contenu"));
                a.setDescription(rs.getString("description"));
                
                a.setTitre(rs.getString("titre"));
                a.setNbrLike(rs.getInt("nbrLike"));
                a.setIdUser(rs.getInt("idUser"));
                la.add(a);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return la;
    }
    
    public Article findById(long idArticle){
         Article a =new Article();
          try {
            Statement st=cnx.createStatement();
            String query="select * from article where idArticle="+idArticle;
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                
                a.setTitre(rs.getString("titre"));
                a.setContenu(rs.getString("contenu"));
                a.setDescription(rs.getString("description"));
                a.setIdAticle(rs.getLong("idArticle"));
                
                a.setNbrLike(rs.getInt("nbrLike"));
                a.setIdUser(rs.getLong("idUser"));
            }else{
                System.out.println("article n existe pas");
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
        
    }
    
    public List<Article> findByTitre(String titre){
        List<Article> articles=afficher();
        List<Article> resultat=articles.stream().filter(article->titre.equals(article.getTitre())).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l article n existe pas");
        }else{
            System.out.println("l article existe");
        }
        return resultat;
    }
    
     public List<Article> findByNbrLike(int nbrLike){
        List<Article> articles=afficher();
        List<Article> resultat=articles.stream().filter(article->nbrLike==article.getNbrLike()).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l article n existe pas");
        }else{
            System.out.println("l article existe");
        }
        return resultat;
    }
     
    public List<Article> sortByIdArticle(){
        List<Article> articles=afficher();
        List<Article> resultat=articles.stream().sorted(Comparator.comparing(Article::getIdAticle)).collect(Collectors.toList());
        return resultat;
    }
    
     public List<Article> sortByNbrLike(){
        List<Article> articles=afficher();
        List<Article> resultat=articles.stream().sorted(Comparator.comparing(Article::getNbrLike)).collect(Collectors.toList());
        return resultat;
    }
}
