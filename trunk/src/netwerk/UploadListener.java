/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Administrator
 */
public class UploadListener implements Runnable {

    ServerSocket serverSocket = null;
    DomeinController dController = null;

    public UploadListener(ServerSocket socket,DomeinController d) {
        dController = d;
        this.serverSocket = socket;
    }

    public void run() {

        OutputStream out = null;
        Socket socket = null;
        try {
            while (true) {

                try {

                    System.out.println("UploadListener waiting ...");
                    socket = serverSocket.accept();
                    System.out.println("Accepted! / UploadListener");
                    InputStream is = socket.getInputStream();
                    ObjectInputStream o = new ObjectInputStream(is);

                    File file = (File) o.readObject();

                    new UploadFile(file, serverSocket,dController).run();
                    socket.close();
                      
                } catch (Exception ex) {System.out.println(ex.toString() + "UploadListener 1"); }

            }

        } catch (Exception e) {
            System.out.println(e.toString()+ "UploadListener 2");
        }


    }
}
