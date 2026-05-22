package models;

//Student extends Person and is the central class of the system. Student uses composition.
// it owns a Planner and a Quiz object which are created inside Student's constructor.

public class Student extends Person {
    private String studentID;
    private int semester;
    private String department;
    private Planner planner; // composition
    private Quiz quiz;       // composition

    public Student(String name, int age, String email,
                   String studentID, int semester, String department) {
        super(name, age, email); // calling Person constructor
        this.studentID = studentID;
        this.semester = semester;
        this.department = department;
        this.planner = new Planner(); // created automatically
        this.quiz = new Quiz();

    }
    public String getStudentID() { 
        return studentID; 
    }
    public int getSemester() { 
        return semester; 
    }
    public String getDepartment() { 
        return department; 
    }
    public Planner getPlanner() { 
        return planner; 
    }
    public Quiz getQuiz() { 
        return quiz; 
    } 

    public void setSemester(int semester) {
        if (semester >= 1 && semester <= 8) {
            this.semester = semester;
        } else {
            System.out.println("Semester must be between 1 and 8.");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("===== Student Profile =====");
        System.out.println("Name       : " + getName());
        System.out.println("Age        : " + getAge());
        System.out.println("Email      : " + getEmail());
        System.out.println("Student ID : " + studentID);
        System.out.println("Semester   : " + semester);
        System.out.println("Department : " + department);
        System.out.println("===========================");
    }
}

