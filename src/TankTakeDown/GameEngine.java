
package TankTakeDown;

import Models.*;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GameEngine {
    
    private boolean gameStarted;
    private boolean gameFinished;
    private boolean alive;
    private String myTank;
    private ArrayList<Brick> brickList;

    private ArrayList<Tank> tankList;
    private GameWindow gameWindow;
    private MapDisplayUnit[][] mapDisplay;
    
    public GameEngine(GameWindow gameWindow,MapDisplayUnit[][] mapDisplay){
        this.gameStarted = false;
        this.gameFinished = false;
        this.alive = false;
        brickList = new ArrayList<Brick>();
        tankList = new ArrayList<Tank>();
        this.gameWindow = gameWindow;
        this.mapDisplay = mapDisplay;
    }
    
    public synchronized void decode(String message){
        switch(message){
            // common messages
            case Command.REQUESTERROR:
                gameWindow.showStatus("Request Error");
                break;
            case Command.SERVERERROR:
                gameWindow.showStatus("Server Error");
                JOptionPane.showMessageDialog(gameWindow, "Oops! an error has occured in server.", "Server Error", JOptionPane.ERROR_MESSAGE);
                break;
            // messages recieved when cannot join game
            case Command.ALREADYADDED:
                gameWindow.showStatus("You has already joined the game");
                break;
            case Command.ALREADYSTARTED:
                gameWindow.showStatus("Game has started. Try agiain later.");
                JOptionPane.showMessageDialog(gameWindow, "The Game has already started. Please join to a new game later.", "Game Already Started", JOptionPane.INFORMATION_MESSAGE);
                break;
            case Command.PLAYERFULL:
                gameWindow.showStatus("Player limit reached. Try again later.");
                JOptionPane.showMessageDialog(gameWindow, "The maximum no of allowed players has joined.Please join the next new game","Player Full",JOptionPane.INFORMATION_MESSAGE);
                break;
            // responses for moveing
            case Command.CELLOCCUPIED:
                gameWindow.showStatus("Invalid move. Cell already is occupied");
                break;
            case Command.TOOQUICK:
                gameWindow.showStatus("Too quick for a move");
                break;
            case Command.INVALIDCELL:
                gameWindow.showStatus("The cell you try to move is invalid.");
                break;
            case Command.NOTYETSTARTED:
                gameWindow.showStatus("Game not yet started to send command.");
                break;
            case Command.ALREADYFINISH:
                gameWindow.showStatus("Cannot play further. Game has finished.");
                break;
            case Command.NOTACONTESTANT:
                gameWindow.showStatus("Not allowed. You have not joined the game.");
                break;
            case Command.DEAD:
                gameWindow.showStatus("You tank is smashed");
                break;
            case Command.JUSTFININSHED:
                gameWindow.showStatus("Game Over");
                break;
            case Command.FELLTOPIT:
                gameWindow.showStatus("Your tank fell into ocean and destroded by shark");
                JOptionPane.showMessageDialog(gameWindow,"Your tank fell into ocean and destroyed by shark","Player Fell",JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                // handle commands that need some string manupilation
                if (message.startsWith(Command.OBSTACLE)){
                    gameWindow.showStatus("You hit an obstacle. Penalty: "+message.substring(9, message.length()-1));
                }
                else if (message.startsWith("I")){
                    /* if message starts with I it contain map data. remove "I:" and "#" from message and call initializeMap to process message
                       though there are other messages startswith I, they will be checked in above switch cases  */
                    initializeMap(message.substring(2, message.length()-1));
                }
                else if(message.startsWith("S")){
                    /* similar to aboves condition, message that starts with S have player location and indicate game has begun. remoove unnecessary
                       parts of message and send to process*/
                    loadTanks(message.substring(2, message.length()-1));
                }
                else if (message.startsWith("L")) {
                    // process life pack information. the string contains cordinates, time out
                    handleLifePack(message.substring(2, message.length() - 1));
                    
                } else if (message.startsWith("C")) {
                    handleCoinPack(message.substring(2, message.length() - 1));
                }
                else if(message.startsWith("G")){
                    updateMapDisplay(message.substring(2, message.length()-1));
                }
        }
    }
    
    private void initializeMap(String details){
        try{
            gameWindow.showStatus("Loading map");
            /* locations of my player name,bricks, stones and water in the string respectively. They are seperated with a colon. elements array will have those 
               seperated but as strings. */
            String[] elements = details.split(":");
            myTank = elements[0];  // my player name. this is the first value in elements array
            /* process brick locations. elements[1] has brick locations each brick seperated with semi-colon and cordinates seperated with commas. bricks array
               will have x,y cordinates of bricks as string*/
            String[] bricks = elements[1].split("[;,]");
            for (int i=0; i<bricks.length; i+=2){   // since bricks array has both x,y cordinates i is incremented by 2
                Brick brick = new Brick(Integer.parseInt(bricks[i]), Integer.parseInt(bricks[i+1]));
                brickList.add(brick);
                mapDisplay[brick.getX()][brick.getY()].setGameObject(brick);
                mapDisplay[brick.getX()][brick.getY()].draw();
            }
            String[] stones = elements[2].split("[;,]");
            for (int i=0; i<stones.length; i+=2){   // since bricks array has both x,y cordinates i is incremented by 2
                Stone stone = new Stone(Integer.parseInt(stones[i]), Integer.parseInt(stones[i+1]));
                mapDisplay[stone.getX()][stone.getY()].setGameObject(stone);
                mapDisplay[stone.getX()][stone.getY()].draw();
            }
            String[] water = elements[3].split("[;,]");
            for (int i=0; i<water.length; i+=2){   // since bricks array has both x,y cordinates i is incremented by 2
                WaterPit waterPit = new WaterPit(Integer.parseInt(water[i]), Integer.parseInt(water[i+1]));
                mapDisplay[waterPit.getX()][waterPit.getY()].setGameObject(waterPit);
                mapDisplay[waterPit.getX()][waterPit.getY()].draw();
            }
            gameWindow.showStatus("Map loaded successfully");
        }catch(IOException e){
            System.out.println("IOException while loading the Map.");
            JOptionPane.showMessageDialog(gameWindow, "An error occured while loading the map","IOException",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadTanks(String details){
        int i =0;
        try{
        String[] players = details.split(":");
        for (String player:players){
            String[] playerDetails = player.split("[;,]");
            Tank tank = new Tank(Integer.parseInt(playerDetails[1]), Integer.parseInt(playerDetails[2]), playerDetails[0], Integer.parseInt(playerDetails[3]));
            tankList.add(tank);
            mapDisplay[tank.getX()][tank.getY()].setGameObject(tank);
            mapDisplay[tank.getX()][tank.getY()].draw();            
        }
        }catch(IOException e){
            System.out.println("IOException while loading image for tank.");
            JOptionPane.showMessageDialog(gameWindow, "An error occured while loading tanks. Cannot find an image","IOException",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleCoinPack(String details){
        String[] coinPack = details.split("[:,]");
        final int x = Integer.parseInt(coinPack[0]), y = Integer.parseInt(coinPack[1]); // need to be accesed by the thread below. hence final
        final long timeOut = Long.parseLong(coinPack[2]);
        CoinPack coin = new CoinPack(x,y,coinPack[3]);
        mapDisplay[x][y].setGameObject(coin);
        mapDisplay[x][y].setCoin(true);
        mapDisplay[x][y].draw();
        final MapDisplayUnit[][] map = mapDisplay;      // need by the thread below
        new Thread(){       // thread that will remove coin from map if it didn't collected by any player before time out
            private int cor_x,cor_y;
            MapDisplayUnit[][] map;
            @Override
            public void run(){
                try {
                    cor_x = x;
                    cor_y = y;
                    map = mapDisplay;
                    sleep(timeOut);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
    
    private void handleLifePack(String details){
        String[] lifePack = details.split("[:,]");
        final int x = Integer.parseInt(lifePack[0]), y = Integer.parseInt(lifePack[1]);
        final long timeOut = Long.parseLong(lifePack[2]);
        LifePack life = new LifePack(x,y);
        mapDisplay[x][y].setGameObject(life);
        mapDisplay[x][y].setLife(true);
        mapDisplay[x][y].draw();
        final MapDisplayUnit[][] map = mapDisplay;
        new Thread(){
            private int cor_x,cor_y;
            MapDisplayUnit[][] map;
            @Override
            public void run(){
                try {
                    cor_x = x;
                    cor_y = y;
                    map = mapDisplay;
                    sleep(timeOut);
                                   } catch (InterruptedException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
    
    private void updateMapDisplay(String details){
        String[] mapUpdate = details.split(":");
        for (int i=0; i<mapUpdate.length-1;i++){
            String[] playerDetails = mapUpdate[i].split("[;,]");
            Tank tank = tankList.get(i);
            mapDisplay[tank.getX()][tank.getY()].setGameObject(null);
            mapDisplay[tank.getX()][tank.getY()].draw();
            tank.setX(Integer.parseInt(playerDetails[1]));
            tank.setY(Integer.parseInt(playerDetails[2]));
            tank.setDirection(Integer.parseInt(playerDetails[3]));
            mapDisplay[tank.getX()][tank.getY()].setGameObject(tank);
            mapDisplay[tank.getX()][tank.getY()].draw();
        }
        String[] brickDetails = mapUpdate[mapUpdate.length - 1].split("[;,]");
        for (int i = 0; i < brickDetails.length; i += 3) {
            Brick brick = brickList.get(i/3);
            mapDisplay[brick.getX()][brick.getY()].draw();
        }
    }
    
    public boolean isGameStarted(){
        return gameStarted;
    }
    
    public void setGameStarted(boolean gameStarted){
        this.gameStarted = gameStarted;
    }
    
    public boolean isGameFinished(){
        return gameFinished;
    }
    
    public void setGameFinsished(boolean gameFinished){
        this.gameFinished = gameFinished;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
}
