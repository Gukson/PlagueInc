import java.util.ArrayList;
import java.util.Random;

public class Ship implements Trasport{
    @Override
    public void infected(Country c) {
        ArrayList<String> possibleSeaCruise = new ArrayList<>();
        for(String s : World.flightsMap.keySet()) if(s.equals(c.getName()) && !World.coutriesMap.get(s).getInfectedStatus()) possibleSeaCruise.add(World.flightsMap.get(s));
        Random random = new Random();
        int randomNumber = random.nextInt(possibleSeaCruise.size() - 1 + 1) + 1;
        Country newInfected = World.coutriesMap.get(possibleSeaCruise.get(randomNumber));
        newInfected.newInfectedConfiguration();
    }
}
