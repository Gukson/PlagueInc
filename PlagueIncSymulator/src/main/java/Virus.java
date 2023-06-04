public class Virus {
    private String virus_name;
    private int virus_points;

    public double cheanseForDeath;
    public double cheanseForInfection;
    private int virusPoints;
    public int difficulty;
    private boolean airplane, ship;

    public Virus(String Name){
        this.virus_name = Name;
        cheanseForDeath = 0;
        cheanseForInfection = 0.1;
        virusPoints = 0;
        airplane = false;
        ship = false;
    }

    public void addPoint(){virusPoints++;}
    public int getPoints(){return virusPoints;}
    public String getName(){return virus_name;}
    public void setOnAirplane(){airplane=true;}
    public void setOnShip(){ship=true;}







}
