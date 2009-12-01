/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import netwerk.*;

/**
 *
 * @author Administrator
 */
public class Run {

    private static DomeinController c = new DomeinController();
    private static Main mainFrame = null;
   

    public static void main(String[] arg) {
        
           mainFrame = new Main(c);

            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    mainFrame.setVisible(true);
                }
            });
    }
}
