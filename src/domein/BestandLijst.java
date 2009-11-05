/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maarten
 */
public class BestandLijst {

    List<Bestand> bestanden;

    public BestandLijst() {
         bestanden = new ArrayList<Bestand>();
        saveFiles();

    }

    public String getDirectory() {
        String directory = null;
        try {
            FileInputStream fstream = new FileInputStream("c://Info.txt");

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            directory = br.readLine();


            in.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return directory;
    }

    public void setDirectory(String directory) {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("c://Info.txt"));
            bw.write(directory);
            bw.close();

        } catch (IOException ex) {
        }



    }

    public List<Bestand> getFiles() {

        return bestanden;

    }

    public void saveFiles() {
       

        File pathName = new File(getDirectory());
        File[] files = pathName.listFiles();
        Bestand b;
        for (File f : files) {
            if (f.isFile()) {
                b = new Bestand(f, "A1");

                bestanden.add(b);
            }

        }


    }
}

