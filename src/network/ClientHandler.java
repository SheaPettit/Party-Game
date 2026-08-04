package network;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import players.Player;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private Server server;
    private Player player;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            InputStream input = clientSocket.getInputStream();
            ois = new ObjectInputStream(input);
            OutputStream output = clientSocket.getOutputStream();
            oos = new ObjectOutputStream(output);
            Message message = new Message(MessageType.REQUEST_NAME);
            oos.writeObject(message);
            boolean cont = true;
            while(cont) {
                Message fromClient = (Message) ois.readObject();
                switch (fromClient.getType()) {
                    case NAME_RESPONSE:
                        String playerName = fromClient.getPlayerName();
                        if(server.addPlayer(playerName)) {
                            message = new Message(MessageType.VALID_NAME);
                            oos.writeObject(message);
                            cont = false;
                            this.player = server.getPlayerByName(playerName);
                        } else {      
                            message = new Message(MessageType.INVALID_NAME);
                            oos.writeObject(message);
                        }
                        if(message.getType() == MessageType.VALID_NAME) {
                            System.out.println("Player " + playerName + " has joined the game.");
                        } else {
                            System.out.println("Player " + playerName + " attempted to join with an invalid name.");
                        }
                        break;
                    case START:
                        System.out.println("Player " + player.getName() + " is attempting to start the game.");
                        if(player.isServerHost()) {
                            message = new Message(MessageType.START);
                            oos.writeObject(message);
                            System.out.println("Game started by host " + player.getName() + ".");
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
    