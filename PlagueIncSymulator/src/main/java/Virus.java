
/**
 * Klasa reprezentujaca wirusa
 */

public class Virus {
    private final String virus_name;
    private int virus_points;
    public double cheanseForDeath;
    public double cheanseForInfection;
    public double cheanseForInfectedFlight;
    public double cheanseForInfectedShipCruise;
    private int virusPoints;
    private boolean airplane, ship;

    /**
     * Konstruktor klasy Wirus
     * @param Name nazwa Wirusa
     */
    public Virus(String Name){
        this.virus_name = Name;
        cheanseForDeath = 0;
        cheanseForInfection = 0.1;
        cheanseForInfectedFlight = 10;
        virusPoints = 0;
        airplane = false;
        ship = false;
    }
    /**
     * Ustawia szansę na zabicie
     *
     */
    public void setCheanseForDeath(double a){cheanseForDeath=a;}
    /**
     * Zwraca szanse na zabicie
     *
     * @return szansa na zabicie
     */
    public double getCheanseForDeath(){return cheanseForDeath;}
    /**
     * Ustawia szansę na zarażenie
     *
     */
    public void setCheanseForInfection(double a){cheanseForInfection=a;}
    /**
     * Zwraca szanse na zarażenie
     *
     * @return szansa na zarażenie
     */
    public double getCheanseForInfection(){return cheanseForInfection;}
    /**
     * Ustawia liczbę punktów
     *
     */
    public void setPoints(int a){virusPoints = a;}

    /**
     * Dodaje liczbę puktów
     *
     */
    public void addPoint(){virusPoints++;}

    /**
     * Zwraca liczbe zarazonych
     *
     * @return  liczbę punktów
     */
    public int getPoints(){return virusPoints;}
    /**
     * Zwraca nazwe Wirusa
     *
     * @return  nazwa Wirusa
     */
    public String getName(){return virus_name;}

    /**
     * Ustawia obecnosc samolotow
     */
    public void setOnAirplane(){airplane=true;}
    /**
     * Ustawia obecnosc statkow
     */
    public void setOnShip(){ship=true;}

    /**
     * Zwraca status obecnosci samolotow.
     *
     * @return Status obecnosci samolotow.
     */

    public boolean getAirplaneStatus(){return airplane;}

    /**
     * Zwraca status obecnosci statkow.
     *
     * @return Status obecnosci statkow.
     */

    public boolean getShipStatus(){return ship;}
}
