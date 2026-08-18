package network;

import question_answer.QAPair;

public interface UserInterface {
    //public void lobby_create_scene();
    //public void lobby_join_scene();
    public String requestNameUI();
    public void invalidNameUI();
    public void validNameUI();
    public String hostStartUI();
    public void gameStartUI();
    public String qa1UI();
    public void qa2UI(QAPair qaPair);
    public void voteUI(QAPair qaPair);
    public void voteResultUI(float percentage);
    public void endUI();
    public void cleanup();
    public void requestAnswer();
    public void buildUI();
}
