package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.*;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendList implements Runnable {

    DomeinController controller;

    public SendList(DomeinController c) {
        controller = c;
    }

    public void run() {
        MulticastSocket socket = null;
        InetAddress address;
        try {
            String test = "/" + InetAddress.getLocalHost().getHostAddress();
            socket = new MulticastSocket(4446);
            address = InetAddress.getByName("230.0.0.1");

            socket.joinGroup(address);
            DatagramPacket packet;


            while (true) {

                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                System.out.println("Waiting to Send list...");
                socket.receive(packet);
                if (!test.equals(packet.getAddress().toString())) {
                    {

                        Bestand[] files = controller.getBestanden();
                        Socket sock = new Socket(packet.getAddress(), 13268);
                        System.out.println("Sending list too : " + sock);

                        ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
                        os.writeObject(files);

                        

                        sock.close();
                        System.out.println("Sending list completed");
                    }


                }
            }


        } catch (Exception ex) {

            Logger.getLogger(SendList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
