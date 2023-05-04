import java.util.ArrayList;

public class Country {
    public boolean infected = false;
    private String Country_name;
    private int population;
    private float temperature;
    private String  climate;
    private ArrayList<Country> neightboors;


    public Country(String name, int population, double avgTemp, String climate) {
        this.Country_name = name;
        this.population = population;
        this.temperature = (float) avgTemp;
        this.climate = climate;
    }
    public int getPopulation(){
        return population;
    }
    public String getName(){
        return Country_name;
    }
}
