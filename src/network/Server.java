package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import players.*;

public class Server{
    private ServerSocket serverSocket;
    private GameThread gameThread;
    private HashMap<Player, ClientHandler> playerToClient;
    public Server() {
        System.out.println("Server is running on port 8080...");
        gameThread = new GameThread(this);
        serverSocket = null;
        playerToClient = new HashMap<>();
    }
    public void start() throws IOException{
        serverSocket = new ServerSocket(8080);
        while (true) {
            Socket cs = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(cs, this);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
    public boolean addPlayer(String playerName, ClientHandler fromClient) {
        boolean success = gameThread.addPlayer(playerName);
        if (success) {
            Player player = gameThread.getPlayerByName(playerName);
            playerToClient.put(player, fromClient);
        }
        return success;
    }
    public Player getPlayerByName(String playerName) {
        return gameThread.getPlayerByName(playerName);
    }
    public void sendToAllClients(Message message) {
        for (ClientHandler clientHandler : playerToClient.values()) {
            try {
                clientHandler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
    

