package main;

import entity.Arrow;

import entity.CloudList;
import entity.WallList;
import entity.EnemyList;
import entity.Boss;
import entity.GetFireball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8;
    final int maxScreenRow = 14;
    
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    int FPS = 60;
    
    Thread gameThread;
    
    KeyHandler keyH = new KeyHandler();
    
    Arrow arrow = new Arrow(this,keyH);
    CloudList cloudlist = new CloudList(this);
    WallList walllist = new WallList(this);
    EnemyList enemylist = new EnemyList(this);
    GetFireball fireballlist = new GetFireball(this);
    
    Boss boss = new Boss(this);
    JPanel number = new JPanel();

    Sound2 sound2 = new Sound2();

    
    public boolean ispress = false;
    public boolean running = false;

    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        play(5);
        
    }

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while (gameThread != null){
            update();
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
                
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    
    }
    public void update(){
        if (running == true){
        arrow.update();
        walllist.update();
        cloudlist.update();
        enemylist.update();
        fireballlist.update();
        boss.update();
        }
        runGame();
        
        //restart game ??
//        if (keyH.rPressed){
//            this.remove(Main.gamePanel);
//            System.out.println("hi");
//            keyH.rPressed = false;
//    }

            
        }
    
    public void runGame(){
        if (keyH.isSpace){
            running = true;
        } else {
            running = false;
        }  
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Image intro = null;
        
        if (running == false) {
            
            try{
                intro = ImageIO.read(getClass().getResourceAsStream("/img/intro3.png"));

            }catch(IOException e){
                e.printStackTrace();
            }
            
            g2.drawImage(intro, 0,0,384,300, null);
            g2.setColor(Color.BLUE);
            g2.setFont(new Font("Tahoma", Font.BOLD, 33));
            g2.drawString("Gun choose number", screenWidth / 2 - 165, screenHeight / 2 + 10);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Tahoma", Font.BOLD, 15));
            g2.drawString("Game Instruction :", screenWidth / 2 - 70, screenHeight / 2 + 40);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("1. Press Left or Right button to move your gun", screenWidth - 350, screenHeight / 2 + 70);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("2. Press Up button to shoot your gun", screenWidth - 350, screenHeight / 2 + 90);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("    (Can fire only when boss come out)", screenWidth - 350, screenHeight / 2 + 110);
            g2.setColor(Color.BLACK);

            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("3. Move your gun to avoid birds and choose", screenWidth - 350, screenHeight / 2 + 130);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("    a better choice to increase or decrease your number", screenWidth - 350, screenHeight / 2 + 150);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("    of arrows.", screenWidth - 350, screenHeight / 2 + 170);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("4. Game will end if you can defeat the boss", screenWidth - 350, screenHeight / 2 + 190);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Angsna New", Font.BOLD, 13));
            g2.drawString("    else you lose", screenWidth - 350, screenHeight / 2 + 210);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Tahoma", Font.BOLD, 15));
            g2.drawString(">> Press Spacebar to start the game <<", screenWidth / 2 - 150, screenHeight - 80);
            g2.setColor(Color.RED);
            g2.setFont(new Font("Tahoma", Font.BOLD, 30));
            g2.drawString("Good luck, have fun!!!", screenWidth - 355, screenHeight - 40);
        } 
        
        else {

        cloudlist.draw(g2);
        enemylist.draw(g2);
        walllist.draw(g2);
        fireballlist.draw(g2);
        boss.draw(g2);
        arrow.draw(g2);
        
        g2.dispose();
        
        }
        
    }
    
    public void play(int i){
        sound2.play(i);
    }
}
