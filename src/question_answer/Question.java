package question_answer;
public class Question {
    private final String question;
    private final String questioner;
    public Question(String question, String questioner) {
        this.question = question;
        this.questioner = questioner;
    }
    public String getQuestion() {return this.question;}
    public String getQuestioner() {return this.questioner;}
}
