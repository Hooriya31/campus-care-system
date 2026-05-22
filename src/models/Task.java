package models;

//Task is an abstract, and Assignment and Exam both extend it. 

public abstract class Task {
    private String title;
    private String subject;
    private String dueDate;
    private boolean isCompleted;

    // Constructor
    public Task(String title, String subject, String dueDate) {
        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.isCompleted = false; // every task starts incomplete
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void markCompleted() {
        this.isCompleted = true;
        System.out.println("Task marked as completed!");
    }

    // Abstract method, every subclass must implement this differently
    public abstract void getDetails();
}
