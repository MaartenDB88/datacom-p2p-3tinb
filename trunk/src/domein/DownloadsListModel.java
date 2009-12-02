/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domein;

import javax.swing.AbstractListModel;

/**
 *
 * @author Administrator
 */
public class DownloadsListModel extends AbstractListModel {

    private DomeinController d;
    public DownloadsListModel(DomeinController d)
    {
        this.d = d;
       // d.setModelL(this);
    }
    public int getSize() {
       return d.getDownloads().getLijst().length;
    }

    public Object getElementAt(int index) {
        return  d.getDownloads().getLijst()[index].getProgress()+ " " + ":  " +" "  + d.getDownloads().getLijst()[index].name ;
    }
    public int getIndexAt(int index) {
        return  d.getDownloads().getLijst()[index].getId();
    }

    @Override
    protected void fireContentsChanged(Object source, int index0, int index1) {
        super.fireContentsChanged(this, index0, index1);
    }
    

}
