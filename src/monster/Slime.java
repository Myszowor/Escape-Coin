package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Slime extends Entity {
    public Slime(GamePanel gp){
        super(gp);

        type = 1;
        name = "Slime";
        speed = 1;
        maxLife = 2;
        life = maxLife;

        solidArea.x = 2;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/greenslime_1");
        up2 = setup("/monster/greenslime_2");
        down1 = setup("/monster/greenslime_1");
        down2 = setup("/monster/greenslime_2");
        left1 = setup("/monster/greenslime_1");
        left2 = setup("/monster/greenslime_2");
        right1 = setup("/monster/greenslime_1");
        right2 = setup("/monster/greenslime_2");
    }

    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i >= 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
        }
    }
}
