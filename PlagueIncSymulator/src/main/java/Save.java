import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
/**
 * Klasa Save służy do zapisu danych.
 */
public class Save {
    public static HashMap<String,Integer> saveMap;
    File filename;
    /**
     * Konstruktor Save storzy nową mapę oraz przypisuje wszytskim danym wartość początkową równą 0 na wypadek gdyby ulepszenie nie zostało wykonane.
     */
    public Save(){
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = dateObj.format(formatter);
        filename = new File("PlagueIncSymulator/Data/result.txt");

        saveMap = new HashMap<String,Integer>();
        saveMap.put("AirPlane",0);
        saveMap.put("Ship",0);
        saveMap.put("Infect1",0);
        saveMap.put("Infect2",0);
        saveMap.put("Infect3",0);
        saveMap.put("Kill1",0);
        saveMap.put("Kill2",0);
        saveMap.put("Kill3",0);
        saveMap.put("Kill4",0);
        saveMap.put("Kill5",0);


    }
    /**
     * Zapisuje numer dnia w którym ulepszono Samoloty.
     *
     * @param day numer dnia w którym wykonano ulepszenie
     */
    public void addAirPlaneDay(int day){saveMap.put("AirPlane",day);}
    /**
     * Zapisuje numer dnia w którym ulepszono Statki.
     *
     * @param day numer dnia w którym wykonano ulepszenie
     */
    public void addShipDay(int day){saveMap.put("Ship",day);}
    public void addEndGameDay(int day){saveMap.put("EndGame",day);}
    public void addInfectingProcessDay(int lvl, int day){saveMap.put("Infect" + lvl,day);}
    public void addKillingProcessDay(int lvl, int day){saveMap.put("Kill" + lvl,day);}
    public void saveData(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Airplane upgrade: " + saveMap.get("AirPlane"));
            writer.newLine();
            writer.write("Ship upgrade: " + saveMap.get("Ship"));
            writer.newLine();
            writer.write("2nd Infection upgrade: " + saveMap.get("Infect2"));
            writer.newLine();
            writer.write("3rd Infection upgrade: " + saveMap.get("Infect3"));
            writer.newLine();
            writer.write("1st Killing upgrade: " + saveMap.get("Kill1"));
            writer.newLine();
            writer.write("2nd Killing upgrade: " + saveMap.get("Kill2"));
            writer.newLine();
            writer.write("3rd Killing upgrade: " + saveMap.get("Kill3"));
            writer.newLine();
            writer.write("4th Killing upgrade: " + saveMap.get("Kill4"));
            writer.newLine();
            writer.write("5th Killing upgrade: " + saveMap.get("Kill5"));
            writer.newLine();
            writer.write("End of Game: " + saveMap.get("EndGame"));
            writer.newLine();
            writer.write("If the value is 0, it means that the upgrade has not been performed.");
            writer.close();
        }catch ( IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
