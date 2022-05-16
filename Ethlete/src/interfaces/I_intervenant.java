/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static java.util.Collections.list;
import java.util.List;
import models.Intervenant;
/**
 *
 * @author 21624
 */
public interface I_intervenant {
    
    //ajouter
    public boolean ajouterIntervenant(Intervenant I);

    //lister
    public List<Intervenant> afficherIntervenant();

    //update
    public boolean modifierIntervenant(Intervenant I);

    //delete
    public boolean supprimerIntervenant(Intervenant I);
    
    
   public List<Intervenant> rechercherIntervenant(List<Intervenant> initialList,String input);
   public List<Intervenant> sortByNom();
   
}
