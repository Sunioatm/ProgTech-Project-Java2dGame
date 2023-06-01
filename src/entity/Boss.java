package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Boss extends Entity{
    public static int bossHP = 300;
    boolean callBoss = false;
    boolean bossdeath = false;
    public static boolean getShot = false;
    GamePanel gp;

    public Boss(GamePanel gp){
        this.gp = gp;
        x = 10000;
        y = -200;
        speed = 4;
        getImage();
    }
    public void draw(Graphics2D g2){
        
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial Rounded MT",3,14));
        g2.drawString(String.valueOf(bossHP)+" : ",x+10,y-15);   
        g2.setColor(Color.red);
        g2.fillRect(x+50, y-30, bossHP, 20);
        
        BufferedImage image = dragon1;        
        if (Boss.getShot){
            image = dragon2;
        } else {
            image = dragon1;
        }
        g2.drawImage(image,x,y,360,230,null);

             
    }
    public void getImage(){
        try{
            dragon1 = ImageIO.read(getClass().getResourceAsStream("/img/dragon2.png"));
            dragon2 = ImageIO.read(getClass().getResourceAsStream("/img/dragon3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        y += speed;
        if (y > 100){
            y = 100;
        }
        if (Walls.wc > 14){
            Walls.wc = -1;
            x = 0; 
            y = -200;
        }
        if (bossHP <= 0){
            x = 1000;
            if (bossdeath == false){
                gp.play(3);
            }
            bossdeath = true;
        }

        spriteCounter++;
        if (spriteCounter > 20){
            Boss.getShot = false;
            spriteCounter = 0;
        }

    }

    public static void shootBoss(){
        bossHP -= 1;
    }

}
