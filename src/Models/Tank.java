
package Models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tank extends GameObject {

    // Each tank has different images. So these images are not made static like others. These too load only once
    private BufferedImage up;
    private BufferedImage right;
    private BufferedImage down;
    private BufferedImage left;
    private int health;
    private int points;
    private int direction;
    private String name;

    public Tank(int x, int y, String name, int direction) throws IOException {
        super(x, y);
        this.name = name;
        this.direction = direction;
        // load images
        up = ImageIO.read(getClass().getResourceAsStream("/resources/"+name+"0.jpg"));
        right = ImageIO.read(getClass().getResourceAsStream("/resources/"+name+"1.jpg"));
        down = ImageIO.read(getClass().getResourceAsStream("/resources/"+name+"2.jpg"));
        left = ImageIO.read(getClass().getResourceAsStream("/resources/"+name+"3.jpg"));
    }

    @Override
    public BufferedImage getImage() {
        // return image to be drawen according to direction of tank
        switch(direction){
            case 0:
                return up;
            case 1:
                return right;
            case 2:
                return down;
            case 3:
                return left;
            default:
                return null;
        }
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoints() {
        return points;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
}
