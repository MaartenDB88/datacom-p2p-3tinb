/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ReceiveList implements Runnable {

    DomeinController controller;
    DatagramSocket dgSocket = null;
    boolean test = true;
    ServerSocket servsock;

    public ReceiveList(DomeinController c, ServerSocket s) {
        controller = c;
        servsock = s;

    }

    public void run() {
        try {
            dgSocket = new DatagramSocket(4445);
            byte[] buffer = new byte[256];
            String dString = "!!";
            buffer = dString.getBytes();
            // send it
            InetAddress group = InetAddress.getByName("230.0.0.1");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 4446);
            dgSocket.send(packet);
            dgSocket.close();

            
            while (true) {
                //System.out.println("Gimme the goods");
                Socket sock = servsock.accept();
                //Socket sock = new Socket("127.0.0.1", 13267);

                InputStream is = sock.getInputStream();
                ObjectInputStream o = new ObjectInputStream(is);

                List<Bestand> files = (List<Bestand>) o.readObject();
                for (Bestand b : files) {
                   b.setIpAdress(sock.getLocalAddress().toString());
                    controller.addFile(b);
                }
                o.close();
                sock.close();
                
              
            }
              
              
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceiveList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ReceiveList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReceiveList.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

}
