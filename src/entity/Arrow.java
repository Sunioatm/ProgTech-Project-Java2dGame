package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.LinkedList;
import main.GamePanel;
import main.KeyHandler;


public class Arrow extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    LinkedList<Walls> walllist = WallList.getEnemyList();
    LinkedList<Enemy> enemylist = EnemyList.getEnemyList();
    LinkedList<Fireball> fireballlist = GetFireball.getFireballList();
    boolean islose = false;
    BufferedImage image = null;

    public Arrow(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getImage();
    }
    public void setDefaultValues(){
        x = 192;
        y = 500;
        speed = 6;
    }
    public void update(){
        if (keyH.upPressed){
            if (amount > 0){
                if (Walls.wc < 0){  
                    
                    // reload
                    
                    if (keyH.amountShoot < 30){              
                             if (keyH.amountShoot == 1){
                                gp.play(2);
                             }
                             keyH.amountShoot++;
                             if (Boss.bossHP > 0 ){
                                 amount -= 1;
                             }
                             if (keyH.amountShoot == 29){
                                gp.play(1);
                             }

                        if (x>50 && x < 270){
                            Boss.shootBoss();
                            Boss.getShot = true;
                        }
                        
                    }
                } 
            }
        }
        if (Walls.wc == -1 && amount <= 0 && Boss.bossHP > 0 ){
            if (islose == false){    
                gp.play(4);
                islose = true;
                speed = 0;
            }
        }

        if (keyH.leftPressed){
            x -= speed; 
        } if (keyH.rightPressed){
            x += speed;
        }
        if (x < -15){
            x = -15;
        }
        if (x>335){
            x = 335;
        }
        
        isWallCollision();
        isHit();
        isHitFireball();
               
        spriteCounter++;
        if (spriteCounter > 30){
            if (spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }        
        
    }
        
    public void draw(Graphics2D g2){
        
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial Rounded MT",3,22));
        g2.drawString("x "+String.valueOf(amount),182,45);
        g2.drawImage(ammo, 115+keyH.amountShoot ,0,70-(keyH.amountShoot*70/30), 74,null);
        
        g2.setFont(new Font("Arial Rounded MT",3,16));

        
        g2.drawString("Boss Coming", 30, 575);
        
        
        if (Walls.wc >= 0){
            g2.drawString(String.valueOf(Walls.wc)+"/15", 30, 600);
        }else {   
            g2.drawString("15/15", 30, 600);
        }
        
        if (spriteNum == 1){
            image = gun1;
        } 
        if (spriteNum == 2){
            image = gun2;
        }
        if (keyH.isShoot && Walls.wc == -1){
            image = gun3;
        }
          g2.drawImage(image, x, y, 60, 26, null);

        
    }
    public void getImage(){
        try{
            
            gun1 = ImageIO.read(getClass().getResourceAsStream("/img/gun3.png"));
            gun2 = ImageIO.read(getClass().getResourceAsStream("/img/gun4.png"));
            gun3 = ImageIO.read(getClass().getResourceAsStream("/img/gun5.png"));
            ammo = ImageIO.read(getClass().getResourceAsStream("/img/ammo2.png"));
                    
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public Rectangle getArrowHitbox(){
        return new Rectangle(x+10,y,45,6);
    }
    public void isWallCollision(){
        for(int i =0;i<walllist.size();i++){
            if (amount <= 0){
                amount = 0;
            }
            if (getArrowHitbox().intersects(walllist.get(i).getWallHitbox())){
                
                if (walllist.get(i).getOp().equals("add")){
                    amount += walllist.get(i).getRandom();
                    gp.play(1);

                }
                if (walllist.get(i).getOp().equals("subtract")){                
                    amount -= walllist.get(i).getRandom();
                    gp.play(6);
                }
                if (walllist.get(i).getOp().equals("multiply")){
                    gp.play(1);
                    amount *= walllist.get(i).getRandom();
                }
                if (walllist.get(i).getOp().equals("divide")){
                    amount /= walllist.get(i).getRandom();
                    gp.play(6);
                }                      
                break;
            }
        }
    }
    public void isHit(){
        for (int i =0;i<enemylist.size();i++){
            if (getArrowHitbox().intersects(enemylist.get(i).getEnemyHitbox())){
                gp.play(0);
                amount -= enemylist.get(i).getEnemyHP();
                if (amount <= 0){
                    amount = 0;
                }
                
            }
        }
    }
    public void isHitFireball(){
        for (int i = 0;i<fireballlist.size();i++){
            if (getArrowHitbox().intersects(fireballlist.get(i).getFireballHitbox())){
                y = 1000;
            }
        }
    }

}
