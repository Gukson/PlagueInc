public class World {
    public int day;
    public static int population;
    public int infected_population;
    public int dead_populati9on;

    public static Virus wirus;

    public static void setPopulation(int population){
        World.population = population;
    }
    public static int getPopulation(){
        return population;
    }

    public void Game(){
        day = 1;
        infected_population = 1;
        dead_populati9on = 0;
        while(true){


            day++;
        }
    }

}
