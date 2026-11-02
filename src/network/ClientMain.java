package network;

import java.io.IOException;

public class ClientMain {
    private static String serverAddress = "localhost";
    public static void main(String[] args) {
        UIType uiType;
        if(args.length < 1)
            uiType = UIType.GUI;
        else if(args[0].toLowerCase().compareTo("cli") == 0)
            uiType = UIType.CLI;
        else if(args[0].toLowerCase().compareTo("webui") == 0)
            uiType = UIType.WebUI;
        else
            uiType = UIType.GUI;
        if(args.length > 1)
            serverAddress = args[1];
        Client client = new Client(uiType, serverAddress);
        try {
            client.Connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
