package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class Emerald extends Tails {

    private SynchronizedBuffer Buffer;
    
    public Emerald(SynchronizedBuffer shared, int x, int y) throws FileNotFoundException {
        super(x, y);
        this.Buffer = shared;
        setSprite();
    } // constructor

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        sprite.add(new Image(new FileInputStream("src/assets/emeralds.png")));
    } // setSprite

    @Override
    public void run() {
        Image image = super.getSprite().get(0);
        
        while (true) {
            try {
                sleep(10);
                if (Buffer.paint()==1) {
                    super.setImage(image);
                    setX(557);
                    setY(445);
                } else {
                    sleep(10);
                    super.setImage(image);
                    setX(1600);
                    setY(445);
                   
                }
            
            
            } catch (InterruptedException ex) {
                Logger.getLogger(Emerald.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } // while
    } //  run
    
    
} // fin de la clase
