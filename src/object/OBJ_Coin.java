package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coin extends SuperObject {

    GamePanel gp;

    public OBJ_Coin(GamePanel gp) {
        name = "Coin";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/objects/coin.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
