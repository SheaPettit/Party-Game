package network;
import java.io.Serializable;
import java.util.HashMap;
import players.*;
import question_answer.QAPair;
import question_answer.RoundsType;

public class Message implements Serializable {
    private String response;
    private String playerName;
    private String otherPlayerName;
    private String userInput;
    private MessageType type;
    private RoundsType roundType;
    private Player player;
    private boolean isHost;
    private QAPair qaPair;
    private HashMap<String, Integer> voteScores;

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

    public Message(MessageType type, RoundsType roundType) {
        this.type = type;
        this.roundType = roundType;
    }

    public Message(MessageType type, String PlayerName, String userInput) {
        this.type = type;
        this.playerName = PlayerName;
        this.userInput = userInput;
    }

    public Message(MessageType type, String playerName, String otherPlayerName, QAPair qaPair) {
        this.type = type;
        this.playerName = playerName;
        this.otherPlayerName = otherPlayerName;
        this.qaPair = qaPair;
    }

    public Message(MessageType type, HashMap<String, Integer> voteScores) {
        this.type = type;
        this.voteScores = voteScores;
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

    public QAPair getQAPair() {
        return qaPair;
    }

    public HashMap<String, Integer> getVoteScores() {
        return voteScores;
    }
    
    public String getOtherPlayerName() {
        return otherPlayerName;
    }
    
    public RoundsType getRoundType() {
        return roundType;
    }
}
