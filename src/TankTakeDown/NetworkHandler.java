/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankTakeDown;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siva
 */



public class NetworkHandler implements Runnable {
    
    private ServerSocket server;
    private int port;
    private GameEngine gameEngine;
    private static NetworkHandler networkHandler;
    
    private NetworkHandler(){
        networkHandler = null;
        
    }
    
    public static NetworkHandler getInstance(){
        if (networkHandler==null){
            synchronized(NetworkHandler.class){
                if (networkHandler==null)
                    networkHandler = new NetworkHandler();
                    return networkHandler;
            }
        }
        return networkHandler;
    }
    
    private void recieve(){
        System.out.println("calling");
        int i = 0;
        while (true) {
            i++;
            try {
                System.out.println("calling server: "+i);
                Socket socket = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String reply = in.readLine();
                System.out.println("Message from server: "+reply);
                socket.close();
                new Thread(){
                    @Override
                    public void run(){
                        gameEngine.decode(reply);
                    }
                }.start();
            } catch (IOException ex) {
                System.out.println("IOException in reciever");
            }
        }
    }

    public synchronized void send(String ipAddress, int port, String message) throws IOException{
            Socket socket = new Socket(ipAddress, port);
            DataOutputStream d = new DataOutputStream(socket.getOutputStream());
            d.writeBytes(message);
            socket.close();
            System.out.println("Request sent: "+message);
    }
    
    @Override
    public void run() {
        try {
            this.server = new ServerSocket(this.port);
            System.out.println(this.port);
            recieve();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    
}

