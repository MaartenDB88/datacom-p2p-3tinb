package netwerk;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MulticastClient implements Runnable {

    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket(4446);
            InetAddress address = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(address);
            DatagramPacket packet;
            for (int i = 0; i < 5; i++) {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
               
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            }

            socket.leaveGroup(address);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String sendList(String s)
    {
        return null;
    }

   
}
