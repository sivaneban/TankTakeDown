/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Notebook 15
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import TankTakeDown.MapDisplayUnit;

public class Bullet extends GameObject implements Runnable {
    
    private static BufferedImage bullet0 = null;
    private static BufferedImage bullet1 = null;
    private static BufferedImage bullet2 = null;
    private static BufferedImage bullet3 = null;
    
    private int directoin;
    private MapDisplayUnit[][] map;

    public Bullet(int x, int y,int direction,MapDisplayUnit[][] map) throws IOException {
        super(x, y);
        this.directoin = direction;
        this.map = map;
        // load images
        if (bullet0==null){
            bullet0 = ImageIO.read(getClass().getResourceAsStream("/resources/bullet0.jpg"));
        }
        if (bullet1==null){
            bullet1 = ImageIO.read(getClass().getResourceAsStream("/resources/bullet1.jpg"));
        }
        if (bullet2==null){
            bullet2 = ImageIO.read(getClass().getResourceAsStream("/resources/bullet2.jpg"));
        }
        if (bullet3==null){
            bullet3 = ImageIO.read(getClass().getResourceAsStream("/resources/bullet3.jpg"));
        }
    }
    
    @Override
    public BufferedImage getImage() {
        // return image to be drawed according to directon of bullet
        switch(directoin){
            case 0:
                return bullet0;
            case 1:
                return bullet1;
            case 2:
                return bullet2;
            case 3:
                return bullet3;
            default:
                return null;
        }
    }
    
    public int getNextX(){
        switch(directoin){
            case 0:
                return getX();
            case 1:
                return getX()+1;
            case 2:
                return getX();
            default:
                return getX()-1;
        }
    }
    
    public int getNextY(){
        switch(directoin){
            case 0:
                return getY()-1;
            case 1:
                return getY();
            case 2:
                return getY()+1;
            default:
                return getY();
        }
    }

    @Override
    public void run() {
        while(getX()>=0 && getY()<10 && getY()>=0 && getY()<10){
            try {
                sleep(300);
                map[getX()][getY()].draw();
                setX(getNextX());
                setY(getNextY());
                map[getX()][getY()].draw(5,15,getImage());
            } catch (InterruptedException ex){}
        }
    }
    
}
