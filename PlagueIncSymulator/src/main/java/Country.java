import java.util.ArrayList;
import java.util.Random;

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


    public Country(String name, int population, double avgTemp, String climate, String[] neighbour) {
        this.CountryName = name;
        this.population = population;
        this.temperature = (float) avgTemp;
        this.climate = climate;
        this.neighbours = neighbour;
        this.healthyPopulation = population;
    }
    public int getPopulation(){
        return population;
    }

    public int getHealthyPopulation(){return healthyPopulation;}
    public int getInfectedPopulation(){return infectedPopulation;}
    public int getDeadPopulation(){return deadPopulation;}

    public String getName(){
        return CountryName;
    }
    public String[] getNeighbours(){
        return neighbours;
    }

    public void setInfectedPopulation(int i){
        infectedPopulation = i;
        if(infectedPopulation > population) infectedPopulation = population;
        healthyPopulation = population - infectedPopulation;
    }
    public void addInfectedPopulation(int i){
        infectedPopulation += i;
        if(infectedPopulation > population) infectedPopulation = population;
        healthyPopulation = population - infectedPopulation;
    }

    public void setStatusInfected(){infected=true;}
    public boolean getInfectedStatus(){return infected;}

    public void infectYourNeighbor(){
        Random random = new Random();
        int randomNumber = random.nextInt(population - 1 + 1) + 1;
        System.out.println();
        if(randomNumber <= infectedPopulation){
            ArrayList<Country> notInfected = notInfectedNeighbours();
            randomNumber = random.nextInt( notInfected.size() + 1);
            Country newInfected = notInfected.get(randomNumber);
            newInfected.setStatusInfected();
            newInfected.setInfectedPopulation(1);
            World.infectedCountries.add(newInfected);
        }
    }
    
    //Funckja generuje Arraylistę nie zarażonych sąsiadów
    private ArrayList<Country> notInfectedNeighbours(){
        ArrayList<Country> notInfected = new ArrayList<Country>();
        for(String c: neighbours){
            Country tempCountry = World.coutriesMap.get(c);
            if(!tempCountry.getInfectedStatus()) notInfected.add(tempCountry);
        }
        return notInfected;
    }

    public void printInformations(){
        System.out.println("Nazwa Kraju: "+ getName());
        System.out.println("Populacja: "+ population);
        System.out.println("Zarazeni: "+ infectedPopulation);
        System.out.println();
    }


}
