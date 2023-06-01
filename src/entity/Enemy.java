package entity;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Enemy extends Entity{
    GamePanel gp;

    public Enemy(GamePanel gp,int thisx,int thisy){
        this.gp = gp;
        x = thisx;
        y = thisy;
        getImage();
    }
    public void draw(Graphics2D g2){
        
        g2.setFont(new Font("Arial Rounded MT",3,14));
        g2.drawString(String.valueOf(enemyHP),x+10,y-20);
        g2.drawImage(enemy,x,y,34,34,null);
        
        
    }
    public void getImage(){
        try{
            enemy = ImageIO.read(getClass().getResourceAsStream("/img/bird2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        Random rand = new Random();
        y += 7;
        if (y < -150){
            y = rand.nextInt(-450,-150);
            x = rand.nextInt(0,360);
            enemyHP = rand.nextInt(1,11)+Entity.amount/6;
        }
        if (y > 800){
            y = rand.nextInt(-450,-150);
            x = rand.nextInt(0,360);
            enemyHP = rand.nextInt(1,11)+Entity.amount/6;
        }

    }
    public Rectangle getEnemyHitbox(){
        return new Rectangle(x,y,34,3);
    }
    public int getEnemyHP(){
        return enemyHP;
    }

}
