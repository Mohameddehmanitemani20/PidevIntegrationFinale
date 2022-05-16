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
public class AffectationFormateur {
      private  int formateur_idt;
       private int formation_id;
       private int  reponse;
       
}
