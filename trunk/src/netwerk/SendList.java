package netwerk;

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
            socket = new MulticastSocket(4446);
            address = InetAddress.getByName("230.0.0.1");

            socket.joinGroup(address);
            DatagramPacket packet;


            while (true) {

                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                File[] files = controller.getFiles();
                // ServerSocket servsock = new ServerSocket(13267);
                System.out.println("Waiting...");

                // Socket sock2 = servsock.accept();
                Socket sock = new Socket(packet.getAddress(), 13267);
                System.out.println("Accepted connection : " + sock);

                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
                os.writeObject(files);

                System.out.println("Sending...");

                sock.close();               
            }
            //socket.leaveGroup(address);
            // socket.close();

        } catch (Exception ex) {

            Logger.getLogger(SendList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
