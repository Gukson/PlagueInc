import java.util.ArrayList;
import java.util.Random;

public class Airplane implements Trasport{
    @Override
    public void infected(Country c) {
        ArrayList<String> possibleFlights = new ArrayList<>();
        for(String s : World.flightsMap.keySet()) if(s.equals(c.getName()) && !World.coutriesMap.get(s).getInfectedStatus()) possibleFlights.add(World.flightsMap.get(s));
        Random random = new Random();
        int randomNumber = random.nextInt(possibleFlights.size() - 1 + 1) + 1;
        Country newInfected = World.coutriesMap.get(possibleFlights.get(randomNumber));
        newInfected.newInfectedConfiguration();
    }
}
