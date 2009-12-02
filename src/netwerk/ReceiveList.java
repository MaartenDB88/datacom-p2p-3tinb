/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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

            InetAddress group = InetAddress.getByName("230.0.0.1");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 4446);
            dgSocket.send(packet);
            dgSocket.close();


            while (true) {
                if (test) {
                    Socket sock = servsock.accept();
                    System.out.println("Gimme the goods");


                    InputStream is = sock.getInputStream();
                    ObjectInputStream o = new ObjectInputStream(is);

                    Bestand[] files = (Bestand[]) o.readObject();
                    for (Bestand b : files) {
                        int aantal = sock.getLocalAddress().toString().length();
                        b.setIpAdress(sock.getInetAddress().toString().substring(1, aantal));
                        controller.addFile(b);
                    }
                    o.close();
                    sock.close();
                    System.out.println("List received");
                }
            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceiveList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ReceiveList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReceiveList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTest(boolean test) {
        this.test = test;
    }
}
