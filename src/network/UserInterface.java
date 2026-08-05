package network;

import question_answer.QAPair;

public interface UserInterface {
    // void lobby_create_scene();
    // void lobby_join_scene();
    void naming_scene();
    void start_scene();
    void qa_1_scene();
    void qa_2_scene(QAPair qaPair);
    void vote_scene(QAPair qaPair);
    void vote_result_scene(float percentage);
    void end_scene();
}
