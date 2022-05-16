/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import lombok.*;


/**
 *
 * @author pc
 */
@Data
@NoArgsConstructor
public class Reponse_Form {

    public Reponse_Form(int id_reponse, String reponse) {
        this.id_reponse = id_reponse;
        this.reponse = reponse;
    }
    private int id_reponse;
    private String reponse;
}
