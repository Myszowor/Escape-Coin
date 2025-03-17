package entity;


import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasCoin = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 10 * gp.tileSize;
        worldY = 10 * gp.tileSize;
        speed = 3;
        direction = "down";

        maxLife = 4;
        life = maxLife;
    }
    public void getPlayerImage(){
        up1 = setup("up1-Photoroom");
        up2 = setup("up2-Photoroom");
        up3 = setup("up3-Photoroom");
        down1 = setup("down1-Photoroom");
        down2 = setup("down2-Photoroom");
        down3 = setup("down3-Photoroom");
        left1 = setup("left1-Photoroom");
        left2 = setup("left2-Photoroom");
        left3 = setup("left3-Photoroom");
        right1 = setup("right1-Photoroom");
        right2 = setup("right2-Photoroom");
        right3 = setup("right3-Photoroom");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
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

            //sprawdzanie kolizji struktur mapy
            collisionOn = false;
            gp.colChecker.checkTile(this);

            //sprawdzanie kolizji obiektów
            int objIndex = gp.colChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
    public void pickUpObject(int i){
        if(i != 99){
            String objectName = gp.obj[i].name;
            switch(objectName){
                case "Coin":
                    hasCoin++;
                    gp.obj[i] = null;
                    gp.playSE(1);
                    gp.ui.showMessage("Zdobyłeś monetę!");
                    break;
                case "Door":
                    if(hasCoin>0){
                        gp.obj[i] = null;
                        hasCoin--;
                        gp.ui.showMessage("Otworzyłeś drzwi tracąc 1 monetę!");
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                    }
                    else{
                        gp.ui.showMessage("Nie posiadasz monety, której możesz użyć do otwarcia drzwi!");
                    }
                    break;
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
