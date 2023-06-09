import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Klasa Configurator służy do konfiguracji symulatora.
 */
public class Configurator {

    /**
     * Lista prechowujaca kraje.
     */
    public static ArrayList<Country> countries = new ArrayList<>();
    /**
     * Konstruktor Configurator wywoluje metody o lotach, rejsach ,krajach oraz oblicza liczbe ludnosci
     */
    public Configurator(){
        readInfoAboutFlights("PlagueIncSymulator/Data/flights.csv");
        readInfoAboutSeaCruise("PlagueIncSymulator/Data/sea_cruise.csv");
        readInfoAboutCountries("PlagueIncSymulator/Data/countiries_data.csv");
        int population = calculateWorldPopulation();
        World.setPopulation(population);
    }

    /**
     * Odczytuje informacji o krajach z podanego pliku
     *
     * @param filename Sciezka do pliku zawierajaca  informacje o krajach
     */
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
                String[] neighbours = fields[4].split(", ");
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
    /**
     * Oblicza populate swiata
     *
     * @return Populacja swiata
     */
    private int calculateWorldPopulation(){
        int population = 0;
        for (Country country :countries){
            population += country.getPopulation();
        }
        return population;
    }
    /**
     * Odczytuje informacje o lotach z podanego pliku
     *
     * @param filename Sciezka do pliku zawierajaca  informacje o lotach
     */
    private void readInfoAboutFlights(String filename){
        World.flightsMap = new HashMap<String,String >();
        try{
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Kraj")) {
                    continue;
                }
                String[] fields = line.split(";");
                World.flightsMap.put(fields[0],fields[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Odczytuje informacje o rejsach z podanego pliku
     *
     * @param filename Sciezka do pliku zawierajaca  informacje o rejsach
     */
    private void readInfoAboutSeaCruise(String filename){
        World.seaCruiseMap = new HashMap<String,String >();
        try{
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Kraj")) {
                    continue;
                }
                String[] fields = line.split(";");
                World.seaCruiseMap.put(fields[0],fields[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Konfiguracja oraz incijalizacja symulatora
     *
     *
     */
    public static void startConfigurator() {
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
