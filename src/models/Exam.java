package models;

public class Exam extends Task {
    private String examHall;
    private String duration;
    private String examType;

    public Exam(String title, String subject, String dueDate,
                String examHall, String duration, String examType) {
        super(title, subject, dueDate);
        this.examHall = examHall;
        this.duration = duration;
        this.examType = examType;
    }

    public String getExamHall() { return examHall; }
    public String getDuration() { return duration; }
    public String getExamType() { return examType; }

    public void setExamHall(String examHall) { this.examHall = examHall; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setExamType(String examType) { this.examType = examType; }

    @Override
    public void getDetails() {
        System.out.println("------------------------------");
        System.out.println("TYPE      : Exam");
        System.out.println("Title     : " + getTitle());
        System.out.println("Subject   : " + getSubject());
        System.out.println("Date      : " + getDueDate());
        System.out.println("Hall      : " + examHall);
        System.out.println("Duration  : " + duration);
        System.out.println("Exam Type : " + examType);
        System.out.println("Status    : " +
            (isCompleted() ? "Completed" : "Pending"));
        System.out.println("------------------------------");
    }
}