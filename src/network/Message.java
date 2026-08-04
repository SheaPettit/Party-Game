package network;
import java.io.Serializable;
import players.*;

public class Message implements Serializable {
    private String response;
    private String playerName;
    private String data;
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

    public Message(MessageType type, Player player, boolean isHost) {
        this.type = type;
        this.player = player;
        this.isHost = isHost;
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

    public String getData() {
        return data;
    }

    public MessageType getType() {
        return type;
    }

    public boolean isHost() {
        return isHost;
    }
}
