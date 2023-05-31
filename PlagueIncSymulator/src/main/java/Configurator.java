import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Configurator {
    public static ArrayList<Country> countries = new ArrayList<>();

    public Configurator(){
        readInfoAboutCountries("PlagueIncSymulator/Data/countiries_data.csv");
        int population = calculateWorldPopulation();
        World.setPopulation(population);
    }
    private void readInfoAboutCountries(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("KRAJ")) {
                    continue;
                }

                String[] fields = line.split(";");
                String name = fields[0];
                String[] neighbours = fields[4].split(",");
                String populationStr = fields[1].replaceAll("\\s+", "");
                int population = Integer.parseInt(populationStr);
                String avgTempStr = fields[2].replaceAll("[^\\d.-]+", "");
                double avgTemp = Double.parseDouble(avgTempStr);
                String climate = fields[3];
                countries.add(new Country(name, population, avgTemp, climate,neighbours));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private int calculateWorldPopulation(){
        int population = 0;
        for (Country country :countries){
            population += country.getPopulation();
        }
        return population;
    }

    public static void main(String[] args) {
        Configurator gui = new Configurator();
        int worldPopulation = World.getPopulation();
        System.out.println("WORLD POPULATION " + worldPopulation);
        for (Country country : gui.countries){
            System.out.println(country.getName() + "  POPULATION: " + country.getPopulation() + "  NEIGHBOURS : " + country.getNeighbours());
            String[] Arr = country.getNeighbours();
            System.out.println(Arr[0]);
        }
    }

}
