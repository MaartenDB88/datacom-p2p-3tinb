/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.Byte;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Administrator
 */
public class UploadFile implements Runnable {

    File file;
    ServerSocket serverSocket = null;
    
    public UploadFile(File b, ServerSocket s,DomeinController d) {
        file = b;
        serverSocket = s;
    }

    public void run() {
        DataInputStream in = null;
        Socket socket = null;
        DataOutputStream out = null;
        int length;
     
        byte[] bytes = new byte[8192];

        try {
            System.out.println("Waiting Upload ...");
            socket = serverSocket.accept();
            System.out.println("Upload Started!");
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(new FileInputStream(file));
            
            while ((length = in.read(bytes))!=-1) {
                
                out.write(bytes,0,length);
                out.flush();               
            }
            in.close();
            out.close();
            socket.close();
            System.out.println("Sending File Completed");
        } catch (Exception ex) {
            System.out.println(ex.toString() + "Upload.java");
        }
    }
}


