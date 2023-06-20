import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa GUI reprezentujaca interfejs graficzny symulacji.
 */
public class GUI extends JFrame implements ActionListener {
    private final int width = 1200;
    private final int height = 675;
    JLabel lHealthyPopulation, lInfectedPopulation, lDeadPopulation, lDay, lHealthy, lInfected, lDead, lPoints;
    JTextArea tHealtyCoutries, tInfectedCountries, tDeadCoutries;
    int timelvl = 1;
    ImageIcon lvl1,lvl2,lvl3;
    JButton bTime;
    JScrollPane spHealthy, spInfected, spdead;

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
        lvl1 = new ImageIcon("PlagueIncSymulator/Data/Images/9057079_play_button_icon.png");
        lvl2 = new ImageIcon("PlagueIncSymulator/Data/Images/7754971_media player_button_speed_fast_arrow_icon.png");
        lvl3 = new ImageIcon("PlagueIncSymulator/Data/Images/9034563_track_next_icon.png");

        bTime = new JButton(lvl1);
        bTime.setBounds(1100,20,48,48);
        bTime.addActionListener(this);
        add(bTime);

        lPoints = new JLabel("Punkty");
        lPoints.setBounds(400, 20, 300,30);
        lPoints.setFont(new Font("SansSerif",Font.PLAIN,24));
        lPoints.setHorizontalAlignment(SwingConstants.CENTER);
        add(lPoints);

        lHealthy = new JLabel("Zdrowi");
        lHealthy.setBounds(20, 250, 300,50);
        lHealthy.setFont(new Font("SansSerif",Font.PLAIN,24));
        lHealthy.setHorizontalAlignment(SwingConstants.CENTER);
        add(lHealthy);

        lInfected = new JLabel("Zarażeni");
        lInfected.setBounds(350, 250, 300,50);
        lInfected.setFont(new Font("SansSerif",Font.PLAIN,24));
        lInfected.setHorizontalAlignment(SwingConstants.CENTER);
        add(lInfected);

        lDead = new JLabel("Zmarli");
        lDead.setBounds(700, 250, 300,50);
        lDead.setFont(new Font("SansSerif",Font.PLAIN,24));
        lDead.setHorizontalAlignment(SwingConstants.CENTER);
        add(lDead);

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

        lDay = new JLabel("");
        lDay.setBounds(20,130,400,100);
        lDay.setFont(new Font("SansSerif",Font.PLAIN,24));
        add(lDay);

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
        firstInfected.newInfectedConfiguration();
        String healthyCoutries, infectedCoutries, deadCoutries;
        while( World.deadPopulation != World.population && World.infectedCountries.size() > 0){
            lDay.setText("Dzień: " + World.day);
            lPoints.setText("Punkty: " + World.virus.getPoints());
            healthyCoutries = "";
            infectedCoutries = "";
            deadCoutries = "";
            for(int x = 0; x< World.infectedCountries.size(); x++){
                Country c = World.infectedCountries.get(x);
                World.infectionProcess(c);
            }

            List<Country> infectedCountries;
            infectedCountries = new ArrayList<>(World.infectedCountries);
            for(Country country: infectedCountries){
                country.killingHealthyPopulation();
            }
            Thread.sleep(World.time);

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
            long suma = 0;
            for(Country x: World.infectedCountries){
                suma += x.getInfectedPopulation();
            }
            World.infectedPopulation = suma;
            lHealthyPopulation.setText("Zdrowi: " + Long.toString(World.healthyPopulation - World.infectedPopulation - World.deadPopulation) + " (" + Math.round((float) (World.healthyPopulation- World.infectedPopulation - World.deadPopulation) / World.population * 100) + "%)");
            lDeadPopulation.setText("Zmarli: " + Long.toString(World.deadPopulation) + " (" + Math.round((float) World.deadPopulation / World.population * 100) + "%)");
            lInfectedPopulation.setText("Zarażeni: " + Long.toString(World.infectedPopulation) + " (" + Math.round((float) World.infectedPopulation / World.population * 100) + "%)");

            repaint();
            if(World.day%30 == 0)World.virus.addPoint();
            World.day++;
            System.out.println("DAY" + World.day
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        if(zrodlo == bTime){
            if(timelvl == 1) {
                bTime.setIcon(lvl2);
                timelvl++;
                World.speedup_time();
            }
            else if (timelvl == 2) {
                bTime.setIcon(lvl3);
                timelvl++;
                World.speedup_time();
            }
            else {
                bTime.setIcon(lvl1);
                timelvl =1;
                World.speedup_time();
            }

        }
    }
}


