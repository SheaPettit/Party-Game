package network;

import question_answer.QAPair;

public interface UserInterface {
    //public void lobby_create_scene();
    //public void lobby_join_scene();
    public void requestNameUI();
    public void invalidNameUI();
    public void validNameUI();
    public void gameStartUI(boolean isHost);
    public void qa1UI();
    public void qa2UI(QAPair qaPair);
    public void voteUI(QAPair qaPair);
    public void voteResultUI(float percentage);
    public void endUI();
    public void cleanup();
    public void requestAnswer();
    public void buildUI();
}
