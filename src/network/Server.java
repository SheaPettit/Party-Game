package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args) throws IOException {
        
        System.out.println("Server is running on port 8080...");
        ServerSocket socket = new ServerSocket(8080);
        while (true) {
            Socket cs = socket.accept();
            ClientHandler clientHandler = new ClientHandler(cs);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
    
}
