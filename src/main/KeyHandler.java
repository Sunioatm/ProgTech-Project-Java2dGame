package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    public boolean isSpace = false;
    public boolean isShoot = false;
    public static boolean restart = false;
    public boolean rPressed = false;
    public static int amountShoot = 0;
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); 
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = true;
            isShoot = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            if (isSpace == false) {
                isSpace = true;
            } else {
                isSpace = false;
            }
        }

        if (code == KeyEvent.VK_R){
            rPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
            isShoot = false;
            amountShoot = 0;
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        
    }
    
}
