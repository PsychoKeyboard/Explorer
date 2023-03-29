package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;            //each tile has an image. Eg, tile[0] is grass

    //the map 
    int mapTileNum[][];


    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];

        mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    public void getTileImage(){

        try{


            //tile 0 -> grass; tile 1 -> wall; tile2 -> water; tile3 -> earth; tile4 -> tree; tile5 -> sand
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void loadMap(String filePath){

        try{

            //using input stream to import text file and buffered reader to read
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col< gp.getMaxScreenCol() && row < gp.getMaxScreenRow()){

                String line = br.readLine();

                while(col<gp.getMaxScreenCol()){
                    //split line into number array
                    String numbers[] = line.split(" ");

                    //convert string to int
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.getMaxScreenCol()){
                    col = 0;
                    row++;
                }

            }

            br.close();

        }catch(Exception e){

        }
    }


    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()){

            //get num at position col row
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            
            col++;
            x+= gp.tileSize;

            if(col == gp.getMaxScreenCol()){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        
        
        }


        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
    }
    
}
