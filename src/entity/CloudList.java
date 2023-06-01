package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;
import main.GamePanel;

public class CloudList extends Entity {
    LinkedList<Clouds> cloudlist = new LinkedList<>();
    Clouds cloud;
    GamePanel gp;
    
    public CloudList(GamePanel gp){
        this.gp = gp;
        addCloud(new Clouds(gp,3,-50,3));
        addCloud(new Clouds(gp,100,-300,4));
        addCloud(new Clouds(gp,350,-100,2));
        addCloud(new Clouds(gp,275,-100,3));
        addCloud(new Clouds(gp,75,-500,1));

    }
    
    public void draw(Graphics2D g2){
        for (int i = 0;i<cloudlist.size();i++){
            cloud = cloudlist.get(i);
            cloud.draw(g2);
        }

    }
    public void update(){
        for (int i = 0 ; i<cloudlist.size();i++){
            cloud = cloudlist.get(i);
            cloud.update();
        }
    }
    
    public void addCloud(Clouds cloud){
        cloudlist.add(cloud);
    }
        
}
