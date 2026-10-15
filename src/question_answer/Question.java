package question_answer;
public class Question {
    private String question;
    private final String questioner;
    public Question(String question, String questioner) {
        this.question = question;
        this.questioner = questioner;
    }
    public Question(String questioner) {
        this.question = null;
        this.questioner = questioner;
    }
    public String getQuestion() {return this.question;}
    public String getQuestioner() {return this.questioner;}
    public void setQuestion(String question, String questioner) {
        if(this.question == null && this.questioner.compareTo(questioner) == 0) {
            this.question = question;
        }
    }
}
