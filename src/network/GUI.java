package network;

import javax.swing.*;
import question_answer.QAPair;

public class GUI extends AbstractUI {
    private JFrame mainFrame;
    public GUI(Client client) {
        super(client);
    }
    public String requestNameUI(){return null;}
    public void invalidNameUI(){}
    public void validNameUI(){}
    public String hostStartUI(){return null;}
    public void gameStartUI(){}
    public String qa1UI(){return null;}
    public void qa2UI(QAPair qaPair){}
    public void voteUI(QAPair qaPair){}
    public void voteResultUI(float percentage){}
    public void endUI(){}
    public void cleanup(){}
    public void requestAnswer(){}
    public void buildUI() {
        mainFrame = new JFrame("Party Game");
        mainFrame.setSize(750, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}

