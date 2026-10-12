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
    private UserInterface ui;
    private volatile boolean userInputReady;
    private String input;
    public Client(UIType uiType) {
        socket = null;
        ois = null;
        oos = null;
        player = null;
        isHost = false;
        switch(uiType) {
            case CLI:
                /*ui = new CLI(this);
                break;*/
            case WebUI:
                /*ui = new WebUI(this);
                break;*/
            case GUI:
            default:
                ui = new GUI(this);
                break;
        }
        input = "";
    }
    public void Connect() throws IOException{
        System.out.println("Client is running...");
        ui.buildUI();
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
                        ui.invalidNameUI();
                    case MessageType.REQUEST_NAME:
                        ui.requestNameUI();
                        while(!input.isEmpty()){Thread.sleep(10);}
                        message = new Message(MessageType.NAME_RESPONSE, input);
                        oos.writeObject(message);
                        break;
                    case MessageType.VALID_NAME:
                        ui.validNameUI();
                        cont = false;
                        player = fromServer.getPlayer();
                        isHost = player.isServerHost();
                        break;
                }
            }
            if(isHost) {
                while("start".compareTo(input.toLowerCase()) != 0) {
                    ui.hostStartUI();
                }
                message = new Message(MessageType.START);
                oos.writeObject(message);
            }
            cont = true;
            while(cont) {
                fromServer = (Message) ois.readObject();
                if(fromServer.getType() != MessageType.START) continue;
                ui.gameStartUI();
                cont = false;
            }
            ui.qa1UI();
            userInputReady = false;
            while(!userInputReady) {
                Thread.sleep(100);
                if(ois.available() > 0) {
                    fromServer = (Message) ois.readObject();
                    if(fromServer.getType() == MessageType.SUBMIT_QUESTION)
                        ui.requestAnswer();
                }
            }
            message = new Message(MessageType.SEND_QUESTION, player.getName(), input);
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
            ui.cleanup();
        }
    }
    public void inputReady(String input) {
        this.input = input;
        this.userInputReady = true;
    }
}
