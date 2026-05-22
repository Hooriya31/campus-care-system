package models;

public class Flashcard {
    private String subject;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption; // stores "A", "B", "C", or "D"

    public Flashcard(String subject, String question,
                     String optionA, String optionB,
                     String optionC, String optionD,
                     String correctOption) {
        this.subject = subject;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption.toUpperCase();
    }

    // Getters
    public String getSubject() { return subject; }
    public String getQuestion() { return question; }
    public String getCorrectOption() { return correctOption; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }

    // Check answer
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer.trim().toUpperCase().equals(correctOption)) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Wrong! Correct answer was: " 
                + correctOption + ") " + getCorrectAnswerText());
            return false;
        }
    }

    // Returns the text of the correct answer
    public String getCorrectAnswerText() {
        switch (correctOption) {
            case "A": return optionA;
            case "B": return optionB;
            case "C": return optionC;
            case "D": return optionD;
            default: return "";
        }
    }

    // Display the question and all options
    public void displayCard() {
        System.out.println("------------------------------");
        System.out.println("Subject : " + subject);
        System.out.println("Q: " + question);
        System.out.println("  A) " + optionA);
        System.out.println("  B) " + optionB);
        System.out.println("  C) " + optionC);
        System.out.println("  D) " + optionD);
        System.out.println("------------------------------");
    }
}