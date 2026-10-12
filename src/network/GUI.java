package network;

import java.awt.*;
import javax.swing.*;
import question_answer.QAPair;

public class GUI extends AbstractUI {
    private JFrame mainFrame;
    public GUI(Client client) {
        super(client);
    }
    public void requestNameUI(){
        /*JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("Name");
        JTextField text = new JTextField("Enter Name...");
        JButton buttton = new JButton("Done");*/
    }
    public void invalidNameUI(){}
    public void validNameUI(){}
    public void hostStartUI(){}
    public void gameStartUI(){}
    public void qa1UI(){}
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
        addStartFrame();
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    private void addStartFrame() {
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        JLabel startText = new JLabel("Press 'START' to join the game");
        startPanel.add(startText, BorderLayout.NORTH);
        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> {
            client.inputReady("start");
        });
        startPanel.add(startButton, BorderLayout.CENTER);
        mainFrame.add(startPanel, BorderLayout.CENTER);
    }   
}

