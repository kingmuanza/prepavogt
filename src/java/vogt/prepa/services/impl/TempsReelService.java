package vogt.prepa.services.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import vogt.prepa.entities.Pointage;

public class TempsReelService {

    List<Pointage> pointages = new ArrayList<Pointage>();

    public static final String CHEMIN = "C:\\Users\\muanz\\OneDrive\\Documents\\SIA\\";

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public List<Pointage> getDerniersPointages() throws IOException {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filename = sdf.format(date) + ".csv";
        System.out.println("C:\\Users\\muanz\\OneDrive\\Documents\\SIA\\2" + filename);
        BufferedReader br = null ;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\muanz\\OneDrive\\Documents\\SIA\\2" + filename);
            br = new BufferedReader(fileReader);
            System.out.println("YOUPIIIII !!! JAI TROUVE LE FICHIER  !");

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            sb.append(line);

            sb.append(System.lineSeparator());
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                String[] decoupe = line.split(",");
                System.out.println(Arrays.toString(decoupe));
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Le fichier que vous avez l'air de vouloir avoir envie !");
            return new ArrayList<Pointage>();
        } catch (IOException ex) {
            Logger.getLogger(TempsReelService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            br.close();
        }

        return pointages;
    }
}
