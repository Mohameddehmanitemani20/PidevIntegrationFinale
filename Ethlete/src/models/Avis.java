/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author anasl
 */
public class Avis {
    private long id_avis;
    private long id_user;
    private Integer note;

    public Avis() {
    }

    public Avis(long id_avis, long id_user, Integer note) {
        this.id_avis = id_avis;
        this.id_user = id_user;
        this.note = note;
    }

    public Avis(long id_user, Integer note) {
        this.id_user = id_user;
        this.note = note;
    }

  

    public long getId_avis() {
        return id_avis;
    }

    public long getId_user() {
        return id_user;
    }

    public Integer getnote() {
        return note;
    }

    public void setId_avis(long id_avis) {
        this.id_avis = id_avis;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setnote(Integer note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "Avis{" + "id_avis=" + id_avis + ", id_user=" + id_user + ", note=" + note + '}';
    }
    
  
    
}
