package models;

//Planner holds an ArrayList of Task objects.
//implements the Saveable interface which forces them to have save() and load() methods.

import java.util.ArrayList;
import java.io.*;

public class Planner implements Saveable {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task t) {
        tasks.add(t);
        System.out.println("Task added: " + t.getTitle());
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            System.out.println("Removed: " + tasks.get(index).getTitle());
            tasks.remove(index);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
            return;
        }
        System.out.println("\n===== All Tasks =====");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task #" + (i + 1));
            tasks.get(i).getDetails();    //polymorphysm
        }
    }

    public ArrayList<Task> getTasks() { return tasks; }
    public int getTaskCount() { return tasks.size(); }

    @Override
    public void save(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(filename));
            for (Task t : tasks) {
                if (t instanceof Assignment) {
                    Assignment a = (Assignment) t;
                    writer.write("ASSIGNMENT," + a.getTitle() + "," 
                        + a.getSubject() + "," + a.getDueDate() + "," 
                        + a.getSubmissionType() + "," + a.getTotalMarks() 
                        + "," + a.isCompleted());
                } else if (t instanceof Exam) {
                    Exam e = (Exam) t;
                    writer.write("EXAM," + e.getTitle() + "," 
                        + e.getSubject() + "," + e.getDueDate() + "," 
                        + e.getExamHall() + "," + e.getDuration() + "," 
                        + e.getExamType() + "," + e.isCompleted());
                }
                writer.newLine();
            }
            writer.close();
            System.out.println("Planner saved to " + filename);
        } catch (IOException e) {
            System.out.println("Could not save planner: " + e.getMessage());
        }
    }

    @Override
    public void load(String filename) {
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(filename));
            String line;
            tasks.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("ASSIGNMENT")) {
                    Assignment a = new Assignment(
                        parts[1], parts[2], parts[3], parts[4], 
                        Integer.parseInt(parts[5]));
                    if (parts[6].equals("true")) a.markCompleted();
                    tasks.add(a);
                } else if (parts[0].equals("EXAM")) {
                    Exam e = new Exam(
                        parts[1], parts[2], parts[3], 
                        parts[4], parts[5], parts[6]);
                    if (parts[7].equals("true")) e.markCompleted();
                    tasks.add(e);
                }
            }
            reader.close();
            System.out.println("Planner loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Could not load planner: " + e.getMessage());
        }
    }
}

