package players;
import java.io.Serializable;
import question_answer.*;
import rounds.*;

public class Message implements Serializable {
    private String message;
    private String player;

    public Message(String message, String player) {
        this.message = message;
        this.player = player;
    }

    public String getMessage() {
        return message;
    }

    public String getPlayer() {
        return player;
    }
    
}
