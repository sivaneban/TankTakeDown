
package Models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Brick extends GameObject{
    
    /* images to be diplayed on game window. these images are loded only ones. each brick will give corresponding image according to damage level.
       for 100% damage no image will be drawn. corresponding location will be coloured to the colour of window */
    private static BufferedImage brick0 = null;    // no damage
   
    
    public Brick(int x, int y) throws IOException {
        super(x, y);
        // load images        
        brick0 = ImageIO.read(getClass().getResourceAsStream("/resources/brick.jpg"));      
        
    }
    
    @Override
    public BufferedImage getImage() {
        // return image
        return brick0;
        }
    

  
    
}