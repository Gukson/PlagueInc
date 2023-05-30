public class Virus {
    private String virus_name;
    private int virus_points;

    public double cheanseForDeath;
    public double cheanseForInfection;

    public int difficulty;

    public Virus(String Name){
        this.virus_name = Name;
        cheanseForDeath = 0;
        cheanseForInfection = 1.1;
    }



}
