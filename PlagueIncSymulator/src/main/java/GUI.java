import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    JFrame okno;

    private final int width = 1440;
    private final int height = 810;

    JButton bNewGame,bLoad,bExit;
    JTextField tName;

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

        tName = new JTextField();
        tName.setBounds(50,50,100,200);
        tName.setToolTipText("Podaj współczynnik A");
        pNewGame.add(tName);
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

        }
        else if (zrodlo == bExit) {
            okno.dispose();
        }
    }
}


