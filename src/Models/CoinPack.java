package Models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CoinPack extends GameObject {
    
    private String amount;
    private static BufferedImage coin = null;

    public CoinPack(int x, int y, String amount) {
        super(x, y);
        this.amount = "$ "+amount;   // amount is used only to draw. so it is kept as a string
        if (coin==null){
            try {
                coin = ImageIO.read(getClass().getResourceAsStream("/resources/coin.jpg"));
            } catch (IOException ex) {
                System.out.println("Error while the coin pack");
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        return coin;
    }
    
    public String getAmount(){
        return amount;
    }
    
}
