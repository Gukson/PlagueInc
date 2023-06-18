import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Klasa reprezentujaca swiat gry.
 */
public class World {
    public static int day;
    public static long population;
    public static long infectedPopulation;
    public static long healthyPopulation;
    public static long deadPopulation;
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
    public static void setPopulation(long population){
        World.population = population;

    }

    /**
     * Zwraca populacje swiata.
     *
     * @return Populacja swiata.
     */
    public static long getPopulation(){
        return population;
    }

    /**
     * Funkcja odpowiedzialna za zarazanie .
     *
     * @param c Kraj, w w ktorym wystepuje zarazanie.
     */
    //Funkcja odpowiedzialna za proces zrażania w danym kraju
    static void infectionProcess(Country c){
        long newInfectedPopulation = c.addInfectedPopulation(); //wylicza ilość nowych zarażonych i ich dodaje

        c.infectYourNeighbor();

        if(virus.getAirplaneStatus() && c.getFlightsAmout()!=0){
            c.infectByPlane();
        }
        if(virus.getAirplaneStatus() && c.getShipCruisesAmount()!=0){
            c.infectByShip();
        }
    }
}
