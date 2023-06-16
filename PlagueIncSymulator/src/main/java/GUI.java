import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * Klasa GUI reprezentujaca interfejs graficzny symulacji.
 */
public class GUI extends JFrame implements ActionListener {
    JFrame okno;

    private final int width = 1200;
    private final int height = 675;

    JButton bNewGame,bLoad,bExit, bPlay;
    JTextField tName;
    JCheckBox difficulty_hard, difficulty_normal,difficulty_easy;
    JPanel pMainPage, pNewGame;


    /**
     * Metoda glowna aplikacji
     *
     * @param args argumenty
     */
    public static void main(String[] args) {
        GUI appMenu = new GUI();
        Configurator.startConfigurator();
    }
    /**
     * Konstruktor klasy GUI
     *
     */


    public GUI(){
        okno = new JFrame("Plague INC");
        okno.setSize(width,height);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setResizable(false);
        okno.setLayout(null);
        okno.setVisible(true);
        MainPage();
    }


    /**
     * Tworzy strone glowna interfejsu graficznego
     *
     */
    public void MainPage(){
        pMainPage = new JPanel();
        pMainPage.setBounds(0,0,width,height);
        bNewGame = new JButton("New Game");
        bNewGame.setBounds((width/2) - 100,(height/3)-15,200,30);
        bNewGame.addActionListener(this);
        pMainPage.add(bNewGame);

        bLoad = new JButton("Load");
        bLoad.setBounds((width/2) - 100,(height/2)-15,200,30);
        pMainPage.add(bLoad);

        bExit = new JButton("Exit");
        bExit.setBounds((width/2) - 100,(height*2/3)-15,200,30);
        bExit.addActionListener(this);
        pMainPage.add(bExit);
        okno.add(pMainPage);
    }
    /**
     * Tworzy panel nowej gry.
     */

    public void NewGame(){
        pNewGame = new JPanel();
        pNewGame.setBounds(0,0,width,height);

        JLabel lName = new JLabel("Podaj nazwę Wirusa");
        lName.setBounds((width/2)-120,120,300,60);
        lName.setFont(new Font("SansSerif",Font.BOLD,24));

        tName = new JTextField();
        tName.setBounds((width/2)-100,(height/3)-30,200,60);
        tName.setFont(new Font("SansSerif",Font.BOLD,18));


        // Zmieniam zeby mozna bylo wybrac opcje tylko hard normal albo easy
        ButtonGroup difficultyGroup = new ButtonGroup();

        JRadioButton difficulty_hard = new JRadioButton("Trudny");
        difficulty_hard.setBounds((width/2) - 175, (height/2)-20, 150, 20);
        difficultyGroup.add(difficulty_hard);
        difficulty_hard.addActionListener(this);

        JRadioButton difficulty_normal = new JRadioButton("Normalny");
        difficulty_normal.setBounds((width/2) - 35, (height/2)-20, 150, 20);
        difficultyGroup.add(difficulty_normal);
        difficulty_normal.addActionListener(this);

        JRadioButton difficulty_easy = new JRadioButton("Łatwy");
        difficulty_easy.setBounds((width/2) + 115, (height/2)-20, 150, 20);
        difficultyGroup.add(difficulty_easy);
        difficulty_easy.addActionListener(this);


        bPlay = new JButton("Play");
        bPlay.setBounds((width/2)-50,(height/2)+50,100,50);
        bPlay.addActionListener(this);

        pNewGame.add(difficulty_hard);
        pNewGame.add(difficulty_normal);
        pNewGame.add(difficulty_easy);
        pNewGame.add(tName);
        pNewGame.add(lName);
        pNewGame.add(bPlay);
        okno.add(pNewGame);
    }


    /**
     * Obsluguje akcje wykonane przez uzytkownika
     *
     * @param e - klikniety przycisk
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        if(zrodlo == bNewGame) {
            setLayout(null);
            okno.remove(pMainPage);
            okno.revalidate();
            okno.repaint();
            NewGame();

        } else if (zrodlo == bPlay) {
            try {
                World.StartGame("Niemcy",tName.getText());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }


        } else if (zrodlo == bExit) {
            okno.dispose();
        }
    }
}


