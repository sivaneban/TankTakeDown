
package Models;

import java.awt.image.BufferedImage;
//By Siva
public abstract class GameObject {
    
    private int x;
    private int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract BufferedImage getImage();

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

}
