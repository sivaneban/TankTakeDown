package TankTakeDown;

/**
 *
 * @author Siva
 */
public class Command {
    // Commands sent to server
    public static final String JOIN = "JOIN#";
    public static final String UP = "UP#";
    public static final String DOWN = "DOWN#";
    public static final String LEFT = "LEFT#";
    public static final String RIGHT = "RIGHT#";
    public static final String SHOOT = "SHOOT#";
    // Messages sent by the server for the request JOIN
    public static final String PLAYERFULL = "PLAYERS_FULL#";
    public static final String ALREADYADDED = "ALREADY_ADDED#";
    public static final String ALREADYSTARTED = "GAME_ALREADY_STARTED#";
    // Messsages sent as response for move commands
    public static final String INVALIDCELL = "INVALID_CELL#";
    public static final String NOTACONTESTANT = "NOT_A_VALID_CONTESTANT#";
    public static final String TOOQUICK = "TOO_QUICK#";
    public static final String CELLOCCUPIED = "CELL_OCCUPIED#";
    public static final String OBSTACLE = "OBSTACLE";  
    public static final String FELLTOPIT = "PITFALL#";
    public static final String DEAD = "DEAD#";
    public static final String NOTYETSTARTED = "GAME_NOT_STARTED_YET#";
    public static final String ALREADYFINISH = "GAME_HAS_FINISHED#";
    public static final String JUSTFININSHED = "GAME_FINISHED#";
    public static final String REQUESTERROR = "REQUEST_ERROR#";
    public static final String SERVERERROR = "SERVER_ERROR#";

}
