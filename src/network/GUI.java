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
        resetUI();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel("Name:");
        JTextField text = new JTextField("Enter Name...");
        JButton buttton = new JButton("Done");
        buttton.addActionListener(e -> {
            String name = text.getText();
            client.inputReady(name);
        });
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(text, BorderLayout.CENTER);
        panel.add(buttton, BorderLayout.SOUTH);
        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    public void invalidNameUI(){}
    public void validNameUI(){}
    public void gameStartUI(boolean isHost){
        addStartFrame(isHost);
    }
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
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    private void addStartFrame(boolean isHost) {
        resetUI();
        JPanel startPanel;
        if (isHost) {
            startPanel = addHostStartFrame();
        } else {
            startPanel = addRestStartFrame();
        }
        
        mainFrame.add(startPanel, BorderLayout.CENTER);
        mainFrame.repaint();
        mainFrame.revalidate();
    }   
    private JPanel addHostStartFrame() {
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        JLabel startText = new JLabel("Press 'START' to start the game");
        startPanel.add(startText, BorderLayout.NORTH);
        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> {
            client.inputReady("start");
        });
        startPanel.add(startButton, BorderLayout.CENTER);
        return startPanel;
    }
    private JPanel addRestStartFrame() {
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        JLabel startText = new JLabel("Waiting for host to start the game...");
        startPanel.add(startText, BorderLayout.CENTER);
        return startPanel;
    }
    private void resetUI() {
        mainFrame.getContentPane().removeAll();
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}

