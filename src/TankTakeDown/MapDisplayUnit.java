
package TankTakeDown;

import Models.CoinPack;
import Models.GameObject;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class MapDisplayUnit extends Canvas{

    private GameObject gameObject = null;
    private boolean coin;
    private boolean life;
    
    public MapDisplayUnit() {
        this.setIgnoreRepaint(true);
    }

    public synchronized void draw() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        
        BufferedImage bi = null;
        if (gameObject!=null){
            bi = gameObject.getImage();
        }
        if (gameObject==null || bi==null){
            g.setColor(new Color(240,240,240));
            g.fillRect(0, 0, 50, 50);
        }else{
            g.drawImage(bi, 0, 0, this);
        }
        if (gameObject instanceof CoinPack){
            CoinPack gameObj = (CoinPack)gameObject;
            g.setFont(new Font("Serif",Font.BOLD,14));
            g.drawString(gameObj.getAmount(), 10, 35);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void draw(int x, int y, BufferedImage bi){
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(bi,x,y,this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public GameObject getGameObject(){
        return gameObject;
    }
    
    public void setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
    }

    public boolean hasCoin() {
        return coin;
    }

    public void setCoin(boolean coin) {
        this.coin = coin;
    }

    public boolean hasLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }
    
    
    
}
