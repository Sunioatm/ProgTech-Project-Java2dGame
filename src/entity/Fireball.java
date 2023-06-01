package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Fireball extends Entity{
    
       GamePanel gp;
       boolean ballout = false;
       int y;

    public Fireball(GamePanel gp){
        this.gp = gp;
        x = 1000;
        y = -200;
        speed = 4;
        getImage();
    }
    public void draw(Graphics2D g2){
        

        g2.drawImage(fireball,x,y,360,120,null);
             
    }
    public void getImage(){
        try{
            fireball = ImageIO.read(getClass().getResourceAsStream("/img/fireball2.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        Random rand = new Random();
        y+=3;
        if (Walls.wc == -1 && amount <= 0 && Boss.bossHP > 0 ){
            if (ballout == false){
                x = 0;
                y = -400;
                ballout = true;
            }
            }

    }
    public Rectangle getFireballHitbox(){
        return new Rectangle(x,y,360,3);
    }


}


