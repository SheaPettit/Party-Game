package network;

import java.io.IOException;
import java.io.InputStreamReader;

@Deprecated
public class CLIInputThread implements Runnable {
    private final CLI cli;
    private final InputStreamReader inputReader;
    private String input;
    private boolean cont;

    public CLIInputThread(CLI cli) {
        this.cli = cli;
        this.inputReader = new InputStreamReader(System.in);
        input = "";
        cont = true;
    }

    @Override
    public void run() {
        while (cont) {
            int nextAscii;
            try {
                nextAscii = inputReader.read();
                if (nextAscii == -1) {
                    continue;
                }
                char nextChar = (char) nextAscii;
                if (nextChar == '\n') {
                    cli.sendInputToClient(input);
                } else if (nextAscii == 8 || nextAscii == 127) {
                    if(!input.isEmpty()) {
                        input = input.substring(0, input.length() - 1);
                    }
                }else {
                    input += nextChar;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetInput() {
        input = "";
    }

    public String requestInput() {
        String in = input;
        resetInput();
        return in;
    }

    public void stop() {
        cont = false;
    }
}
