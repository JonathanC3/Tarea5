package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;
import jdk.nashorn.internal.ir.BreakNode;


public class ProducerShadow extends Tails {

    private Buffer sharedLocation;

    public ProducerShadow(Buffer shared, int x, int y) throws FileNotFoundException {
        super(x, y);
        this.sharedLocation = shared;
        setSprite();
    } // constructor

    private void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 6; i++) {
            sprite.add(new Image(new FileInputStream("src/assets/Shadow" + i + ".png")));
        }
    } // setSprite

    @Override
    public void run() {
        ArrayList<Image> sprite = super.getSprite();
        int stop = 1, image = 1, mov = -60;
        int r = ThreadLocalRandom.current().nextInt(3, 18);
        
        while (true) {
            
            try {
                this.sleep(r);
                if (mov >= -60 && stop == 0) {
                    mov -= 2;
                    if (image == 6) {
                        image = 3;
                    }
                    super.setImage(sprite.get(image));
                    image++;
                    if (mov == -60) {
                        stop = 1;
                        r = ThreadLocalRandom.current().nextInt(3, 18);
                        
                        image = 1;
                    }
                    super.setX(mov);
                } else if (mov <= 500 && stop == 1) {
                    mov += 2;
                    if (image == 3) {
                        image = 1;
                    }
                    super.setImage(sprite.get(image));
                    image++;
                    if (mov == 500) {
                        stop = 0;
                        super.setImage(sprite.get(0));
                        this.sharedLocation.set(image);
                        
                        image = 3;
                    }
                    super.setX(mov);
                }
            } catch (InterruptedException ex) {
            }
            
        } // while
       
    } // run
    

} // fin de la clase

