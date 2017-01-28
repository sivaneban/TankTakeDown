
package Models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Brick extends GameObject{
    
     /* images to be diplayed on game window. these images are loded only ones. each brick will give corresponding image according to damage level.
       for 100% damage no image will be drawn. corresponding location will be coloured to the colour of window */
    private static BufferedImage brick0 = null;    // no damage
    private static BufferedImage brick1 = null;    // 25% damage
    private static BufferedImage brick2 = null;    // 50% damage
    private static BufferedImage brick3= null;    // 75% damage
    
    private int damageLevel;
    
    public Brick(int x, int y, int damageLevel) throws IOException {
        super(x, y);
        this.damageLevel = damageLevel;
        // load images
        if (brick0==null){
            brick0 = ImageIO.read(getClass().getResourceAsStream("/resources/brick100.jpg"));
        }
        if (brick1==null){
            brick1 = ImageIO.read(getClass().getResourceAsStream("/resources/brick75.jpg"));
        }
        if (brick2==null){
            brick2 = ImageIO.read(getClass().getResourceAsStream("/resources/brick50.jpg"));
        }
        if (brick3==null){
            brick3 = ImageIO.read(getClass().getResourceAsStream("/resources/brick25.jpg"));
        }
    }
    
    @Override
    public BufferedImage getImage() {
        // return image to be drawed according to damage level
        switch(damageLevel){
            case 0:
                return brick0;
            case 1:
                return brick1;
            case 2:
                return brick2;
            case 3:
                return brick3;
            default:
                return null;
        }
    }

    public void setDamageLevel(int damageLevel) {
        this.damageLevel = damageLevel;
    }  
}