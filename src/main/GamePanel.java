package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 40;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker colChecker = new CollisionChecker(this);
    public AssetManager aManager = new AssetManager(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Postacie i obiekty
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[5];
    public Entity monster[] = new Entity[10];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //Stany gry
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int titleState = 3;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aManager.setObject();
        aManager.setMonster();

        playMusic(0);
        stopMusic();
        gameState = titleState;
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
        if(gameState == playState){
            player.update();

            for(int i = 0; i < monster.length; i++) {
                if(monster[i] != null){
                    if(monster[i].alive == true && monster[i].dying == false){
                        monster[i].update();
                    }
                    if(monster[i].alive == false){
                        monster[i] = null;
                    }
                }
            }

            for(int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive == true){
                        projectileList.get(i).update();
                    }
                }
                if(projectileList.get(i).alive == false){
                    projectileList.remove(i);
                }
            }
        }

        if(gameState == pauseState){
            //todo
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        if(gameState == titleState){
            ui.draw(g2d);
        }

        else{
            tileM.draw(g2d);

            entityList.add(player);

            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }

            //Sortowanie
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //Rysowanie podmiotów
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2d);
            }

            //Pusta lista podmiotów
            for(int i = 0; i < entityList.size(); i++){
                entityList.clear();
            }

            ui.draw(g2d);
        }

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
