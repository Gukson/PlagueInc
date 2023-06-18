import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.zip.CheckedOutputStream;

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
        World.deadPopulation = 0;

        World.infectedCountries = new ArrayList<Country>();

        World.virus = new Virus("Wirus");

        Country firstInfected = World.coutriesMap.get("Niemcy");
        firstInfected.newInfectedConfiguration();

        while(World.healthyPopulation > 0){
            for(int x = 0; x< World.infectedCountries.size(); x++){
                Country c = World.infectedCountries.get(x);
                World.infectionProcess(c);
//                c.printInformations();

                //update data

            }
            long suma = 0;
            for(Country x: World.infectedCountries){
                suma += x.getInfectedPopulation();
//                System.out.println(x.getName() + ": " + x.getInfectedPopulation());
            }
            World.infectedPopulation = suma;
            World.healthyPopulation = World.getPopulation() - suma;
//            System.out.println("World Infected Population: " + World.infectedPopulation);

            lHealthyPopulation.setText(Long.toString(World.population - World.infectedPopulation));
            lInfectedPopulation.setText(Long.toString(World.infectedPopulation));
            repaint();

            for(Country x: Configurator.countries) if(x.getInfectedPopulation() == 0) System.out.println(x.getName());

            if(World.day%30 == 0)World.virus.addPoint();
            World.day++;
            System.out.println("Day: " + World.day);
            System.out.println();
            Thread.sleep(0);
        }
    }
}


