/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import netwerk.NetwerkController;

/**
 *
 * @author Maarten
 */
public class DomeinController {

    public NetwerkController getnController() {
        return nController;
    }

    List<Bestand> files;
    private BestandLijst bestandLijst;
    BestandTableModel model;


    Downloads downloads = null;
    DownloadsListModel downloadModel = null;
    NetwerkController nController = null;
       
    public DomeinController() {
        files = new ArrayList<Bestand>();
        bestandLijst = new BestandLijst();
        nController = new NetwerkController(this);
        downloadModel = new DownloadsListModel(this);
        downloads = new Downloads(downloadModel);
    }

    public Downloads getDownloads() {
        return downloads;
    }

    public void addFile(Bestand bestand) {
        files.add(bestand);
        model.update();

    }
    public void setSharedDirectory(String path) {
        bestandLijst.setDirectory(path);

    }
    public void setModel(BestandTableModel model) {
        this.model = model;
     
    }

      public List<Bestand> getFiles()
    {
       return files;
    }
    public Bestand[] getBestanden()
    {
        return bestandLijst.getBestanden();
    }

    public void deleteList()
    {
        files = new ArrayList<Bestand>();
    }

    public String getDirectory()
    {
        return bestandLijst.getDirectory();
    }

    public int addDownload(String name)
    {
      return downloads.addDownload(name);
    }

    public void changeProgressDownload(double progress,int index)
    {
        downloads.updateProgress(progress,index);
    }

    public void deleteDownload(int index)
    {
        downloads.deleteDownload(index);
        nController.DownloadEnded(index);

    }
    public DownloadsListModel getDownloadModel() {
        return downloadModel;
    }
}
