package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;
import main.GamePanel;

public class GetFireball {
    static LinkedList<Fireball> fireballlist = new LinkedList<>();
    Fireball fireball;
    GamePanel gp;
    
    public GetFireball(GamePanel gp){
        this.gp = gp;
        addFireball(new Fireball(gp));
        
    }   
    public void draw(Graphics2D g2){
        for (int i = 0;i<fireballlist.size();i++){
            fireball = fireballlist.get(i);
            fireball.draw(g2);
        }
    }
    public void update(){
        for (int i = 0;i<fireballlist.size();i++){
            fireball = fireballlist.get(i);
            fireball.update();
        }
    }
    public void addFireball(Fireball fireball){
        fireballlist.add(fireball);
    }
    public static LinkedList<Fireball> getFireballList(){
        return fireballlist;
    }
    
}
