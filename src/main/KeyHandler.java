package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shotKeyPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_SPACE){
                shotKeyPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.pauseState;
            }
        }

        //Menu
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNumber--;
                if(gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNumber++;
                if(gp.ui.commandNumber > 1){
                    gp.ui.commandNumber = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNumber == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNumber == 1){
                    System.exit(0);
                }
            }
        }

        if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

        if(gp.gameState == gp.endGameState){
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            shotKeyPressed = false;
        }
    }
}
