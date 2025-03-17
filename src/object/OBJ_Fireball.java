package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;

    public OBJ_Fireball(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 6;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        alive = false;

        solidArea.x = 2;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("projectile/fireball_up_1");
        up2 = setup("projectile/fireball_up_2");
        down1 = setup("projectile/fireball_down_1");
        down2 = setup("projectile/fireball_down_2");
        left1 = setup("projectile/fireball_left_1");
        left2 = setup("projectile/fireball_left_2");
        right1 = setup("projectile/fireball_right_1");
        right2 = setup("projectile/fireball_right_2");
    }
}
