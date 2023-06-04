public class Virus {
    private String virus_name;
    private int virus_points;

    public double cheanseForDeath;
    public double cheanseForInfection;
    public int virusPoints;

    public int difficulty;

    public Virus(String Name){
        this.virus_name = Name;
        cheanseForDeath = 0;
        cheanseForInfection = 0.1;
        virusPoints = 0;
    }

    public void addPoint(){virusPoints++;}




}
