import javax.lang.model.element.Name;
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
    /**
     * Ustawia liczbe zarazonych osob
     * @param i liczba zarazonych osob
     */
    public void setInfectedPopulation(int i){
        infectedPopulation = i;
        if(infectedPopulation > population) infectedPopulation = population;
        healthyPopulation = population - infectedPopulation;

    }
    public long addInfectedPopulation(){
        if(healthyPopulation == 0) return 0;
        long a = (long) (Math.ceil(getInfectedPopulation() * World.virus.cheanseForInfection)); //wylicza ilość nowych zarażonych
        if(infectedPopulation + a > healthyPopulation){
            a = healthyPopulation - infectedPopulation;
            infectedPopulation = population;
            healthyPopulation = 0;
            return a;
        }
        infectedPopulation += a;
        healthyPopulation -= a;


        return a;
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
    /**
     * Zwraca liczbe lotow
     * @return Liczba lotowe
     */
    public int getFlightsAmout(){return availableFlights.size();}
    /**
     * Dodaje lot
     */

    public void addFlight(String flight){availableFlights.add(flight);}
    /**
     * Dodaje liczbe rejsow
     * @return rejs
     */
    public int getShipCruisesAmount(){return availableShipCruise.size();}
    /**
     * Dodaje rejs
     */
    public void addShipCruise(String flight){availableShipCruise.add(flight);}
    /**
     * Zaraza losowego sasiada
     */
    public void infectYourNeighbor(){
        if(neighbours.length == 1 && Objects.equals(neighbours[0], "None")) return;
        Random random = new Random();
        int randomNumber = random.nextInt((int)population - 1 + 1) + 1;
        if(randomNumber <= infectedPopulation){
            ArrayList<Country> notInfected = notInfectedNeighbours();
            if(notInfected.size() != 0){
                randomNumber = random.nextInt( notInfected.size());
                Country newInfected = notInfected.get(randomNumber);
                newInfected.newInfectedConfiguration();
            }
        }
    }
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
            deadPopulation = population;
            healthyPopulation = 0;
            infectedPopulation = 0;
            World.deadPopulation += acudeath;
            return 0;
        }
        return deaths;
    }
    /**
     * Funkcja odpowiadajaca za uzupelnianie tablicy osobami zarazonymi w ciagu 14 dni.
     * Funkcja odpowiada za przesylanie ilosci zabitych osob do funkcji z zabijaniem
     *
     */
    public void killingHealthyPopulation(){
        long sum = 0;
        int index = World.day % 14; // AKTUALNY INDEX
        int nextIndex = (index + 1 )% 14; // NASTEPNY INDEX
        for (int i =0 ;i<14;i++){
            sum += infectedLast14Days[i];
        }
        if(World.day > 14){
            long dead = killInfectedPeople((infectedLast14Days[index]));
            infectedLast14Days[index] -= dead;
            long moveInfected = infectedLast14Days[index];
            infectedLast14Days[nextIndex] += moveInfected;
            infectedLast14Days[index] = infectedPopulation - sum;
        }

    }


    /**
     * Generuje ArrayListe krajow , ktorzy nie sa zarazeni
     *
     * @return  Lista niezarazonych sasiadow
     */
    private ArrayList<Country> notInfectedNeighbours(){
        ArrayList<Country> notInfected = new ArrayList<Country>();
        for(String c: neighbours){
            Country tempCountry = World.coutriesMap.get(c);
            if(!tempCountry.getInfectedStatus()) notInfected.add(tempCountry);
        }
        return notInfected;
    }
    /**
     * Funkcja odpowiadajaca za dodawanie zainfekowanego kraju do tablicy zainfekowanych krajow.
     */

    public void newInfectedConfiguration(){
        setStatusInfected();
        setInfectedPopulation(1);
        World.infectedCountries.add(this);
        World.virus.addPoint();
    }
    /**
     * Funkcja odpowiadajaca za infekowanie samolotem
     */
    public void infectByPlane(){
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber <= World.virus.cheanseForInfectedFlight){
            randomNumber = random.nextInt(availableFlights.size()) + 1;
            Country newInfected = World.coutriesMap.get(availableFlights.get(randomNumber-1));
            if(!newInfected.getInfectedStatus()){
                newInfected.newInfectedConfiguration();
            }
        }
    }
    /**
     * Funkcja odpowiadajaca za infekowanie statkiem
     */
    public void infectByShip(){
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber <= World.virus.cheanseForInfectedShipCruise){
            randomNumber = random.nextInt(availableShipCruise.size()) + 1;
            Country newInfected = World.coutriesMap.get(availableShipCruise.get(randomNumber-1));
            if(!newInfected.getInfectedStatus()){
                newInfected.newInfectedConfiguration();
            }
        }
    }

}
