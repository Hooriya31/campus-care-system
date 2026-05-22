package models;
public class QuizResult {
    private String subject;
    private int totalQuestions;
    private int correctAnswers;

    public QuizResult(String subject, int totalQuestions, int correctAnswers) {
        this.subject = subject;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
    }
    // Getters
    public String getSubject() { return subject; }
    public int getTotalQuestions() { return totalQuestions; }
    public int getCorrectAnswers() { return correctAnswers; }

    public double getScorePercentage() {
        if (totalQuestions == 0) return 0;
        return ((double) correctAnswers / totalQuestions) * 100;
    }

    public void displayResult() {
        System.out.println("==============================");
        System.out.println("Quiz Result of " + subject);
        System.out.println("Score     : " + correctAnswers 
            + "/" + totalQuestions);
        System.out.printf ("Percentage: %.1f%%%n", getScorePercentage());
        if (getScorePercentage() >= 80) {
            System.out.println("Excellent work!");
        } else if (getScorePercentage() >= 50) {
            System.out.println("Good effort, keep practicing!");
        } else {
            System.out.println("Keep studying, you will get there!");
        }
        System.out.println("==============================");
    }
}