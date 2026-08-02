package question_answer;
public class Answer {
    private final String answer;
    private final String answerer;
    public Answer(String answer, String answerer) {
        this.answer = answer;
        this.answerer = answerer;
    }
    public String getAnswer() {return this.answer;}
    public String getAnswerer() {return this.answerer;}
}
