package main;

import object.OBJ_Coin;
import object.OBJ_Door;

public class AssetManager {
    GamePanel gp;
    public AssetManager(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Coin();
        gp.obj[0].worldX = 5 * gp.tileSize;
        gp.obj[0].worldY = 5 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 7 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize;
    }
}
