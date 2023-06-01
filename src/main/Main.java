package main;
import javax.swing.JFrame;
import main.GamePanel;
import main.KeyHandler;
import main.Sound2;

public class Main{
    static GamePanel gamePanel;
    KeyHandler keyH = new KeyHandler();
    
    public static void main(String[] args){
            
        JFrame window = new JFrame();
                

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Gun Choose Number");
        
        gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
       
        gamePanel.startGameThread();
     
         
}
}
    
