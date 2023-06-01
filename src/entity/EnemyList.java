
package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;
import main.GamePanel;

public class EnemyList {
    static LinkedList<Enemy> enemylist = new LinkedList<>();
    Enemy enemy;
    GamePanel gp;
    
    public EnemyList(GamePanel gp){
        this.gp = gp;
        addEnemy(new Enemy(gp,50,-200));
        addEnemy(new Enemy(gp,200,-200));
        addEnemy(new Enemy(gp,200,-200));       
        
        addEnemy(new Enemy(gp,50,-200));
        addEnemy(new Enemy(gp,200,-200));
        addEnemy(new Enemy(gp,200,-200));     
        
        addEnemy(new Enemy(gp,50,-200));
        addEnemy(new Enemy(gp,200,-200));
        addEnemy(new Enemy(gp,200,-200));   
       
        addEnemy(new Enemy(gp,50,-200));
        addEnemy(new Enemy(gp,200,-200));
        addEnemy(new Enemy(gp,200,-200));         
        
        addEnemy(new Enemy(gp,50,-200));
        addEnemy(new Enemy(gp,200,-200));
        addEnemy(new Enemy(gp,200,-200));  
        
        
    }
    public void draw(Graphics2D g2){
        for (int i = 0;i<enemylist.size();i++){
            enemy = enemylist.get(i);
            enemy.draw(g2);
        }

    }
    public void update(){
        for (int i = 0 ; i<enemylist.size();i++){
            enemy = enemylist.get(i);
            enemy.update();
            if (Boss.bossHP <= 0){
                enemylist.remove();
            }
        }
    }
    public void addEnemy(Enemy enemy){
        enemylist.add(enemy);
    }

    public static LinkedList<Enemy> getEnemyList(){
        return enemylist;
    } 
}
