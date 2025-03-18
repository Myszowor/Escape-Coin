package entity;

import main.GamePanel;

public abstract class Projectile extends Entity {

    public Projectile(GamePanel gp){
        super(gp);
    }
    public void set(int worldX, int worldY, String direction, boolean alive){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.life = this.maxLife;
    }
    public void update(){
        int monsterIndex = gp.colChecker.checkEntity(this, gp.monster);
        if(monsterIndex != 99){
            System.out.println("Fireball trafił potwora o indeksie: " + monsterIndex);
            gp.player.damageMonster(monsterIndex, attack);
            alive = false;
        }

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

        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }
}
