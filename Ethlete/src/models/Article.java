/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author MSI
 */
public class Article {
    
    private long idAticle;
        private long idUser;

    private String titre;
    private String contenu;
    private String description;
    
    private int nbrLike ;

    public Article() {
    }

    public Article(long idUser, String titre, String contenu, String description, int nbrLike) {
        this.idUser = idUser;
        this.titre = titre;
        this.contenu = contenu;
        this.description = description;
        this.nbrLike = nbrLike;
    }

    public Article(long idAticle, long idUser, String titre, String contenu, String description, int nbrLike) {
        this.idAticle = idAticle;
        this.idUser = idUser;
        this.titre = titre;
        this.contenu = contenu;
        this.description = description;
        this.nbrLike = nbrLike;
    }

   

    public long getIdAticle() {
        return idAticle;
    }

    public void setIdAticle(long idAticle) {
        this.idAticle = idAticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public int getNbrLike() {
        return nbrLike;
    }

    public void setNbrLike(int nbrLike) {
        this.nbrLike = nbrLike;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Article{" + "idAticle=" + idAticle + ", idUser=" + idUser + ", titre=" + titre + ", contenu=" + contenu + ", description=" + description + ", nbrLike=" + nbrLike + '}';
    }

    

    
    
    

   
    
    
    
}
