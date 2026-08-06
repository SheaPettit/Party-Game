package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import players.*;

public class Client{
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Player player;
    private boolean isHost;
    private CLI cli;
    public Client() {
        socket = null;
        ois = null;
        oos = null;
        player = null;
        isHost = false;
        cli = new CLI(this);
    }
    public void Connect() throws IOException{
        System.out.println("Client is running...");
        try {
            socket = new Socket("localhost", 8080);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            boolean cont = true;
            Message message = null;
            Message fromServer = null;
            while(cont) {
                fromServer = (Message) ois.readObject();
                switch(fromServer.getType()) {
                    case MessageType.INVALID_NAME:
                        cli.invalidNameUI();
                    case MessageType.REQUEST_NAME:
                        String name = cli.requestNameUI();
                        message = new Message(MessageType.NAME_RESPONSE, name);
                        oos.writeObject(message);
                        break;
                    case MessageType.VALID_NAME:
                        cli.validNameUI();
                        cont = false;
                        player = fromServer.getPlayer();
                        isHost = player.isServerHost();
                        break;
                }
            }
            if(isHost) {
                String cmd = "";
                while("start".compareTo(cmd.toLowerCase()) != 0) {
                    cmd = cli.hostStartUI();
                }
                message = new Message(MessageType.START);
                oos.writeObject(message);
            }
            cont = true;
            while(cont) {
                fromServer = (Message) ois.readObject();
                if(fromServer.getType() != MessageType.START) continue;
                cli.gameStartUI();
                cont = false;
            }
            message = new Message(MessageType.LEAVE);
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (ois != null) {
                ois.close();
            }
            if (oos != null) {
                oos.close();
            }
            cli.cleanup();
        }
    }
}
