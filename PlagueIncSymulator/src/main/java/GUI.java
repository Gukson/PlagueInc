import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    JFrame okno;

    private final int width = 1200;
    private final int height = 675;

    JButton bNewGame,bLoad,bExit, bPlay;
    JTextField tName;
    JCheckBox difficulty_hard, difficulty_normal,difficulty_easy;
    JPanel pMainPage, pNewGame;


    public static void main(String[] args) {
        GUI appMenu = new GUI();
    }

    public GUI(){
        okno = new JFrame("Plague INC");
        okno.setSize(width,height);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setResizable(false);
        okno.setLayout(null);
        okno.setVisible(true);
        MainPage();
    }

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

    public void NewGame(){
        pNewGame = new JPanel();
        pNewGame.setBounds(0,0,width,height);

        JLabel lName = new JLabel("Podaj nazwę Wirusa");
        lName.setBounds((width/2)-120,120,300,60);
        lName.setFont(new Font("SansSerif",Font.BOLD,24));

        tName = new JTextField();
        tName.setBounds((width/2)-100,(height/3)-30,200,60);
        tName.setFont(new Font("SansSerif",Font.BOLD,18));

        difficulty_hard = new JCheckBox("Trudny");
        difficulty_hard.setBounds((width/2) - 175,(height/2)-20,150,20);
        difficulty_hard.addActionListener(this);

        difficulty_normal = new JCheckBox("Normalny");
        difficulty_normal.setBounds((width/2) - 35,(height/2)-20,150,20);
        difficulty_normal.addActionListener(this);

        difficulty_easy = new JCheckBox("Łatwy");
        difficulty_easy.setBounds((width/2) + 115,(height/2)-20,150,20);
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
            World.wirus = new Virus(tName.getText());

        } else if (zrodlo == bExit) {
            okno.dispose();
        }
    }
}


