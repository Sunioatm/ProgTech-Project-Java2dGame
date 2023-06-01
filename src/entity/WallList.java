
package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;
import main.GamePanel;

public class WallList extends Entity {
    static LinkedList<Walls> walllist = new LinkedList<>();
    Walls wall;
    GamePanel gp;
    Boss boss;
    
    public WallList(GamePanel gp){
        this.gp = gp;
        addWall(new Walls(gp,0,-250,7));
        addWall(new Walls(gp,193,-250,7));

        
    }
    public void draw(Graphics2D g2){
        for (int i = 0;i<walllist.size();i++){
            wall = walllist.get(i);
            wall.draw(g2);
        }


    }
    public void update(){
        for (int i = 0 ; i<walllist.size();i++){
            wall = walllist.get(i);
            wall.update();
        }

    }
    public void addWall(Walls wall){
        walllist.add(wall);
    }

    public static LinkedList<Walls> getEnemyList(){
        return walllist;
    } 
}
