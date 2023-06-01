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

    public void setInfectedStatus(){infected=true;}
    public boolean getInfectedStatus(){return infected;}

    public void infectYourNeighbor(){
        Random random = new Random();
        int randomNumber = random.nextInt(population - 1 + 1) + 1;
        System.out.println();
        if(randomNumber <= infectedPopulation){
            randomNumber = random.nextInt( neighbours.length + 1);

            //zaraź któregoś z niezarażonych sąsiadów
        }

    }

    public void printInformations(){
        System.out.println("Nazwa Kraju: "+ getName());
        System.out.println("Populacja: "+ population);
        System.out.println("Zarazeni: "+ infectedPopulation);
        System.out.println();
    }


}
