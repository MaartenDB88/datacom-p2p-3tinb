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

    public List<Bestand> getBestanden() {
        return files;
    }

    public void addFile(Bestand bestand) {
        files.add(bestand);
        model.update();

    }
/*
    public void createLijst() {
        files = new ArrayList<Bestand>();
        files = bestandLijst.getFiles();
        model.fireTableDataChanged();
    }*/
    public void createLijst()
    {
        /*
        ExecutorService threadExecutor = Executors.newFixedThreadPool(2);
        threadExecutor.execute(new MulticastServerThread());
        threadExecutor.execute(new UDPReceive());
        threadExecutor.shutdown();
         */
    }

    public void setSharedDirectory(String path) {
        bestandLijst.setDirectory(path);

    }

    public BestandTableModel getModel() {
        return model;
    }

    public void setModel(BestandTableModel model) {
        this.model = model;
    }

    public File[] getFiles()
    {
       return bestandLijst.GetFiles();
    }

}
