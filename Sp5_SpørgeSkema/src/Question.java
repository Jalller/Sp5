
public class Question {
    private String question;
    private String greenAdvice;
    private boolean green;



    public Question(String question, String greenAdvice, Boolean green) {
        this.question = question;
        this.greenAdvice = greenAdvice;
        this.green = green;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", greenAdvice='" + greenAdvice + '\'' +
                ", green=" + green +
                '}';
    }

    public boolean isGreen() {
        return green;
    }

    public void setGreen(boolean green) {
        this.green = green;
    }

    public String getQuestion() {
        return question;
    }

    public String getGreenAdvice() {
        return greenAdvice;
    }


}
