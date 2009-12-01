/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author Maarten
 */
public class Bestand implements Serializable  {

  private  File bestand;
  private  String ipAdress;
  private  long size;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public Bestand(File bestand) {
        this.bestand = bestand;
         size =  bestand.length();
    }

    public File getBestand() {
        return bestand;
    }

    public void setBestand(File bestand) {
        this.bestand = bestand;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }
}
