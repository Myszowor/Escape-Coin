package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("../res/maps/map.txt");
    }

    public void getTileImage() {
        setup(0, "grass1", false);
        setup(1, "grass2", false);
        setup(2, "dirt1", false);
        setup(3, "dirt2", true);
        setup(4, "stone1", false);
        setup(5, "stone2", true);
    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }
    public void draw(Graphics2D g2d) {
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNumber = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2d.drawImage(tile[tileNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol==gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
