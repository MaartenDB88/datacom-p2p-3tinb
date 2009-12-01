/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maarten
 */
public class BestandLijst {

    List<Bestand> bestanden;

    public BestandLijst() {
        bestanden = new ArrayList<Bestand>();
    }

    public String getDirectory() {
        String directory = null;
        try {
            FileInputStream fstream = new FileInputStream("Info.txt");

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

            BufferedWriter bw = new BufferedWriter(new FileWriter("Info.txt"));
            bw.write(directory);
            bw.close();

        } catch (IOException ex) {
        }
    }


    public Bestand[] getBestanden() {
        int i = 0;
        File pathName = new File(getDirectory());
        File[] files = pathName.listFiles();
        List<File> fileLijst = new ArrayList<File>();
        for(File f : files)
        {
            if(f.isFile())
            fileLijst.add(f);
        }
        Bestand[] bestanden = new Bestand[fileLijst.size()];

        for(File f:fileLijst)
        {
            bestanden[i] = new Bestand(f);
            i++;
        }
        return bestanden;
    }
}

