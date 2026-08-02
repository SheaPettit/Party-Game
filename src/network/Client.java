package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) throws IOException{
        System.out.println("Client is running...");
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8080);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Connected Client to Server");
            System.out.println("Client Sent Message to Server");
            InputStream input = socket.getInputStream();
            Scanner read = new Scanner(input);
            System.out.print("Server Sent: " + read.nextLine());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
