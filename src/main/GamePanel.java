package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //ustawienia ekranu
    final int originalTileSize = 16; //16x16px
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48px
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 16;
    public final int screenWidth = maxScreenCol * tileSize; //960px
    public final int screenHeight = maxScreenRow * tileSize; //768px

    //świat
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker colChecker = new CollisionChecker(this);
    public AssetManager aManager = new AssetManager(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Postacie i obiekty
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[5];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aManager.setObject();

        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        tileM.draw(g2d);

        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2d, this);
            }
        }

        player.draw(g2d);

        ui.draw(g2d);

        g2d.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
