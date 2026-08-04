package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import players.*;

public class Server{
    private ServerSocket serverSocket;
    private GameThread gameThread;
    public Server() {
        System.out.println("Server is running on port 8080...");
        gameThread = new GameThread(this);
        serverSocket = null;
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
    public boolean addPlayer(String playerName) {
        return gameThread.addPlayer(playerName);
    }
    public Player getPlayerByName(String playerName) {
        return gameThread.getPlayerByName(playerName);
    }
}
    

