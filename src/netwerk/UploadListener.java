/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Administrator
 */
public class UploadListener implements Runnable {

    ServerSocket serverSocket = null;
    NetwerkController controller =null;

    File file;

    public UploadListener(ServerSocket socket,NetwerkController c) {
        this.controller = c;
        this.serverSocket = socket;
    }

    public void run() {

        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        int length;

        byte[] bytes = new byte[819200];
        try {
            while (true) {

                try {

                    System.out.println("UploadListener waiting ...");
                    socket = serverSocket.accept();
                    System.out.println("Accepted! / UploadListener");
                    InputStream is = socket.getInputStream();
                    ObjectInputStream o = new ObjectInputStream(is);

                    controller.StartUpload((File) o.readObject());
                   

                } catch (Exception ex) {
                    System.out.println(ex.toString() + " UploadListener 1");
                     socket.close();
                }
                socket.close();
            }

        } catch (Exception e) {
            System.out.println(e.toString() + " UploadListener 2");
            
        }


    }
}
