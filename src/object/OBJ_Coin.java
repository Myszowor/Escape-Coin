package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;


public class OBJ_Coin extends Entity {

    public OBJ_Coin(GamePanel gp) {
        super(gp);

        name = "Coin";
        down1 = setup("objects/coin");

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;
    }
}
