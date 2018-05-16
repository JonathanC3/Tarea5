package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class ConsumerTail extends Tails {

    private SynchronizedBuffer sharedLocation;

    public ConsumerTail(SynchronizedBuffer shared, int x, int y) throws FileNotFoundException {
        super(x, y);
        this.sharedLocation = shared;
        setSprite();
    } // constructor

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i <7; i++) {
            sprite.add(new Image(new FileInputStream("src/assets/Tails" + i + ".png")));
        }
    } // setSprite

    @Override
    public void run() {
        ArrayList<Image> sprite = super.getSprite();
        int mov = 1250;
        int stop = 0, s = 0, image = 1;
        int r = ThreadLocalRandom.current().nextInt(3, 18);
        
        while (true) {
            try {
                this.sleep(r);
                super.setX(mov);
                if (mov >= 570 && stop == 0) {
                    mov -= 1;
                    if (image == 4) {
                        image = 1;
                    }
                    super.setImage(sprite.get(image));
                    image++;
                    if (mov == 570) {
                        stop = 1;
                        super.setImage(sprite.get(0));
                        //System.out.println(this.sharedLocation.get());
                        s += this.sharedLocation.get();
                        //no pinta hasta que el otro thread llegue
                        this.sharedLocation.setPaint(0);
                        image = 4;
                    }
                } else if (mov <= 1250 && stop == 1) {
                    mov += 1;
                    if (image == 7) {
                        image = 4;
                    }
                    super.setImage(sprite.get(image));
                    image++;
                    if (mov == 1200) {
                        stop = 0;
                        r = ThreadLocalRandom.current().nextInt(3, 18);
                        
                        image = 1;
                    }
                }
            
            } catch (InterruptedException ex) {
            }
            
        }// while
        
    } //  run

} // fin de la clase
