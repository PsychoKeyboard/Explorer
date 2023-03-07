package main;
import javax.swing.JFrame;

public class App 
{
    //cool
    public static void main(String[] args) throws Exception 
    {
        //creates my window application where the game will play
        JFrame window = new JFrame();

        // closes JFrame windown when x is pressed
        // could also use JFrame.HIDE_ON_CLOSE to hide when x is pressed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        window.setResizable(false);
        window.setTitle("Bazinga");

        GamePanel gamePanel = new GamePanel();      //576x768
        window.add(gamePanel);

        //causes this window to be sized to fit the preferred size and layouts of its subcomponents(GamePanel)
        window.pack();          

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //start a new thread which starts clock
        gamePanel.startGameThread();        

    }
}
