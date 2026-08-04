package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import players.*;

public class GameThread {
    private Server server;
    private HashMap<String, Player> players;
    private List<String> playerNames;
    public  GameThread(Server server) {
        this.server = server;
        this.players = new HashMap<>();
        this.playerNames = new ArrayList<>();
    }   
    public boolean addPlayer(String playerName) {
        if (playerNames.contains(playerName)) {
            return false;
        }
        Player newPlayer;
        if (players.isEmpty()) {
            newPlayer = new Player(playerName, true);
        } else {
            newPlayer = new Player(playerName, false);
        }
        players.put(playerName, newPlayer);
        playerNames.add(playerName);
        return true;
    }
    public Player getPlayerByName(String playerName) {
        return players.get(playerName);
    }
}
