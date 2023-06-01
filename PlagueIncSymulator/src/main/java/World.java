import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class World {
    public static int day;
    public static int population;
    public static int infectedPopulation;
    public static int deadPopulation;


    public static Virus wirus;

    private static ArrayList<Country> infectedCountries;
    private static HashMap<String,Country> coutries;

    public static void setPopulation(int population){
        World.population = population;
    }
    public static int getPopulation(){
        return population;
    }

    //Zaraża pierwszy wybrany kraj
    public static void StartGame(String startingCountry, String virusName) throws InterruptedException {
        infectedCountries = new ArrayList<Country>();
        coutries = new HashMap<String,Country>();
        for(Country c : Configurator.countries) coutries.put(c.getName(),c);

        wirus = new Virus(virusName);

        Country firstInfected = coutries.get(startingCountry);
        firstInfected.setInfectedStatus();
        firstInfected.setInfectedPopulation(1);
        infectedCountries.add(firstInfected);
        Game();
    }

    //Główna funckja odpowiedzialna za grę
    private static void Game() throws InterruptedException {
        day = 1;
        infectedPopulation = 1;
        deadPopulation = 0;
        while(true){
            for(Country c : infectedCountries){
                infectionProcess(c);
                c.printInformations();
            }
            day++;
            System.out.println("Day: " + day);
            Thread.sleep(1000);
        }
    }

    //Funkcja odpowiedzialna za proces zrażania w danym kraju
    private static void infectionProcess(Country c){
        int newInfectedPopulation = (int) (Math.ceil(c.getInfectedPopulation() * wirus.cheanseForInfection)); //wylicza ilość nowych zarażonych
        c.addInfectedPopulation(newInfectedPopulation); //dodaje nowych zarażonych
        c.infectYourNeighbor();

    }

}
