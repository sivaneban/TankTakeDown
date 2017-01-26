package Models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WaterPit extends GameObject{
    
    // image to be drawn at location of water pits. loaded only once
    private static BufferedImage waterPit = null;

    public WaterPit(int x, int y) throws IOException {
        super(x, y);
        // load image
        if (waterPit==null){
            waterPit = ImageIO.read(getClass().getResourceAsStream("/resources/waterpit.jpg"));
        }
    }
    
    @Override
    public BufferedImage getImage() {
        return waterPit;
    }
    
}
