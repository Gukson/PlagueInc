import java.util.ArrayList;
import java.util.Random;
/**
 * Klasa Configurator służy do przechowywania informacji o kraju.
 */
public class Country {
    private boolean infected = false;
    private final String CountryName;
    private float temperature;
    private String  climate;
    private final String[] neighbours;

    private final int population;
    private int healthyPopulation;
    private int infectedPopulation;
    private int deadPopulation;

    /**
     * Konstruktor klasy Country.
     *
     * @param name       Nazwa kraju
     * @param population Populacja kraju
     * @param avgTemp    Srednia temperatura kraju
     * @param climate    Klimat
     * @param neighbour  Tablica sąsiadów kraju
     */
    public Country(String name, int population, double avgTemp, String climate, String[] neighbour) {
        this.CountryName = name;
        this.population = population;
        this.temperature = (float) avgTemp;
        this.climate = climate;
        this.neighbours = neighbour;
        this.healthyPopulation = population;
    }
    /**
     * Zwraca populacje kraju.
     * @return Populacja kraju.
     */
    public int getPopulation(){
        return population;
    }
    /**
     * Zwraca liczbe zdrowych osob w kraju
     * @return Liczba zdrowych osob
     */
    public int getHealthyPopulation(){return healthyPopulation;}

    /**
     * Zwraca liczbe chorych osob w kraju
     * @return Liczba chorych osob
     */
    public int getInfectedPopulation(){return infectedPopulation;}
    /**
     * Zwraca liczbe martwych osob w kraju
     * @return Liczba martwych osob
     */
    public int getDeadPopulation(){return deadPopulation;}

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

    /**
     * Dodaje liczbe zarazonych osob
     * @param i liczba zarazonych osob
     */
    public void addInfectedPopulation(int i){
        infectedPopulation += i;
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

    /**
     * Zaraza losowego sasiada

     */
    public void infectYourNeighbor(){
        Random random = new Random();
        int randomNumber = random.nextInt(population - 1 + 1) + 1;
        System.out.println();
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
     * Generuje ArrayListe krajow , ktorzy nie sa zarazeni
     *
     * @return  Lista niezarazonych sasiadow
     */
    //Funckja generuje Arraylistę nie zarażonych sąsiadów
    private ArrayList<Country> notInfectedNeighbours(){
        ArrayList<Country> notInfected = new ArrayList<Country>();
        System.out.println(neighbours);
        for(String c: neighbours){
            Country tempCountry = World.coutriesMap.get(c);
            System.out.println(c);
            if(!tempCountry.getInfectedStatus()) notInfected.add(tempCountry);
        }
        return notInfected;
    }
    /**
     * Wyswietla informacje o kraju.
     */
    public void printInformations(){
        System.out.println("Nazwa Kraju: "+ getName());
        System.out.println("Populacja: "+ population);
        System.out.println("Zarazeni: "+ infectedPopulation);
        System.out.println();
    }

    public void newInfectedConfiguration(){
        setStatusInfected();
        setInfectedPopulation(1);
        World.infectedCountries.add(this);
        World.virus.addPoint();
    }
}
