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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Administrator
 */
public class DownloadFile implements Runnable {

    private File file = null;
    private String ipAddress;
    private Bestand bestand = null;
    private int port = 13269;
    private int portStart = 13267;
    private Socket socket = null;
    private DataInputStream fileIn = null;
    private DataOutputStream fileOut = null;
    private byte[] bytes = new byte[8192];
    private boolean stopDownload = false;
    private boolean pauseDownload = false;
    private DomeinController dController;
    int id;
    private int length = 16384;
    private double totalBytes;
    private double bytesDone = 0;
    private int progress = 0;

    public DownloadFile(DomeinController c, int id, int start, int download) {
        this.id = id;
        dController = c;
        this.portStart = start;
        this.port = download;
    }

    public void run() {

        try {
            socket = new Socket(ipAddress, portStart);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(file);

            socket.close();

            System.out.println("Initialize Download");
            socket = new Socket(ipAddress, port);
            fileIn = new DataInputStream(socket.getInputStream());

            fileOut = new DataOutputStream(new FileOutputStream(new File(dController.getDirectory() + file.getName())));
            System.out.println("Begin Download");

            while ((length = fileIn.read(bytes)) != -1 && stopDownload != true) {

                
                bytesDone = bytesDone + length;
                fileOut.write(bytes, 0, length);
                fileOut.flush();
              
                    while(pauseDownload && stopDownload != true)
                    {
                        Thread.sleep(1000);
                    }
                
                   progress();
            }

            fileIn.close();
            fileOut.close();
            socket.close();
            System.out.println("Download ended");
            dController.deleteDownload(id);


        } catch (Exception e) {
            System.out.println(e.toString() + "Download.java");
        }
    }

    public void progress() {
        if (((int) ((bytesDone / totalBytes) * 100)) > progress) {
            dController.changeProgressDownload((bytesDone / totalBytes) * 100, id);
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
