package netwerk;

import domein.DomeinController;
import java.io.*;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;
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

                ServerSocket servsock = new ServerSocket(13267);
                                   System.out.println("Waiting...");

                    Socket sock = servsock.accept();
                    System.out.println("Accepted connection : " + sock);

                    ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
                    os.writeObject(files);

                    System.out.println("Sending...");

                    sock.close();
                




                /*    for (int i = 0; i < 3; i++) {
                String message = "test";
                InetAddress add = packet.getAddress();
                int port = packet.getPort();
                DatagramSocket dgSocket = new DatagramSocket(4449);
                DatagramPacket outPacket = new DatagramPacket(message.getBytes(),
                message.length(), add, port);

                dgSocket.send(outPacket);
                dgSocket.close();
                Thread.sleep(300);
                System.out.println(add.toString());
                System.out.println(port);
                }
                 */

            }
            //socket.leaveGroup(address);
            // socket.close();

        } catch (Exception ex) {

            Logger.getLogger(SendList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
