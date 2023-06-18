import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Klasa GUI reprezentujaca interfejs graficzny symulacji.
 */
public class GUI extends JFrame{
    private final int width = 1200;
    private final int height = 675;
    JLabel lHealthyPopulation, lInfectedPopulation, lDeadPopulation;
    JButton bNewGame,bLoad,bExit, bPlay;
    public static JTextField tName;
    JPanel pMainPage;
    static JPanel pNewGame;
    static JPanel pGameGUI;
    /**
     * Metoda glowna aplikacji
     *
     * @param args argumenty
     */
    public static void main(String[] args) throws InterruptedException {
        Configurator.startConfigurator();
        GUI appMenu = new GUI();
        appMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appMenu.setVisible(true);
    }
    /**
     * Konstruktor klasy GUI
     *
     */
    public GUI() throws InterruptedException {
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        lHealthyPopulation = new JLabel("");
        lHealthyPopulation.setBounds(20, 70, 150,50);
        lHealthyPopulation.setFont(new Font("SansSerif",Font.PLAIN,24));
        lInfectedPopulation = new JLabel("");
        lInfectedPopulation.setBounds(20, 90, 150,100);
        lInfectedPopulation.setFont(new Font("SansSerif",Font.PLAIN,24));
        add(lHealthyPopulation);
        add(lInfectedPopulation);
        World.day = 1;
        World.infectedPopulation = 0;
        World.healthyPopulation = World.population;
        World.infectedCountries = new ArrayList<>();
        World.virus = new Virus("Wirus");
        Country firstInfected = World.coutriesMap.get("Niemcy");
        firstInfected.newInfectedConfiguration();
        while( World.deadPopulation < World.population){
            for(int x = 0; x< World.infectedCountries.size(); x++){
                Country c = World.infectedCountries.get(x);
                World.infectionProcess(c);
            }
            long suma = 0;
            for(Country x: World.infectedCountries){
                suma += x.getInfectedPopulation();
            }
            World.infectedPopulation = suma;
            long healtyPopulation = World.population - World.infectedPopulation;
            World.healthyPopulation = World.getPopulation() - suma;
            lHealthyPopulation.setText(Long.toString(   healtyPopulation));
            lInfectedPopulation.setText(Long.toString(World.infectedPopulation));
            if(World.healthyPopulation == 0) {
                List<Country> infectedCountries;
                infectedCountries = new ArrayList<>(World.infectedCountries);
                Random random = new Random();
                    while (infectedCountries.size() > 0){
                        int randomIndex = random.nextInt(infectedCountries.size());
                        Country randomCountry = infectedCountries.get(randomIndex);
                        randomCountry.killingAfterNotHealthyPopulation();
                        if(randomCountry.getPopulation() <= randomCountry.getDeadPopulation()){
                            infectedCountries.remove(randomIndex);
                        }
                    }
            }
            System.out.println(World.deadPopulation);
            repaint();
            if(World.day%30 == 0)World.virus.addPoint();
            World.day++;
            Thread.sleep(1);
        }
    }
}


