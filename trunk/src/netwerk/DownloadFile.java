/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Administrator
 */
public class DownloadFile implements Runnable {

    File file = null;
    String ipAddress;
    Bestand bestand = null;
    int port = 13267;
    Socket socket = null;
    DataInputStream fileIn = null;
    DataOutputStream fileOut = null;
    byte[] bytes = new byte[1024];
    boolean stopDownload = false;
    boolean pauseDownload = false;
    DomeinController dController;
    int id;
    int length;
    double totalBytes;
    double bytesDone = 0;
    int progress = 0;

    public DownloadFile(DomeinController c, int id) {
        this.id = id;
        dController = c;
    }

    public void run() {

        try {
            socket = new Socket(ipAddress, port);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(file);
            socket.close();

            System.out.println("Initialize Download");
            socket = new Socket(ipAddress, port);
            fileIn = new DataInputStream(socket.getInputStream());
            
            fileOut = new DataOutputStream(new FileOutputStream(new File(dController.getDirectory() + "\\" + file.getName())));
            System.out.println("Begin Download");
            
            while ((length = fileIn.read(bytes)) != -1) {
                bytesDone = bytesDone + length;
                fileOut.write(bytes, 0, length);
                fileOut.flush();
                progress();
                

            }

            fileIn.close();
            fileOut.close();
            socket.close();
            System.out.println("Download ended");
            dController.deleteDownload();

        } catch (Exception e) {
            System.out.println(e.toString() + "Download.java");
        }
    }

    public void progress()
    {
        if(((int)((bytesDone / totalBytes)*100))>progress)
        {
           dController.changeProgressDownload((bytesDone / totalBytes)*100);
        }
    }

    public void Download(Bestand b) {
        file = b.getBestand();
        bestand = b;
        totalBytes = (int) bestand.getSize();
        ipAddress = b.getIpAdress();
        pauseDownload = false;
        stopDownload = false;


    }

    public void PauseDownload() {
        if (pauseDownload) {
            pauseDownload = false;
        } else {
            pauseDownload = true;
        }
    }

    public void StopDownload() {
        this.stopDownload = true;
    }
}
