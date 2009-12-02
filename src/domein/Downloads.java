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

    int id = 0;
    HashMap map = new HashMap();
    List<Download> downloads = new ArrayList<Download>();

   DownloadsListModel test;

    public Downloads(DownloadsListModel test){
        this.test = test;
    }

    /**
     *
     * @return ID van de download.
     *
    **/
    public int getId()
    {
        return id++;
    }
    public int addDownload(String name) {
        int i = getId();      
         downloads.add(new Download(name,i));
          test.fireContentsChanged(null, i, i);
        return i;
    }

    public boolean deleteDownload(int index) {
       

        for(Download d :downloads)
        {
            if(d.id ==index)
            {
                downloads.remove(d);
                test.fireContentsChanged(null, index, index);
              
                return true;
            }
        }        
       return false;
    }

    public boolean updateProgress(double p,int index) {

         for(Download d :downloads)
        {
            if(d.id ==index)
            {
                d.setProgress((int)p);
               test.fireContentsChanged(null, index, index);
                              
                return true;
            }
        }

        return false;
    }

    public Download[] getLijst() {

        return downloads.toArray(new Download[downloads.size()]);

    }



}
