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
    JLabel lHealthyPopulation, lInfectedPopulation, lDeadPopulation, lDay, lHealthy, lInfected, lDead, lPoints,
            lPlaneStatus, lShipStatus, lTimeSpeed, lPlanePrize, lShipPrize, lVirusLevel, lVirusUpgradePrize,lDeadLvl,lDeadUpgradePrize;
    JTextArea tHealtyCoutries, tInfectedCountries, tDeadCoutries;
    int timeLvl = 1, killingLvl = 0, infectingLvl = 1, needToUpgradeInfecting = 5, needToUpgradeKilling = 7;
    boolean airPlane = false, ship = false;
    ImageIcon iTimeSpeed1, iTimeSpeed2, iTimeSpeed3, iAirPlane, iShip, iVirus, iDeath;
    JButton bTime, bAirPlane, bShip, bVirus, bDeath;
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


        //IMAGEICONS
        iDeath = new ImageIcon("PlagueIncSymulator/Data/Images/death.png");
        iVirus = new ImageIcon("PlagueIncSymulator/Data/Images/virus.png");
        iTimeSpeed1 = new ImageIcon("PlagueIncSymulator/Data/Images/PlayButton.png");
        iTimeSpeed2 = new ImageIcon("PlagueIncSymulator/Data/Images/FasterButton.png");
        iTimeSpeed3 = new ImageIcon("PlagueIncSymulator/Data/Images/FastestButton.png");
        iShip = new ImageIcon("PlagueIncSymulator/Data/Images/ship_icon.png");
        iAirPlane = new ImageIcon("PlagueIncSymulator/Data/Images/airplane_icon.png");


        //BUTTONS
        bTime = new JButton(iTimeSpeed1);
        bTime.setBounds(1100,20,48,48);
        bTime.addActionListener(this);
        add(bTime);

        bVirus = new JButton(iVirus);
        bVirus.setBounds(650,115,70,70);
        bVirus.addActionListener(this);
        add(bVirus);

        bDeath = new JButton(iDeath);
        bDeath.setBounds(750,115,70,70);
        bDeath.addActionListener(this);
        add(bDeath);

        bAirPlane = new JButton(iAirPlane);
        bAirPlane.setBounds(650, 10, 70,70);
        bAirPlane.addActionListener(this);
        add(bAirPlane);

        bShip = new JButton(iShip);
        bShip.setBounds(750, 10, 70,70);
        bShip.addActionListener(this);
        add(bShip);


        //LABELS
        lDeadUpgradePrize = new JLabel(needToUpgradeKilling +"vP to upgrade");
        lDeadUpgradePrize.setBounds(720, 205, 120,20);
        lDeadUpgradePrize.setFont(new Font("SansSerif",Font.PLAIN,10));
        lDeadUpgradePrize.setHorizontalAlignment(SwingConstants.CENTER);
        add(lDeadUpgradePrize);

        lDeadLvl = new JLabel("(" + killingLvl +"/5)");
        lDeadLvl.setBounds(750, 185, 70,20);
        lDeadLvl.setFont(new Font("SansSerif",Font.PLAIN,14));
        lDeadLvl.setHorizontalAlignment(SwingConstants.CENTER);
        lDeadLvl.setForeground(Color.red);
        add(lDeadLvl);

        lVirusUpgradePrize = new JLabel(needToUpgradeInfecting+"vP to upgrade");
        lVirusUpgradePrize.setBounds(620, 205, 120,20);
        lVirusUpgradePrize.setFont(new Font("SansSerif",Font.PLAIN,10));
        lVirusUpgradePrize.setHorizontalAlignment(SwingConstants.CENTER);
        add(lVirusUpgradePrize);

        lVirusLevel = new JLabel("(" + infectingLvl +"/3)");
        lVirusLevel.setBounds(650, 185, 70,20);
        lVirusLevel.setFont(new Font("SansSerif",Font.PLAIN,14));
        lVirusLevel.setHorizontalAlignment(SwingConstants.CENTER);
        lVirusLevel.setForeground(Color.red);
        add(lVirusLevel);

        lPlanePrize = new JLabel("10 vP to activate");
        lPlanePrize.setBounds(620, 90, 120,20);
        lPlanePrize.setFont(new Font("SansSerif",Font.PLAIN,10));
        lPlanePrize.setHorizontalAlignment(SwingConstants.CENTER);
        add(lPlanePrize);

        lShipPrize = new JLabel("10 vP to activate");
        lShipPrize.setBounds(720, 90, 120,20);
        lShipPrize.setFont(new Font("SansSerif",Font.PLAIN,10));
        lShipPrize.setHorizontalAlignment(SwingConstants.CENTER);
        add(lShipPrize);

        lShipStatus = new JLabel("Disable");
        lShipStatus.setBounds(750, 70, 70,30);
        lShipStatus.setFont(new Font("SansSerif",Font.PLAIN,14));
        lShipStatus.setForeground(Color.red);
        lShipStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(lShipStatus);

        lPlaneStatus = new JLabel("Disable");
        lPlaneStatus.setBounds(650, 70, 70,30);
        lPlaneStatus.setFont(new Font("SansSerif",Font.PLAIN,14));
        lPlaneStatus.setForeground(Color.red);
        lPlaneStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(lPlaneStatus);

        lTimeSpeed = new JLabel("Time speed");
        lTimeSpeed.setBounds(1075 , 68, 100,30);
        lTimeSpeed.setFont(new Font("SansSerif",Font.PLAIN,14));
        lTimeSpeed.setHorizontalAlignment(SwingConstants.CENTER);
        add(lTimeSpeed);

        lPoints = new JLabel("vPoints");
        lPoints.setBounds(400, 20, 300,30);
        lPoints.setFont(new Font("SansSerif",Font.PLAIN,24));
        lPoints.setHorizontalAlignment(SwingConstants.CENTER);
        add(lPoints);

        lHealthy = new JLabel("Healthy");
        lHealthy.setBounds(20, 250, 300,50);
        lHealthy.setFont(new Font("SansSerif",Font.PLAIN,24));
        lHealthy.setHorizontalAlignment(SwingConstants.CENTER);
        add(lHealthy);

        lInfected = new JLabel("Infected");
        lInfected.setBounds(350, 250, 300,50);
        lInfected.setFont(new Font("SansSerif",Font.PLAIN,24));
        lInfected.setHorizontalAlignment(SwingConstants.CENTER);
        add(lInfected);

        lDead = new JLabel("Dead");
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


        //TEXTAREA
        tHealtyCoutries = new JTextArea("");
        tHealtyCoutries.setEditable(false);
        tHealtyCoutries.setLineWrap(true);
        tHealtyCoutries.setWrapStyleWord(true);
        tHealtyCoutries.setFont(new Font("SansSerif",Font.PLAIN,15));

        tInfectedCountries = new JTextArea("");
        tInfectedCountries.setEditable(false);
        tInfectedCountries.setLineWrap(true);
        tInfectedCountries.setWrapStyleWord(true);
        tInfectedCountries.setFont(new Font("SansSerif",Font.PLAIN,15));

        tDeadCoutries = new JTextArea("");
        tDeadCoutries.setEditable(false);
        tDeadCoutries.setLineWrap(true);
        tDeadCoutries.setWrapStyleWord(true);
        tDeadCoutries.setFont(new Font("SansSerif",Font.PLAIN,15));


        //SCROLLAREA
        spHealthy = new JScrollPane(tHealtyCoutries,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spHealthy.setBounds(20,300,300,300);
        add(spHealthy);

        spInfected = new JScrollPane(tInfectedCountries,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spInfected.setBounds(350,300,300,300);
        add(spInfected);

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
            lDay.setText("Day: " + World.day);
            lPoints.setText("vPoints: " + World.virus.getPoints());
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
            lHealthyPopulation.setText("Healthy: " + Long.toString(World.healthyPopulation - World.infectedPopulation - World.deadPopulation) + " (" + Math.round((float) (World.healthyPopulation- World.infectedPopulation - World.deadPopulation) / World.population * 100) + "%)");
            lInfectedPopulation.setText("Infected: " + Long.toString(World.infectedPopulation) + " (" + Math.round((float) World.infectedPopulation / World.population * 100) + "%)");
            lDeadPopulation.setText("Dead: " + Long.toString(World.deadPopulation) + " (" + Math.round((float) World.deadPopulation / World.population * 100) + "%)");

            repaint();
            if(World.day%50 == 0)World.virus.addPoint();
            World.day++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == bTime){
            if(timeLvl == 1) {
                bTime.setIcon(iTimeSpeed2);
                timeLvl++;
                World.speedup_time();
            }
            else if (timeLvl == 2) {
                bTime.setIcon(iTimeSpeed3);
                timeLvl++;
                World.speedup_time();
            }
            else {
                bTime.setIcon(iTimeSpeed1);
                timeLvl =1;
                World.speedup_time();
            }
        } else if (source == bAirPlane && !airPlane && World.virus.getPoints() >= 10) {
            airPlane = true;
            World.virus.setPoints(World.virus.getPoints() - 10);
            World.virus.setOnAirplane();
            lPlanePrize.setText("");
            lPlaneStatus.setText("Activate");
            lPlaneStatus.setForeground(Color.green);
        }
        else if (source == bShip && !ship && World.virus.getPoints() >= 10) {
            ship = true;
            World.virus.setPoints(World.virus.getPoints() - 10);
            World.virus.setOnAirplane();
            lShipPrize.setText("");
            lShipStatus.setText("Activate");
            lShipStatus.setForeground(Color.green);
        }
        else if (source == bVirus && infectingLvl<3 && World.virus.getPoints() >= needToUpgradeInfecting) {
            World.virus.setPoints(World.virus.getPoints() - needToUpgradeInfecting);
            needToUpgradeInfecting+=8;
            infectingLvl++;
            lVirusLevel.setText("(" + infectingLvl +"/3)");
            lVirusUpgradePrize.setText(needToUpgradeInfecting+"vP to upgrade");
            World.virus.setCheanseForInfection(World.virus.getCheanseForInfection() + 0.05);
            if(infectingLvl == 3)lVirusUpgradePrize.setText("max upgraded");
        }
        else if (source == bDeath && killingLvl<5 && World.virus.getPoints() >= needToUpgradeKilling) {
            World.virus.setPoints(World.virus.getPoints() - needToUpgradeKilling);
            needToUpgradeKilling+=10;
            killingLvl++;
            lDeadLvl.setText("(" + killingLvl +"/5)");
            lDeadUpgradePrize.setText(needToUpgradeKilling+"vP to upgrade");
            World.virus.setCheanseForDeath(World.virus.getCheanseForDeath() + 0.01);
            if(killingLvl == 3)lVirusUpgradePrize.setText("max upgraded");
        }
    }
}


