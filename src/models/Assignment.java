package models;

public class Assignment extends Task {
    private String submissionType; //online or physical
    private int totalMarks;

    public Assignment(String title, String subject, String dueDate,
                      String submissionType, int totalMarks) {
        super(title, subject, dueDate);
        this.submissionType = submissionType;
        this.totalMarks = totalMarks;
    }

    public String getSubmissionType() { return submissionType; }
    public int getTotalMarks() { return totalMarks; }

    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    public void setTotalMarks(int totalMarks) {
        if (totalMarks > 0) this.totalMarks = totalMarks;
        else System.out.println("Marks must be greater than 0.");
    }

    @Override
    public void getDetails() {
        System.out.println("------------------------------");
        System.out.println("TYPE            : Assignment");
        System.out.println("Title           : " + getTitle());
        System.out.println("Subject         : " + getSubject());
        System.out.println("Due Date        : " + getDueDate());
        System.out.println("Submission Type : " + submissionType);
        System.out.println("Total Marks     : " + totalMarks);
        System.out.println("Status          : " +
            (isCompleted() ? "Completed" : "Pending"));
        System.out.println("------------------------------");
    }
}