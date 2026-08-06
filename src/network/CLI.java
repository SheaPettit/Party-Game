package network;

import java.util.Scanner;
import question_answer.*;

public class CLI extends AbstractUI {
    private final Scanner scan;

    public CLI(Client client) {
        super(client);
        scan = new Scanner(System.in);
    }

    public String requestNameUI() {
        System.out.print("Enter name: ");
        return scan.nextLine();
    }

    public void invalidNameUI() {
        System.out.println("Invalid name. Please try again.");
    }

    public void validNameUI() {
        System.out.println("Valid name. Welcome to the game!");
    }

    public String hostStartUI() {
        System.out.print("Type 'start' to start the game: ");
        return scan.nextLine();
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
}
