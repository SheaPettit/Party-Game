package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import players.*;

public class Client{
    public static void main(String[] args) throws IOException{
        System.out.println("Client is running...");
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Scanner scan = null;
        Player player = null;
        boolean isHost = false;
        try {
            socket = new Socket("localhost", 8080);
            OutputStream output = socket.getOutputStream();
            oos = new ObjectOutputStream(output);
            InputStream input = socket.getInputStream();
            ois = new ObjectInputStream(input);
            boolean cont = true;
            Message message = null;
            Message fromServer = null;
            scan = new Scanner(System.in);
            while(cont) {
                fromServer = (Message) ois.readObject();
                switch(fromServer.getType()) {
                    case MessageType.INVALID_NAME:
                        System.out.println("Invalid name. Please try again.");
                    case MessageType.REQUEST_NAME:
                        System.out.print("Enter name: ");
                        String name = scan.nextLine();
                        message = new Message(MessageType.NAME_RESPONSE, name);
                        oos.writeObject(message);
                        break;
                    case MessageType.VALID_NAME:
                        System.out.println("Valid name. Welcome to the game!");
                        cont = false;
                        player = fromServer.getPlayer();
                        isHost = player.isServerHost();
                        break;
                }
            }
            if(isHost) {
                String cmd = "";
                while("start".compareTo(cmd.toLowerCase()) != 0) {
                    System.out.print("Type 'start' to start the game: ");
                    cmd = scan.nextLine();
                }
                message = new Message(MessageType.START);
                oos.writeObject(message);
            }
            cont = true;
            while(cont) {
                fromServer = (Message) ois.readObject();
                if(fromServer.getType() != MessageType.START) continue;
                System.out.println("Game started!");
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
            if (scan != null) {
                scan.close();
            }
        }
    }
}
