package network;

import java.util.Scanner;
import question_answer.*;

@Deprecated
public class CLI extends AbstractUI {
    private final Scanner scan;
    private final CLIInputThread inputThread;

    public CLI(Client client) {
        super(client);
        scan = new Scanner(System.in);
        inputThread = new CLIInputThread(this);
        new Thread(inputThread).start();
    }

    public void requestNameUI() {
    }

    public void invalidNameUI() {
        System.out.println("Invalid name. Please try again.");
    }

    public void validNameUI() {
        System.out.println("Valid name. Welcome to the game!");
    }

    public void hostStartUI() {
    }

    public void gameStartUI() {
        System.out.println("Game started!");
    }

    public void qa1UI() {
    }

    public void qa2UI(QAPair qaPair) {
    }

    public void voteUI(QAPair qaPair) {
    }

    public void voteResultUI(float percentage) {
    }

    public void endUI() {
    }

    public void cleanup() {
    }

    public void sendInputToClient(String input) {
        client.inputReady(input);
        inputThread.resetInput();
    }
    
    public void requestAnswer() {
       client.inputReady(inputThread.requestInput());
    }
    public void buildUI() {
    }
}
