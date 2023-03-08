package entity;

import java.awt.image.BufferedImage;

public class Entity 
{
    public int x, y;
    public int speed;    

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;      //just a timing thing so that we know when to change the image
    public int spriteNum = 1;   //keeps track of which image to use
}
