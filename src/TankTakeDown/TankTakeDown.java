package TankTakeDown;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TankTakeDown {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Exception while setting");
        }
        GameWindow gw = new GameWindow();
        gw.setLocationRelativeTo(null);
        gw.setVisible(true);
        NetworkHandler nw = NetworkHandler.getInstance();
        nw.setPort(7000);
        nw.setGameEngine(gw.getGameEngine());
        (new Thread(nw)).start();
    }
    
}

