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
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean connected;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.connected = true;
    }

    @Override
    public void run() {
        System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
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
                        if(server.addPlayer(playerName, this)) {
                            message = new Message(MessageType.VALID_NAME, server.getPlayerByName(playerName));
                            oos.writeObject(message);
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
                            server.sendToAllClients(message);
                            System.out.println("Game started by host " + player.getName() + ".");
                        }
                        break;
                    case SEND_QUESTION:
                        String question = fromClient.getUserInput();
                        System.out.println("Player " + player.getName() + " submitted a question: " + question);
                        message = new Message(MessageType.SEND_QUESTION, question);
                        server.sendToAllClients(message);
                        break;
                    case LEAVE:
                        System.out.println("Player " + player.getName() + " has disconnected.");
                        cont = false;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.connected = false;
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMessage(Message message) {
        if(!connected) {
            System.out.println("Client is not connected. Cannot send message.");
            return;
        }
        try {
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    