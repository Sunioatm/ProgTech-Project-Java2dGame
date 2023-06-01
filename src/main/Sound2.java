package main;

import java.io.File;
import jaco.mp3.player.MP3Player;


public class Sound2 {
    File soundURL[] = new File[10];
    
    public Sound2() {

        soundURL[0] = new File("./src/sound/oof3.mp3");
        soundURL[1] = new File("./src/sound/reload3.mp3");
        soundURL[2] = new File("./src/sound/shoot3.mp3");
        soundURL[3] = new File("./src/sound/bossdeath2.mp3");
        soundURL[4] = new File("./src/sound/omaewa2.mp3");
        soundURL[5] = new File("./src/sound/maintheme3.mp3");
        soundURL[6] = new File("./src/sound/wrong2.mp3");

    }
    public void play(int i){
        new MP3Player(soundURL[i]).play();
    }


}
