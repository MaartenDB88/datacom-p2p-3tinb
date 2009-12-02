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

    public Download(String name,int id) {
        this.id = id;
        this.name = name;
    }
    int id;

    String name;
   String progress = "Waiting ...";

    public int getId() {
        return id;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = Integer.toString(progress)  + " %";
    }


}
