package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import main.GamePanel;

public class Clouds extends Entity{
    GamePanel gp;
    public Clouds(GamePanel gp,int thisx,int thisy,int thisspeed){
        this.gp = gp;
        x = thisx;
        y = thisy;
        speed = thisspeed;
        getImage();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(cloud,x,y,66,31,null);

    }
    public void getImage(){
        try{
            cloud = ImageIO.read(getClass().getResourceAsStream("/img/cloud2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        Random rand = new Random();
        y += speed;
        if (y > 1350){
            x = rand.nextInt(0,350);
            y = -50;   
        }
        
    }

}