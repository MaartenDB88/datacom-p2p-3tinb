/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javax.swing.SwingUtilities;

/**
 *
 * @author Administrator
 */
public class Run {

    private static DomeinController c = new DomeinController();

   

    public static void main(String[] arg) {
        
           SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    new Main(c).setVisible(true);
                }
            });
    }
}
