import java.util.ArrayList;
import java.util.HashMap;

public class World {
    public int day;
    public static int population;
    public int infectedPopulation;
    public int deadPopulation;


    public static Virus wirus;

    private ArrayList<Country> infectedCountries;
    private HashMap<String,Country> coutries;

    public static void setPopulation(int population){
        World.population = population;
    }
    public static int getPopulation(){
        return population;
    }

    //Zaraża pierwszy wybrany kraj
    public void StartGame(String startingCountry, String virusName){
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
    public void Game(){
        day = 1;
        infectedPopulation = 1;
        deadPopulation = 0;
        while(true){
            for(Country c : infectedCountries){
                infectionProcess(c);
            }
            day++;
        }
    }

    private void infectionProcess(Country c){
        int newInfectedPopulation = (int) (Math.ceil(c.getHealthyPopulation() * wirus.cheanseForInfection)); //wylicza ilość nowych zarażonych
        c.addInfectedPopulation(newInfectedPopulation); //dodaje nowych zarażonych
        c.infectYourNeighbor();


    }

}
