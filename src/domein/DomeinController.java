/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maarten
 */
public class DomeinController {

    List<Bestand> files;
    private BestandLijst bestandLijst;
    BestandTableModel model;
    Downloads downloads = new Downloads();
       
    public DomeinController() {
        files = new ArrayList<Bestand>();
        bestandLijst = new BestandLijst();

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

    public void addDownload(String name)
    {
        downloads.addDownload(name);
    }

    public void changeProgressDownload(double progress)
    {
        downloads.updateProgress(progress);
    }

    public void deleteDownload()
    {
        downloads.deleteDownload();
    }
}
