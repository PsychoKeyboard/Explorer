package entity;

import main.KeyHandler;
import main.App;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Color;

public class Player extends Entity 
{
    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp, KeyHandler keyH) 
    {
        this.gp = gp;
        this.keyH = keyH;

        SetDefaultValues();
        getPlayerImage();
    }    

    public void SetDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/down1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/down2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/up1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void update()
    {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
        {
            if(keyH.upPressed)
            {
                y -= speed;
                direction = "up";
            }
            if(keyH.downPressed)
            {
                y += speed;
                direction = "down";
            }
            if(keyH.leftPressed)
            {
                x -= speed;
                direction = "left";
            }
            if(keyH.rightPressed)
            {
                x += speed;
                direction = "right";
            }
            

            //to change the image, so that it looks like the sprite is walking
            spriteCounter++;

            //after 10 frames change image
            if(spriteCounter > 10)
            {
                if(spriteNum == 1)
                    spriteNum = 2;
                else if(spriteNum == 2)
                    spriteNum = 1;    

                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2)
    {
                //TEMPORARY: represents my player, currently a rectangle of size equal to a tile
    //             g2.setColor(Color.white);
    //             g2.fillRect(x, y, gp.tileSize, gp.tileSize);   
        
        BufferedImage image = null;

        switch(direction){
        case "up":
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            break;
        case "down":
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
            break;
        case "left":
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            break;
        case "right":
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            break; 
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
