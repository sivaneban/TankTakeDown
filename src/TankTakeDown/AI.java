/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankTakeDown;

import java.io.IOException;

/**
 *
 * @author Notebook 15
 */
public class AI {

   private GameEngine gameEngine;
     public void collectCoin(String ip,int port) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message=Command.RIGHT;
                          try {
                              if (gameEngine.isGameStarted()==true){
                                NetworkHandler.getInstance().send(ip, port, message);
                              }
                        //NetworkHandler.getInstance().send(ip, port, message);
                } catch (IOException ex) {
                    System.out.println("Commmand "+message+" not sent");
                }
                        Thread.sleep(1100);                             
                                                    
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }
}
