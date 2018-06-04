package vogt.prepa.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import vogt.prepa.entities.Pointage;

public class TempsReelService {

    List<Pointage> pointages = new ArrayList<Pointage>();

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public List<Pointage> getDerniersPointages() {

        int n = randInt(1, 4);
        for (int i = 0; i < n; i++) {
            pointages.add(new Pointage(randInt(1000, 9999) + "", "", randInt(10000, 99999) + "" + i, "", "", new Date()));

        }
        return pointages;
    }
}
