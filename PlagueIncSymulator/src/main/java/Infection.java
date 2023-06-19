import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Infection {
    public static void newInfectedConfiguration(Country c){
        c.setStatusInfected();
        c.setInfectedPopulation(1);
        World.infectedCountries.add(c);
        World.virus.addPoint();
    }
    public static void infectByPlane(Country c){
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber <= World.virus.cheanseForInfectedFlight){
            randomNumber = random.nextInt(c.getavailableFlights().size()) + 1;
            Country newInfected = World.coutriesMap.get(c.getavailableFlights().get(randomNumber-1));
            if(!newInfected.getInfectedStatus()){
//                System.out.println("SAMOLOT ZAINFEKOWAL " + newInfected.getName());
                newInfectedConfiguration(newInfected);
            }
        }
    }
    public static void addInfectedPopulation(Country c){
        long healthyPopulation = c.getHealthyPopulation();
        long infectedPopulation = c.getInfectedPopulation();
        long population = c.getPopulation();
        if(healthyPopulation == 0) return ;
        long a = (long) (Math.ceil(infectedPopulation * World.virus.cheanseForInfection)); //wylicza ilość nowych zarażonych
        if(infectedPopulation + a > healthyPopulation){
            a = healthyPopulation - infectedPopulation;
            c.setInfectedPopulation(population);
            c.setHealthyPopulation(0);
        }
        c.setInfectedPopulation(infectedPopulation + a);
        c.setHealthyPopulation(healthyPopulation - a);
    }

    /**
     * Zaraza losowego sasiada
     */
    public static void infectYourNeighbor(Country c){
        String[] neighbours = c.getNeighbours();
        long population = c.getPopulation();
        long infectedPopulation = c.getInfectedPopulation();
        if(neighbours.length == 1 && Objects.equals(neighbours[0], "None")) return;
        Random random = new Random();
        int randomNumber = random.nextInt((int)population - 1 + 1) + 1;
        if(randomNumber <= infectedPopulation){
            ArrayList<Country> notInfected = notInfectedNeighbours(c);
            if(notInfected.size() != 0){
                randomNumber = random.nextInt( notInfected.size());
                Country newInfected = notInfected.get(randomNumber);
                newInfectedConfiguration(newInfected);
            }
        }
    }

    /**
     * Generuje ArrayListe krajow , ktorzy nie sa zarazeni
     *
     * @return  Lista niezarazonych sasiadow
     */
    private static ArrayList<Country> notInfectedNeighbours(Country c){
        String[] neighbours = c.getNeighbours();
        ArrayList<Country> notInfected = new ArrayList<Country>();
        for(String s: neighbours){
            Country tempCountry = World.coutriesMap.get(s);
            if(!tempCountry.getInfectedStatus()) notInfected.add(tempCountry);
        }
        return notInfected;
    }

    public static void infectByShip(Country c){
        ArrayList<String> availableShipCruise = c.getAvailableShipCruise();
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber <= World.virus.cheanseForInfectedShipCruise){
            randomNumber = random.nextInt(availableShipCruise.size()) + 1;
            Country newInfected = World.coutriesMap.get(availableShipCruise.get(randomNumber-1));
            if(!newInfected.getInfectedStatus()){
//                System.out.println("STATEK ZAINFEKOWAL " + newInfected.getName());
                newInfectedConfiguration(newInfected);
            }
        }
    }

}
