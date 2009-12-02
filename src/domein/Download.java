/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domein;

/**
 *
 * @author Administrator
 */
public class Download {

    public Download(String name) {
        this.name = name;
    }

    String name;
   int progress = 0;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }


}
