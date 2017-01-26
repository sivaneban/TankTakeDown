
package Models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Stone extends GameObject{
    
    // image to be drawn on screen. load only once to reduce IO
    private static BufferedImage stone = null;

    public Stone(int x, int y) throws IOException {
        super(x, y);
        // load image
        if (stone==null){
            stone = ImageIO.read(getClass().getResourceAsStream("/resources/stones.jpg"));
        }
    }
    
    @Override
    public BufferedImage getImage() {
        return stone;
    }
    
}
