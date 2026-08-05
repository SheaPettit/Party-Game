package question_answer;

import java.io.Serializable;

public class QAPair implements Serializable {
    private Question question;
    private Answer answer;
    private boolean answered;
    private boolean questioned;
    private final RoundsType round;
    public QAPair(String response, String playerName, RoundsType round) {
        this.round = round;
        if(round == RoundsType.ONE) {
            this.question = new Question(response, playerName);
            this.answer = null;
            answered = false;
            questioned = true;
        } else if(round == RoundsType.TWO) {
            this.answer = new Answer(response, playerName);
            this.question = null;
            answered = true;
            questioned = false;
        }
    }
    public void completeQA(String response, String playerName) {
        if(round == RoundsType.ONE) {
            if (answered) return;
            this.answer = new Answer(response, playerName); 
            this.answered = true;
        } else if(round == RoundsType.TWO) {
            if (questioned) return;
            this.question = new Question(response, playerName);
            this.questioned = true;
        }
    }
    public String getQuestion() {return this.question.getQuestion();}
    public String getAnswer() {return this.answer.getAnswer();}
    public String getQuestioner() {return this.question.getQuestioner();}
    public String getAnswerer() {return this.answer.getAnswerer();}
}
