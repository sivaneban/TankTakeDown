
package Models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LifePack extends GameObject{
    
    private static BufferedImage lifePack;

    public LifePack(int x, int y) {
        super(x, y);
        if (lifePack==null){
            try {
                lifePack = ImageIO.read(getClass().getResourceAsStream("/resources/life.jpg"));
            } catch (IOException ex) {
                System.out.println("Error while loading the life pack");
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        return lifePack;
    }
    
}