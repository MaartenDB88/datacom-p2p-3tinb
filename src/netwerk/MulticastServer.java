package netwerk;

import domein.Bestand;
import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastServer {

    protected boolean error = true;
    protected DatagramSocket socket = null;


    public MulticastServer() throws IOException {
     socket = new DatagramSocket(4445);
    }

    public void getLijst(List<Bestand> bestanden) {
         try {
        
                byte[] buf = new byte[1024];
                String dString = "";                
                buf = dString.getBytes();
                // send it
                InetAddress group = InetAddress.getByName("230.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
                error = false;
            }
        
        socket.close();
    }
}
