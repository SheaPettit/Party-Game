package network;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.Connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
