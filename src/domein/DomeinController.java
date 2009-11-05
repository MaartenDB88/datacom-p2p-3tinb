/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.BestandLijst;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Maarten
 */
public class DomeinController {

    List<Bestand> files;
    private BestandLijst bestandLijst;

    public DomeinController() {
        files = new ArrayList<Bestand>();
        bestandLijst = new BestandLijst();

    }

    public List<Bestand> getFiles() {
        return files;
    }

    public void addFile(Bestand bestand) {
        files.add(bestand);
    }

    public void createLijst() {
        files = new ArrayList<Bestand>();
        files = bestandLijst.getFiles();
    }

    public void setSharedDirectory(String path) {
        bestandLijst.setDirectory(path);

    }

    public void createLijst(BestandTableModel bt) {

        createLijst();
        bt.fireTableDataChanged();

    }
}
