package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    GamePanel gp;

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction = "down";
    public Rectangle solidArea;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int type; // 0 = player, 1 = monster
    public int attack;
    public int defense;
    public int actionLockCounter = 0;
    public int shotAvailableCounter = 0;
    public String name;
    public boolean invincible = false;
    public boolean alive = true;
    public boolean dying = false;
    public Projectile projectile;

    public int invincibleCounter = 0;
    public int dyingCounter = 0;

    public int maxLife;
    public int life;

    public BufferedImage image, image1, image2;
    public boolean collision = false;

    public Entity(GamePanel gp){
        this.gp = gp;
        solidArea = new Rectangle();
    }

    public void setAction(){}

    public void update(){
        setAction();

        collisionOn = false;
        gp.colChecker.checkTile(this);
        gp.colChecker.checkObject(this, false);
        boolean contactPlayer = gp.colChecker.checkPlayer(this);
        gp.colChecker.checkEntity(this, gp.monster);

        if(this.type == 1  && contactPlayer == true){
            if(gp.player.invincible == false){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

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

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("../res/" + imagePath + ".png"));
            //Konwersja do typu TYPE_INT_ARGB, aby zachować kolory
            BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = convertedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            image = uTool.scaleImage(convertedImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

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
            if (image == null) {
                image = down1;
            }
            if(invincible == true){
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            if(dying == true){
                dyingAnimation(g2d);
            }

            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void dyingAnimation(Graphics2D g2d){
        dyingCounter++;
        if(dyingCounter <= 5){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > 5 && dyingCounter <= 10){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > 10 && dyingCounter <= 15){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > 15 && dyingCounter <= 20){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > 20 && dyingCounter <= 25){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > 25 && dyingCounter <= 30){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > 40) {
            dying = false;
            alive = false;
        }
    }
}
