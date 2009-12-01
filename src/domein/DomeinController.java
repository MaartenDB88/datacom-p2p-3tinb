/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.BestandLijst;
import netwerk.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Maarten
 */
public class DomeinController {

    List<Bestand> files;
    private BestandLijst bestandLijst;
    BestandTableModel model;
    
    public DomeinController() {
        files = new ArrayList<Bestand>();
        bestandLijst = new BestandLijst();

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

}
