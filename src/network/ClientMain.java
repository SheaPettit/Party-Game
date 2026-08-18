package network;

import java.io.IOException;

public class ClientMain {
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
        Client client = new Client(uiType);
        try {
            client.Connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
