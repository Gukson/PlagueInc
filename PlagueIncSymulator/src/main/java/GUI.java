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
    JTextArea tHealtyCoutries, tInfectedCountries, tDeadCoutries;
    JButton bNewGame,bLoad,bExit, bPlay;
    JScrollPane spHealthy, spInfected, spdead;
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
        lHealthyPopulation.setBounds(20, 40, 400,100);
        lHealthyPopulation.setFont(new Font("SansSerif",Font.PLAIN,24));
        add(lHealthyPopulation);

        lInfectedPopulation = new JLabel("");
        lInfectedPopulation.setBounds(20, 70, 400,100);
        lInfectedPopulation.setFont(new Font("SansSerif",Font.PLAIN,24));
        add(lInfectedPopulation);

        lDeadPopulation = new JLabel("");
        lDeadPopulation.setBounds(20, 100, 400,100);
        lDeadPopulation.setFont(new Font("SansSerif",Font.PLAIN,24));
        add(lDeadPopulation);

        tHealtyCoutries = new JTextArea("");
        tHealtyCoutries.setEditable(false);
        tHealtyCoutries.setLineWrap(true);
        tHealtyCoutries.setWrapStyleWord(true);
        tHealtyCoutries.setFont(new Font("SansSerif",Font.PLAIN,15));
        spHealthy = new JScrollPane(tHealtyCoutries,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spHealthy.setBounds(20,300,300,300);
        add(spHealthy);

        tInfectedCountries = new JTextArea("");
        tInfectedCountries.setEditable(false);
        tInfectedCountries.setLineWrap(true);
        tInfectedCountries.setWrapStyleWord(true);
        tInfectedCountries.setFont(new Font("SansSerif",Font.PLAIN,15));
        spInfected = new JScrollPane(tInfectedCountries,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spInfected.setBounds(350,300,300,300);
        add(spInfected);

        tDeadCoutries = new JTextArea("");
        tDeadCoutries.setEditable(false);
        tDeadCoutries.setLineWrap(true);
        tDeadCoutries.setWrapStyleWord(true);
        tDeadCoutries.setFont(new Font("SansSerif",Font.PLAIN,15));
        spdead = new JScrollPane(tDeadCoutries,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spdead.setBounds(700,300,300,300);
        add(spdead);



        World.day = 1;
        World.infectedPopulation = 0;
        World.healthyPopulation = World.population;
        World.infectedCountries = new ArrayList<>();
        World.virus = new Virus("Wirus");
        Country firstInfected = World.coutriesMap.get("Niemcy");
        Infection.newInfectedConfiguration(firstInfected);
        String healthyCoutries, infectedCoutries, deadCoutries;
        while( World.deadPopulation != World.population && World.infectedCountries.size() > 0){
            healthyCoutries = "";
            infectedCoutries = "";
            deadCoutries = "";
            for(Country c: Configurator.countries){
                healthyCoutries += c.getName() + ": " + c.getHealthyPopulation() + " (" + Math.round((float) c.getHealthyPopulation() / c.getPopulation() * 100) + "%)\n";
            }
            tHealtyCoutries.setText(healthyCoutries);
            for(Country c: World.infectedCountries){
                infectedCoutries += c.getName() + ": " + c.getInfectedPopulation() + " (" + Math.round((float) c.getInfectedPopulation() / c.getPopulation() * 100) + "%)\n";
                deadCoutries += c.getName() + ": " + c.getDeadPopulation() + " (" + Math.round((float) c.getDeadPopulation() / c.getPopulation() * 100) + "%)\n";
            }
            tInfectedCountries.setText(infectedCoutries);
            tDeadCoutries.setText(deadCoutries);
            for(int x = 0; x< World.infectedCountries.size(); x++){
                Country c = World.infectedCountries.get(x);
                World.infectionProcess(c);
            }
            List<Country> infectedCountries;
            infectedCountries = new ArrayList<>(World.infectedCountries);
            Random random = new Random();
            int randomIndex = random.nextInt(infectedCountries.size());
            Country randomCountry = infectedCountries.get(randomIndex);
            if(infectedCountries.size() > 0 && randomCountry.getHealthyPopulation() == 0) {
                randomCountry.killingAfterNotHealthyPopulation();
                randomCountry.printInformations();
            }
            if(randomCountry.getHealthyPopulation() ==  0 ){
                infectedCountries.remove(randomIndex);
            }
            System.out.println(World.infectedCountries.size());
            System.out.println(World.population);
            long suma = 0;
            for(Country x: World.infectedCountries){
                suma += x.getInfectedPopulation();
            }
            World.infectedPopulation = suma;
            lHealthyPopulation.setText("Zdrowi: " + Long.toString(World.healthyPopulation - World.infectedPopulation) + " (" + Math.round((float) World.healthyPopulation / World.population * 100) + "%)");
            lDeadPopulation.setText("Zmarli: " + Long.toString(World.deadPopulation) + " (" + Math.round((float) World.deadPopulation / World.population * 100) + "%)");
            lInfectedPopulation.setText("Zarażeni: " + Long.toString(World.infectedPopulation) + " (" + Math.round((float) World.infectedPopulation / World.population * 100) + "%)");
            repaint();
            if(World.day%30 == 0)World.virus.addPoint();
            World.day++;
            Thread.sleep(0);
        }
    }
}


