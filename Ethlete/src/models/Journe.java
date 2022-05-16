/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author gaming
 */
public class Journe {
    private int id_journe;
    private int numJourne;
    private String date_journe;
    private int id_competition;
    public Journe(){
    }
    public Journe(int id_journe, int numJourne ,String date_journe,int id_competition){
        this.id_journe=id_journe;
        this.numJourne=numJourne;
        this.date_journe=date_journe;
        this.id_competition = id_competition;
        
    }

    public Journe(int id_journe, int numJourne, String date_journe) {
        this.id_journe = id_journe;
        this.numJourne = numJourne;
        this.date_journe = date_journe;
    }

    public Journe(int numJourne, String date_journe) {
        this.numJourne = numJourne;
        this.date_journe = date_journe;
    }

  
    public Journe(int numJourne, String date_journe,int id_competition){
        this.numJourne=numJourne;
        this.date_journe=date_journe;
        this.id_competition = id_competition;
    }

    public int getId_journe() {
        return id_journe;
    }

    public int getNumJourne() {
        return numJourne;
    }

    public String getDate_journe() {
        return date_journe;
    }

    public int getId_competition() {
        return id_competition;
    }
    

    public void setId_journe(int id_journe) {
        this.id_journe = id_journe;
    }

    public void setNumJourne(int numJourne) {
        this.numJourne = numJourne;
    }

    public void setDate_journe(String date_journe) {
        this.date_journe = date_journe;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    @Override
    public String toString() {
        return "journe{" + "id_journe=" + id_journe + ", numJourne=" + numJourne + ", date_journe=" + date_journe + ", id_competition=" + id_competition + '}';
    }
    
    

}
    
    

            
        
    

