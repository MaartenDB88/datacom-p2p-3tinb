/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netwerk;

import domein.Bestand;
import domein.DomeinController;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
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
    ServerSocket serverSocketList = null;
    HashMap downloadMap = new HashMap();
    int downloadId = 0;
    

    public NetwerkController(DomeinController controller) {
        try {
            serverSocketUpload  = new ServerSocket(13267);
            serverSocketList  = new ServerSocket(13268);
            this.domeinController = controller;
            
                   
            ExecutorService threadExecutor2 = Executors.newFixedThreadPool(2);
            threadExecutor2.execute(new SendList(domeinController));
            threadExecutor2.execute(new UploadListener(serverSocketUpload,controller));
            

        } catch (IOException ex) {
            Logger.getLogger(NetwerkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int StartDownload(Bestand bestand) {

        domeinController.addDownload(bestand.getBestand().getName());
        DownloadFile download = new DownloadFile(domeinController,downloadId);
        
        download.Download(bestand);
        Thread thread = new Thread(download);
        thread.start();
      
        return 0;

    }

    public void PauseResumeDownload() {
      
    }

    public void StopDownload() {
       
    }

    public void ReceiveList() {
        threadReceiveList = Executors.newFixedThreadPool(1);
        receiveList = new ReceiveList(domeinController, serverSocketList);
        receiveList.setTest(true);
        threadReceiveList.execute(receiveList);
    }

    public void StopReceivList()
    {
        if(!threadReceiveList.isShutdown())
        {
            receiveList.setTest(false);
            threadReceiveList.shutdown();
        }
    }
}
