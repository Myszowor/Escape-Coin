package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp) {
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/objects/heart_full.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("../res/objects/heart_half.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("../res/objects/heart_blank.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
