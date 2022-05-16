/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import services.ServiceParticipation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static java.util.Spliterators.*;
import javax.swing.JLabel;
/**
 *
 * @author pc
 */
public class Impression implements Printable {

    
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                }
                /* On définit une marge pour l'impression */
                int marge=30;

                /* On récupère les coordonnées des bords de la page */
                int x = (int)pageFormat.getImageableX();
                int y = (int)pageFormat.getImageableY();
                int w = (int)pageFormat.getImageableWidth();
                int h = (int)pageFormat.getImageableHeight();

                /* Dessin d'un cadre gris clair*/
                graphics.setColor(Color.WHITE);
                graphics.fillRect(x+30, y+10, w-20, h-20);

                /* On écrit une ligne de titre en rouge, en gras de taille 18 */
                graphics.setFont(new Font("Arial", Font.BOLD, 18));
                graphics.setColor(Color.RED);
                graphics.drawString("Liste des Participants par formation\n", x + marge, y+marge);
              
ServiceParticipation s1= new ServiceParticipation();
JLabel j = new JLabel();
Map<Formation, List<User>> map1=new HashMap();
map1=s1.consulter_particiapnts_par_formation();
                /* On écrit une ligne en noir de taille 14 */
                graphics.setFont(new Font("Arial", Font.PLAIN, 14));
                graphics.setColor(Color.BLACK);
                int i=0;
                List<User> list=new ArrayList();
                for(Formation f:map1.keySet())
                { i+=40;
                    
                    
                    graphics.drawString(f.getNom_formation()+":", x + marge, y+marge+i);
                    list=map1.get(f);
 for (int k=0;k<list.size();k++)
 {i+=40;                          
                    
                    graphics.drawString(map1.get(f).get(k).getPrenom(), x + marge, y+marge+i);}
                    i+=40;

}
             


                return PAGE_EXISTS;
        }

  
}
