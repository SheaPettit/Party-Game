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
            question = new Question(response, playerName);
            answer = null;
            answered = false;
            questioned = true;
        } else if(round == RoundsType.TWO) {
            answer = new Answer(response, playerName);
            question = null;
            answered = true;
            questioned = false;
        }
    }
    //Make it so assignment of player can be done first, than have another method for assignment of response
    public void assignQA(String playerName) {
        if(round == RoundsType.ONE) {
            if (answered || answer != null) return;
            answer = new Answer(playerName); 
        } else if(round == RoundsType.TWO) {
            if (questioned || question != null) return;
            question = new Question(playerName);
        }
    }
    public void completeQA(String response, String playerName) {
        if(round == RoundsType.ONE) {
            if (answered) return;
            answer.setAnswer(response, playerName); 
            answered = true;
        } else if(round == RoundsType.TWO) {
            if (questioned) return;
            question.setQuestion(response, playerName); 
            questioned = true;
        }
    }
    public String getQuestion() {return this.question.getQuestion();}
    public String getAnswer() {return this.answer.getAnswer();}
    public String getQuestioner() {return this.question.getQuestioner();}
    public String getAnswerer() {return this.answer.getAnswerer();}
    public String getPlayerOne() {
        switch(round) {
            case ONE:
                return question.getQuestioner();
            case TWO:
                return answer.getAnswerer();
            default:
                throw new IllegalStateException("Unexpected value: Round " + round);
        }
    }
    public String getPlayerTwo() {
        switch(round) {
            case ONE:
                return answer.getAnswerer();
            case TWO:
                return question.getQuestioner();
            default:
                throw new IllegalStateException("Unexpected value: Round " + round);
        }
    }
}
