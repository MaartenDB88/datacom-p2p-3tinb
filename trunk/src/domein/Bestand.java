/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.File;

/**
 *
 * @author Maarten
 */
public class Bestand {

  private  File bestand;
  private  String ipAdress;

    public Bestand(File bestand, String ipAdress) {
        this.bestand = bestand;
        this.ipAdress = ipAdress;
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
