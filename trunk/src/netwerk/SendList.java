package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.*;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.List;
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
                socket.receive(packet);

// VERWIJDER COMMENTS ZODAT HIJ ZIJN EIGEN LIJST NIET KRIJGT
//               if(!test.equals(packet.getAddress().toString()))
//               {
                List<Bestand> files = controller.getFiles();

                System.out.println("Waiting...");


                Socket sock = new Socket(packet.getAddress(), 13267);
                System.out.println("Accepted connection : " + sock);

                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
                os.writeObject(files);

                System.out.println("Sending...");

                sock.close();
 //            }
            }


        } catch (Exception ex) {

            Logger.getLogger(SendList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
