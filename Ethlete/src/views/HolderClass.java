/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author gaming
 */
public class HolderClass {
    static int compId;
    static int journeId;
    public static void setCompId(int id){
        HolderClass.compId=id;
    }
    public static int getCompId(){
        return compId;
    }

    public static int getJourneId() {
        return journeId;
    }

    public static void setJourneId(int journeId) {
        HolderClass.journeId = journeId;
    }
    
}
