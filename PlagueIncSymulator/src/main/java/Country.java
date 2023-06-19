import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
/**
 * Klasa Configurator służy do przechowywania informacji o kraju.
 */
public class    Country {
    private boolean infected = false;
    private final String CountryName;
    private final float temperature;
    private String  climate;
    private final String[] neighbours;
    private ArrayList<String> availableFlights;
    private ArrayList<String> availableShipCruise;
    private final long population;
    private long healthyPopulation;
    private long infectedPopulation;
    private long deadPopulation;
    private long[] infectedLast14Days;
    /**
     * Konstruktor klasy Country.
     *
     * @param name       Nazwa kraju
     * @param population Populacja kraju
     * @param avgTemp    Srednia temperatura kraju
     * @param climate    Klimat
     * @param neighbour  Tablica sąsiadów kraju
     */
    public Country(String name, long population, double avgTemp, String climate, String[] neighbour) {
        this.CountryName = name;
        this.population = population;
        this.temperature = (float) avgTemp;
        this.climate = climate;
        this.neighbours = neighbour;
        this.healthyPopulation = population;
        availableFlights = new ArrayList<String>();
        availableShipCruise = new ArrayList<String>();
        infectedLast14Days = new long[14];
    }
    /**
     * Zwraca populacje kraju.
     * @return Populacja kraju.
     */
    public long getPopulation(){
        return population;
    }
    /**
     * Zwraca liczbe zdrowych osob w kraju
     * @return Liczba zdrowych osob
     */

    public void setHealthyPopulation(long a){healthyPopulation = a;}
    public long getHealthyPopulation(){return healthyPopulation;}
    /**
     * Zwraca liczbe chorych osob w kraju
     * @return Liczba chorych osob
     */
    public long getInfectedPopulation(){return infectedPopulation;}
    /**
     * Zwraca liczbe martwych osob w kraju
     * @return Liczba martwych osob
     */
    public long getDeadPopulation(){return deadPopulation;}
    /**
     * Zwraca nazwe kraju
     * @return Nazwa kraju.
     */
    public String getName(){
        return CountryName;
    }
    /**
     * Zwraca tablice sasiadow kraju.
     * @return Tablica sasiadow
     */
    public String[] getNeighbours(){
        return neighbours;
    }

    public ArrayList<String> getavailableFlights(){return availableFlights;}

    public ArrayList<String> getAvailableShipCruise(){return availableShipCruise;}

    /**
     * Ustawia liczbe zarazonych osob
     * @param i liczba zarazonych osob
     */
    public void setInfectedPopulation(long i){
        infectedPopulation = i;
        if(infectedPopulation > population) infectedPopulation = population;
        healthyPopulation = population - infectedPopulation;

    }

    /**
     * Ustawia status kraju na zarazony
     */
    public void setStatusInfected(){infected=true;}
    /**
     * Zwraca staatus zarazenia kraju
     * @return Status zarazenia kraju
     */
    public boolean getInfectedStatus(){return infected;}
    public int getFlightsAmout(){return availableFlights.size();}
    public void addFlight(String flight){availableFlights.add(flight);}
    public int getShipCruisesAmount(){return availableShipCruise.size();}
    public void addShipCruise(String flight){availableShipCruise.add(flight);}

    /**
     * Funkcja odpowiedzialna za zabijanie zarazonych ludzi.
     *
     * @param people Ludzie, ktorych czesc zostanie zabita.
     * @return liczba zgonow
     */
    public long killInfectedPeople(long people){
        long deaths = (long) (Math.ceil(people * World.virus.cheanseForDeath)); // to * 0.1 mozna zamienic na chance4Death jak zostanie dodane do gui
        if (deadPopulation + deaths < population){
            deadPopulation += deaths;
            infectedPopulation -= deaths;
            World.deadPopulation += deaths;
        }
        else{
            long acudeath = population - deadPopulation;
            infectedPopulation -=acudeath;
            deadPopulation = population;
            World.deadPopulation += acudeath;
        }
        return deaths;
    }
    /**
     * Funkcja odpowiadajaca za przesylanie ilosci osob zarazonych do funkcji killInfectedPeople(people)
     *
     */
    public void killingAfterNotHealthyPopulation(){
        killInfectedPeople((infectedPopulation));
    }


    /**
     * Wyswietla informacje o kraju.
     */
    public void printInformations(){
        System.out.println("Nazwa Kraju: "+ getName());
        System.out.println("Populacja: "+ population);
        System.out.println("Zarazeni: "+ infectedPopulation);
        System.out.println("Death: " + deadPopulation);
        System.out.println();
    }
}
