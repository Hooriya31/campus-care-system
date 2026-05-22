package gui;

import javax.swing.*;
import java.awt.*;

import models.QuizResult;
import models.Student;

public class DashboardFrame extends JFrame {

    private Student student;

    public DashboardFrame(Student student) {

        this.student = student;

        setTitle("CampusCare Dashboard");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel heading = new JLabel("CampusCare Dashboard");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(120, 30, 300, 30);
        panel.add(heading);

        JButton plannerButton = new JButton("Study Planner");
        plannerButton.setBounds(150, 90, 180, 40);
        panel.add(plannerButton);

        JButton quizButton = new JButton("Flashcard Quiz");
        quizButton.setBounds(150, 150, 180, 40);
        panel.add(quizButton);

        JButton profileButton = new JButton("My Profile");
        profileButton.setBounds(150, 210, 180, 40);
        panel.add(profileButton);

        JButton resultsButton = new JButton("View Quiz Results");
        resultsButton.setBounds(150, 270, 180, 40);
        panel.add(resultsButton);

        // quiz button
        quizButton.addActionListener(e -> {
        String subject = JOptionPane.showInputDialog("Enter subject:");
          if (subject != null && !subject.isEmpty()) {
                     new QuizFrame(student, subject); }
            });
        // Planner button
        plannerButton.addActionListener(e -> {
                new PlannerFrame(student).setVisible(true);
                  });

        //result button
        resultsButton.addActionListener(e -> {
   
         StringBuilder sb = new StringBuilder();

         if (student.getQuiz().getResults().isEmpty()) {
        sb.append("No quiz results yet.");
        } else {
        for (QuizResult r : student.getQuiz().getResults()) {
            sb.append("Subject: ").append(r.getSubject()).append("\n");
            sb.append("Score: ").append(r.getCorrectAnswers())
              .append("/").append(r.getTotalQuestions()).append("\n");
            sb.append("Percentage: ")
              .append(String.format("%.1f", r.getScorePercentage()))
              .append("%\n\n");
        }
    }

    JOptionPane.showMessageDialog(this, sb.toString());
});         

        // Profile button
        profileButton.addActionListener(e -> {
            showProfile();
        });

        add(panel);
        setVisible(true);
    }

    private void showProfile() {

        String info =
                "Name: " + student.getName()
                + "\nStudent ID: " + student.getStudentID()
                + "\nDepartment: " + student.getDepartment()
                + "\nSemester: " + student.getSemester();

        JOptionPane.showMessageDialog(this, info);
    }
}
