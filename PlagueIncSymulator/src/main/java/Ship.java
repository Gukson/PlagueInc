import java.util.ArrayList;
import java.util.Random;

public class Ship implements Trasport {
    @Override
    public void infected(Country c) {
        ArrayList<String> possibleSeaCruise = new ArrayList<>();
        for (String s : World.flightsMap.keySet())
            if (s.equals(c.getName()) && !World.coutriesMap.get(World.seaCruiseMap.get(s)).getInfectedStatus())
                possibleSeaCruise.add(World.flightsMap.get(s));
        if (possibleSeaCruise.isEmpty()) return;
        System.out.println("PŁYNIE STATECZEK!");
        Random random = new Random();
        int randomNumber = random.nextInt(possibleSeaCruise.size() - 1 + 1) + 1;
        Country newInfected = World.coutriesMap.get(possibleSeaCruise.get(randomNumber-1));
        System.out.println("Statek ZAINFEKOWAL " + newInfected.getName());
        newInfected.newInfectedConfiguration();
    }
}