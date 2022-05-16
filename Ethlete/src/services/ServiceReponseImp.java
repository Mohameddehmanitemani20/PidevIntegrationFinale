/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import util.DataSource;
import models.AffectationFormateur;
import models.Formation;
import models.Reponse_Form;
import interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 *
 * @author pc
 */
@NoArgsConstructor
public class ServiceReponseImp implements IService <Reponse_Form> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reponse_Form t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Reponse_Form t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reponse_Form t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reponse_Form> afficher() {
List<Reponse_Form> list = new ArrayList<>();
        
        
        try{    String req = "SELECT * FROM reponse ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Reponse_Form(rs.getInt(1),rs.getString(2)));
                
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;     }
    
    public Reponse_Form consulter_rep_accep()
    {
    Reponse_Form r=new Reponse_Form();
    r=this.afficher().stream().filter(e->e.getReponse().equals("confirmé")).findFirst().get();
    return r;}
        public Reponse_Form consulter_rep_ref()
    {
    Reponse_Form r=new Reponse_Form();
    r=this.afficher().stream().filter(e->e.getReponse().equals("refusé")).findFirst().get();
    return r;}
            public Reponse_Form consulter_rep_non_cons()
    {
    Reponse_Form r=new Reponse_Form();
    r=this.afficher().stream().filter(e->e.getReponse().equals("Pas encore consulté")).findFirst().get();
    return r;}
            
                   public Reponse_Form consulter_rep(int idrep)
    {
    Reponse_Form r=new Reponse_Form();
    r=this.afficher().stream().filter(e->e.getId_reponse()==idrep).findFirst().get();
    return r;}
            
            
            
}
