package main;

import monster.Slime;
import object.OBJ_Coin;
import object.OBJ_Door;

public class AssetManager {
    GamePanel gp;
    public AssetManager(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Coin(gp);
        gp.obj[0].worldX = 11 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = 29 * gp.tileSize;
        gp.obj[1].worldY = 29 * gp.tileSize;
    }

    public void setMonster(){
        gp.monster[0] = new Slime(gp);
        gp.monster[0].worldX = 12 * gp.tileSize;
        gp.monster[0].worldY = 12 * gp.tileSize;

        gp.monster[1] = new Slime(gp);
        gp.monster[1].worldX = 14 * gp.tileSize;
        gp.monster[1].worldY = 14 * gp.tileSize;
    }
}
