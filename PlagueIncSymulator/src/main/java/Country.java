public class Country {
    public boolean infected = false;
    private String Country_name;
    private int population;
    private float temperature;
    private String  climate;
    private String[] neighbours;


    public Country(String name, int population, double avgTemp, String climate, String[] neighbour) {
        this.Country_name = name;
        this.population = population;
        this.temperature = (float) avgTemp;
        this.climate = climate;
        this.neighbours = neighbour;
    }
    public int getPopulation(){
        return population;
    }
    public String getName(){
        return Country_name;
    }
    public String[] getNeighbours(){
        return neighbours;
    }
}
