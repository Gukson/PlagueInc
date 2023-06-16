import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Klasa reprezentujaca swiat gry.
 */
public class World {
    public static int day;
    public static int population;
    public static int infectedPopulation;
    public static int deadPopulation;
    public static Virus virus;
    public static ArrayList<Country> infectedCountries;
    public static HashMap<String,Country> coutriesMap;
    public static ArrayList<String> flightsArrayList;
    public static HashMap<String, String> seaCruiseMap;
    public static HashSet<String> CountriesWithFlights;

    /**
     * Ustawia populacje swiata.
     *
     * @param population Populacja swiata.
     */
    public static void setPopulation(int population){
        World.population = population;
    }

    /**
     * Zwraca populacje swiata.
     *
     * @return Populacja swiata.
     */
    public static int getPopulation(){
        return population;
    }

    /**
     * Rozpoczyna nowa gre
     *
     * @param startingCountry Nazwa kraju, ktory jako pierwwszy zostanie zarazony
     * @param virusName Nazwa wirusa.
     */
    //Zaraża pierwszy wybrany kraj
    public static void StartGame(String startingCountry, String virusName) throws InterruptedException {
        infectedCountries = new ArrayList<Country>();

        virus = new Virus(virusName);

        Country firstInfected = coutriesMap.get(startingCountry);
        firstInfected.newInfectedConfiguration();
        Game();
    }
    /**
     * Głowna funkcja odpowiedzialna za logike gry.
     *
     */
    private static void Game() throws InterruptedException {
        day = 1;
        infectedPopulation = 1;
        deadPopulation = 0;
        while(true){
            for(int x = 0; x< infectedCountries.size(); x++){
                Country c = infectedCountries.get(x);
                infectionProcess(c);
                c.printInformations();
            }
            if(day%30 == 0)virus.addPoint();
            day++;
            System.out.println("Day: " + day);
            Thread.sleep(10);
        }
    }
    /**
     * Funkcja odpowiedzialna za zarazanie .
     *
     * @param c Kraj, w w ktorym wystepuje zarazanie.
     */
    //Funkcja odpowiedzialna za proces zrażania w danym kraju
    private static void infectionProcess(Country c){
        int newInfectedPopulation = (int) (Math.ceil(c.getInfectedPopulation() * virus.cheanseForInfection)); //wylicza ilość nowych zarażonych
        c.addInfectedPopulation(newInfectedPopulation); //dodaje nowych zarażonych
        c.infectYourNeighbor();

        if(virus.getAirplaneStatus() && c.getFlightsAmout()!=0){
            c.infectByPlane();
        }
        if(virus.getAirplaneStatus() && c.getShipCruisesAmount()!=0){
            c.infectByShip();
        }

    }

}
