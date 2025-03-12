package entity;


import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 50 * gp.tileSize;
        worldY = 31 * gp.tileSize;
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../res/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../res/player/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("../res/player/up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../res/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../res/player/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("../res/player/down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../res/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../res/player/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../res/player/left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../res/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../res/player/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../res/player/right3.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }

            //sprawdzanie kolizji
            collisionOn = false;
            gp.colChecker.checkTile(this);

            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 13){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2){
                    spriteNumber = 3;
                }
                else if(spriteNumber == 3){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNumber == 1){
                    image = up2;
                }
                if(spriteNumber == 2){
                    image = up1;
                }
                if(spriteNumber == 3){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNumber == 1){
                    image = down2;
                }
                if(spriteNumber == 2){
                    image = down1;
                }
                if(spriteNumber == 3){
                    image = down3;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left2;
                }
                if(spriteNumber == 2){
                    image = left1;
                }
                if(spriteNumber == 3){
                    image = left3;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image = right2;
                }
                if(spriteNumber == 2){
                    image = right1;
                }
                if(spriteNumber == 3){
                    image = right3;
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
