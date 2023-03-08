package main;
//import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.awt.Color;
import java.awt.Dimension;              //Java Abstract Window Toolkit // to develop GUI
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;
//import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;
import javax.swing.text.PlainDocument;

import entity.Player;

public class GamePanel extends JPanel implements Runnable
{
    //Screen Settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;            //need to scale up the 16x16 because too small on computer screen

    public final int tileSize = originalTileSize * scale; //48x48 tile size
    final int maxScreenCol = 16;    // num of tiles in a col
    final int maxScreenRow = 12;    // num of tiles in row // R:C :: 3:4
    final int screenWidth = tileSize * maxScreenCol;    // 768 pixels
    final int screenHeight = tileSize * maxScreenRow;   // 576 pixels

    Thread gameThread;      //starts the game clock 
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this , keyH);

    //FPS
    int FPS = 60;
    
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // to improve game performance
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);    //"focuses" the gamePanel to receive key input
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);          //this ofcourse refers to "this class" GamePanel
        gameThread.start();                 //automatically calls run method 
    
    }

    @Override
    public void run()       //when the game creates a new thread, it automatically launches this run method
    {

        double drawInterval = 1000000000/FPS;    //10^9 divide by my FPS     //draw the screen every 0.00000000167ns or 0.0167s
        long lastTime = System.nanoTime();
        double nextDrawTime = lastTime + drawInterval;
        
        //to display FPS
        long currentTime;
        long timer = 0;         
        int drawCount = 0;


        //game loop //game will keep updating as long as the thread isnt null
        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            //update the information like movement and stuff
            update();

            //draw the screen with updated information
            repaint();
            drawCount++;

            try 
            {
                double remainingTime = nextDrawTime - System.nanoTime(); //how much time reamins until we have to draw again?
                remainingTime = remainingTime/1000000;          //convert to ms cause sleep uses ms


                //if remaining time < 0 then there is no time to sleep!
                if(remainingTime < 0)                   
                    remainingTime = 0;

                Thread.sleep((long)remainingTime);              //pause game loop until its time to draw again

                nextDrawTime += drawInterval;
                
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }

            //display FPS
            if(timer >= 1000000000)
            {
                System.out.println("FPS: "+ drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
        
    }

    //update information
    public void update()
    {
        player.update();
    }

    //draw on my window
    //this method is called automatically when a panel is created
    public void paintComponent(Graphics g)
    {
        //paintComponent is a class in JPanel
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;      //casting my graphics g to graphics 2d
        
        player.draw(g2);

        //disposes of graphics coontext. Won't be able to use this graphic after this line
        g2.dispose();

    }

}
