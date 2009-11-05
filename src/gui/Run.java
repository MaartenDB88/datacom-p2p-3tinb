/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;

/**
 *
 * @author Administrator
 */
public class Run {

    private static DomeinController c = new DomeinController();
    private static Main mainFrame = new Main(c) ;

    public static void main(String[] arg) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                mainFrame.setVisible(true);
            }
        });

    }
}
