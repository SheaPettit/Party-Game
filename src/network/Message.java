package network;
import java.io.Serializable;
import players.*;

public class Message implements Serializable {
    private String response;
    private String playerName;
    private String userInput;
    private MessageType type;
    private Player player;
    private boolean isHost;

    public Message(MessageType type, String playerName) {
        this.type = type;
        this.playerName = playerName;
    }

    public Message(MessageType type) {
        this.type = type;
    }

    public Message(MessageType type, Player player) {
        this.type = type;
        this.player = player;
    }

    public Message(MessageType type, String PlayerName, String userInput) {
        this.type = type;
        this.playerName = PlayerName;
        this.userInput = userInput;
    }

    public String getResponse() {
        return response;
    }

    public Player getPlayer() {
        return player;
    }
    
    public String getPlayerName() {
        return playerName;
    }

    public String getUserInput() {
        return userInput;
    }

    public MessageType getType() {
        return type;
    }

    public boolean isHost() {
        return isHost;
    }
}
