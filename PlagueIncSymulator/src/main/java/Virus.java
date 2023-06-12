
/**
 * Klasa reprezentujaca wirusa
 */

public class Virus {
    private String virus_name;
    private int virus_points;
    public double cheanseForDeath;
    public double cheanseForInfection;
    private int virusPoints;
    public int difficulty;
    private boolean airplane, ship;

    /**
     * Konstruktor klasy Wirus
     * @param Name nazwa Wirusa
     */
    public Virus(String Name){
        this.virus_name = Name;
        cheanseForDeath = 0;
        cheanseForInfection = 0.1;
        virusPoints = 0;
        airplane = false;
        ship = false;
    }

    /**
     * Zwieksza liczbe zarazonych
     *
     */
    public void addPoint(){virusPoints++;}

    /**
     * Zwraca liczbe zarazonych
     *
     * @return  Liczba zarazonych
     */
    public int getPoints(){return virusPoints;}
    /**
     * Zwraca nazwe Wirusa
     *
     * @return  nazwa Wirusa
     */
    public String getName(){return virus_name;}

    /**
     * Ustawia obecnosc samolotow na true???? DO
     */
    public void setOnAirplane(){airplane=true;}
    /**
     * Ustawia obecnosc statkow na true ???? DO POPRAWY
     */
    public void setOnShip(){ship=true;}

    public boolean getAirplaneStatus(){return airplane;}

    public boolean getShipStatus(){return ship;}
}
