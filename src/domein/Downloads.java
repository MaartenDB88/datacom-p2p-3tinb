/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Downloads {

    Download[] downloadLijst = new Download[1];
    List<Download> downloads = new ArrayList<Download>();
    DownloadsTableModel model;

    public Downloads(){

        downloadLijst[0] = new Download("NO DOWNLOADS");
    }
    public void addDownload(String name) {
       downloads.add(new Download(name));
        // downloadLijst[0] = new Download(name);
        Update();
    }

    public void deleteDownload() {
       
        downloads.remove(0);
        // downloadLijst[0] = new Download("NO DOWNLOADS");
        Update();
    }

    public void updateProgress(double p) {
        downloads.get(0).progress = (int)p;
        //downloadLijst[0].progress = (int)p;
        Update();
    }

    public Download[] getLijst() {

        return downloads.toArray(new Download[downloads.size()] );
      //  return downloadLijst;
    }

    public void setModel(DownloadsTableModel d) {
        model = d;
    }

    public void Update() {
        model.update();
    }
}
