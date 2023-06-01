package entity;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Walls extends Entity{
    GamePanel gp;
    static int wc;

    private String[] allOperator = {"add","subtract","multiply","divide","add","add","add","add","multiply","multiply"};
    private String operator = "add";
    
//    public int wallcount = 0;
    public Walls(GamePanel gp,int thisx,int thisy,int thisspeed){
        this.gp = gp;
        x = thisx;
        y = thisy;
        speed = thisspeed;
        getImage();
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        String opSymbol = "+";
        
        g2.setFont(new Font("Arial Rounded MT",3,22));
        g2.drawImage(wall,x,y,192,50,null);
        
        if (operator.equals("add")){
            opSymbol = "+";
        } else if (operator.equals("subtract")){
            opSymbol = "-";
        } else if (operator.equals("multiply")){
            opSymbol = "x";
        } else if (operator.equals("divide")){
            opSymbol = "\u00F7";
        }
        g2.drawString(opSymbol+ " "+String.valueOf(wallRandom),x+60,y-30);
        
        if (spriteNum == 1){
            image = ammo;
        } 
        if (spriteNum == 2){
            image = ammo2;
        } 
        if (spriteNum == 3){
            image =  ammo3;
        }
        if (spriteNum == 4){
            image = ammo3;
        } 
        if (spriteNum == 5){
            image = ammo2;
        } 
        if (spriteNum == 6){
            image =  ammo;
        }
        
        
        g2.drawImage(image,x+15,y-63,50,50,null);

        
    }
    public void getImage(){
        try{
            wall = ImageIO.read(getClass().getResourceAsStream("/img/net2.png"));
            ammo = ImageIO.read(getClass().getResourceAsStream("/img/ammo2.png"));
            ammo2 = ImageIO.read(getClass().getResourceAsStream("/img/ammo3.png"));
            ammo3= ImageIO.read(getClass().getResourceAsStream("/img/ammo4.png"));
       
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        Random rand = new Random();
        y += speed;
        if (y < -200 ){
            y = -200;
            int randOp = rand.nextInt(allOperator.length);
            operator = allOperator[randOp];
            wallRandom = rand.nextInt(1,10);
        }
        if (y > 800){
            y = -200;
            int randOp = rand.nextInt(allOperator.length);
            operator = allOperator[randOp];
            if ((operator.equals("multiply") )){
                wallRandom = rand.nextInt(1,6);
            } else if (operator.equals("divide")) {
                wallRandom = rand.nextInt(1,5);
            } else {
                wallRandom = rand.nextInt(1,10);
            }
            wallcount++;
            wc = wallcount;
            System.out.println(wallcount);
        }
        if (y < -800){
            y = -1000;
        }
        if (wallcount > 14){
            y = -1000;
        }
        spriteCounter++;
        if (spriteCounter > 30){
            if (spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2){
                spriteNum = 3;
            } else if (spriteNum == 3){
                spriteNum = 4;
            } else if (spriteNum == 4){
                spriteNum = 5;
            } else if (spriteNum == 5){
                spriteNum = 6;
            } else if (spriteNum == 6){
                spriteNum =1;
            }
            spriteCounter = 0;
        }  
    }
    public Rectangle getWallHitbox(){
        return new Rectangle(x,y,192,5);
    }
    
    public int getRandom(){
        return wallRandom;
    }
    public String getOp(){
        return operator;
    }


}
