package main;

import object.OBJ_Coin;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_30;
    BufferedImage coinImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);
        OBJ_Coin coin = new OBJ_Coin();
        coinImage = coin.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d) {

        if(gameFinished == true){
            g2d.setFont(arial_30);
            g2d.setColor(Color.YELLOW);
            g2d.setFont(g2d.getFont().deriveFont(50F));

            String text;
            int textLength;
            int x;
            int y;

            text = "KONIEC GRY!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 2);
            g2d.drawString(text, x, y);

            gp.gameThread = null;
        }
        else{
            g2d.setFont(arial_30);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(coinImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2d.drawString("x " + gp.player.hasCoin, 70, 60);

            //wiadomość
            if(messageOn == true){

                g2d.setFont(g2d.getFont().deriveFont(25F));
                g2d.drawString(message, 70, 100);

                messageCounter++;
                if(messageCounter > 90){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
