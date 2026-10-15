package question_answer;
public class Answer {
    private String answer;
    private final String answerer;
    public Answer(String answer, String answerer) {
        this.answer = answer;
        this.answerer = answerer;
    }
    public Answer(String answerer) {
        this.answerer = answerer;
        this.answer = null;
    }
    public String getAnswer() {return this.answer;}
    public String getAnswerer() {return this.answerer;}
    public void setAnswer(String answer, String answerer) {
        if(this.answer == null && this.answerer.compareTo(answerer) == 0) {
            this.answer = answer;
        }
    }
}
