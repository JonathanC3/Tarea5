package Domain;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Tails extends Thread{

    private int x;
    private int y;
    private Image image;
    private ArrayList<Image> sprite;
   

    public Tails(int x, int y) {
        this.x = x;
        this.y = y;
        this.sprite = new ArrayList<Image>();
    } // constructor

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList<Image> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<Image> sprite) {
        this.sprite = sprite;
    }
        
    
} // fin de tails
