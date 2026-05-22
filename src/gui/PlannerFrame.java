package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.*;

public class PlannerFrame extends JFrame {

    private Student student;
    private JTable table;
    private DefaultTableModel model;

    public PlannerFrame(Student student) {

        this.student = student;

        setTitle("Study Planner");
        setSize(700, 400);
        setLocationRelativeTo(null);

        String[] columns = {
            "Type",
            "Title",
            "Subject",
            "Due Date",
            "Status"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addAssignment = new JButton("Add Assignment");
        JButton addExam = new JButton("Add Exam");
        JButton completeTask = new JButton("Mark Complete");
        JButton removeTask = new JButton("Remove Task");
        JButton backButton = new JButton("Back");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(addAssignment);
        buttonPanel.add(addExam);
        buttonPanel.add(completeTask);
        buttonPanel.add(removeTask);
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        add(scrollPane, "Center");
        add(buttonPanel, "South");

        // Add assignment
        addAssignment.addActionListener(e -> addAssignment());

        // Add exam
        addExam.addActionListener(e -> addExam());

        // Mark complete
        completeTask.addActionListener(e -> markComplete());

        // Remove task
        removeTask.addActionListener(e -> removeTask());
        // Back button
         backButton.addActionListener(e -> {
         dispose();
             });

           // Exit button
          exitButton.addActionListener(e -> {
           System.exit(0);
             });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void refreshTable() {

        model.setRowCount(0);

        for (Task t : student.getPlanner().getTasks()) {

            String type = "";

            if (t instanceof Assignment) {
                type = "Assignment";
            } else {
                type = "Exam";
            }

            model.addRow(new Object[]{
                type,
                t.getTitle(),
                t.getSubject(),
                t.getDueDate(),
                t.isCompleted() ? "Completed" : "Pending"
            });
        }
    }

    private void addAssignment() {

        String title = JOptionPane.showInputDialog("Enter title:");
        String subject = JOptionPane.showInputDialog("Enter subject:");
        String date = JOptionPane.showInputDialog("Enter due date:");
        String type = JOptionPane.showInputDialog("Submission type:");
        int marks = Integer.parseInt(
                JOptionPane.showInputDialog("Total marks:")
        );

        Assignment a = new Assignment(
                title,
                subject,
                date,
                type,
                marks
        );

        student.getPlanner().addTask(a);

        student.getPlanner().save("planner_data.txt");

        refreshTable();
    }

    private void addExam() {

        String title = JOptionPane.showInputDialog("Enter title:");
        String subject = JOptionPane.showInputDialog("Enter subject:");
        String date = JOptionPane.showInputDialog("Enter date:");
        String hall = JOptionPane.showInputDialog("Exam hall:");
        String duration = JOptionPane.showInputDialog("Duration:");
        String type = JOptionPane.showInputDialog("Exam type:");

        Exam e = new Exam(
                title,
                subject,
                date,
                hall,
                duration,
                type
        );

        student.getPlanner().addTask(e);

        student.getPlanner().save("planner_data.txt");

        refreshTable();
    }

    private void markComplete() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Select a task first.");
            return;
        }

        student.getPlanner().getTasks().get(row).markCompleted();
        student.getPlanner().save("planner_data.txt");

        refreshTable();
    }

    private void removeTask() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Select a task first.");
            return;
        }

        student.getPlanner().removeTask(row);

        student.getPlanner().save("planner_data.txt");

        refreshTable();
    }
}
