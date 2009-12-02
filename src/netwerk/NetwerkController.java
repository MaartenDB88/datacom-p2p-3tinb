/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class NetwerkController {

    ExecutorService threadDownload = null;
    ExecutorService threadReceiveList = null;
    ReceiveList receiveList = null;
    DomeinController domeinController = null;
    ServerSocket serverSocketUpload = null;
    ServerSocket serverSocketStartUpload = null;
    ServerSocket serverSocketList = null;
    int downloadId = 0;
    List<DownloadFile> downloads = new ArrayList<DownloadFile>();

    public NetwerkController(DomeinController controller) {
        try {
            serverSocketStartUpload = new ServerSocket(13267);
            serverSocketUpload = new ServerSocket(13269);
            serverSocketList = new ServerSocket(13268);
            this.domeinController = controller;

            ExecutorService threadExecutor2 = Executors.newFixedThreadPool(2);
            threadExecutor2.execute(new SendList(domeinController));
            threadExecutor2.execute(new UploadListener(serverSocketStartUpload, this));


        } catch (IOException ex) {
            Logger.getLogger(NetwerkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int StartDownload(Bestand bestand) {

        int index = domeinController.addDownload(bestand.getBestand().getName());
        DownloadFile download = new DownloadFile(domeinController, index, serverSocketStartUpload.getLocalPort(), serverSocketUpload.getLocalPort());
        download.Download(bestand);
        downloads.add(download);
        Thread thread = new Thread(download);
        thread.start();

        return 0;

    }
    public void DownloadEnded(int id)
    {
        downloads.remove(getDownloadFile(id));
    }

    public void PauseResumeDownload(int id) {
        getDownloadFile(id).PauseDownload();
    }

    public void StopDownload(int id) {
        getDownloadFile(id).StopDownload();

    }

    public void ReceiveList() {
        threadReceiveList = Executors.newFixedThreadPool(1);
        receiveList = new ReceiveList(domeinController, serverSocketList);
        receiveList.setTest(true);
        threadReceiveList.execute(receiveList);
    }

    public void StopReceivList() {
        if (!threadReceiveList.isShutdown()) {
            receiveList.setTest(false);
            threadReceiveList.shutdown();
        }
    }

    public void StartUpload(File file) {
        UploadFile upload = new UploadFile(file, serverSocketUpload);
        Thread thread = new Thread(upload);
        thread.start();
    }

    public DownloadFile getDownloadFile(int id)
    {
        for(DownloadFile d:downloads)
        {
            if(d.id == id)
                return d;
        }
        return null;
    }
}
