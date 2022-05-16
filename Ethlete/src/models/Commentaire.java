/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author pc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {
    private int id_comment;
     private String comments;
    
    private int id_user;
    private int id_form;

    public Commentaire(String comments, int id_user, int id_form) {
        this.comments = comments;
        this.id_user = id_user;
        this.id_form = id_form;
    }
    
   
}
