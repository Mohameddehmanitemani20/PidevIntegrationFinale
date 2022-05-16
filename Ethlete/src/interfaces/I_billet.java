/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Billet;

/**
 *
 * @author 21624
 */
public interface I_billet {
    //ajouter
    public boolean ajouterBillet(Billet B);

    //lister
    public List<Billet> afficherBillet();

    //update
    public boolean modifierEvenement(Billet B);

    //delete
    public boolean supprimerEvenement(Billet B);
}
