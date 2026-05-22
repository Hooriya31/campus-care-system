package gui;

import javax.swing.*;
import java.awt.*;
import models.Student;

public class LoginFrame extends JFrame {

    private JTextField nameField;
    private JTextField idField;
    private JButton loginButton;

    public LoginFrame() {

        setTitle("CampusCare Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Heading
        JLabel heading = new JLabel("Welcome to CampusCare");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setBounds(80, 20, 250, 30);
        panel.add(heading);

        // Name label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);
        panel.add(nameLabel);

        // Name field
        nameField = new JTextField();
        nameField.setBounds(150, 80, 180, 25);
        panel.add(nameField);

        // ID label
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(50, 120, 100, 25);
        panel.add(idLabel);

        // ID field
        idField = new JTextField();
        idField.setBounds(150, 120, 180, 25);
        panel.add(idField);

        // Login button
        loginButton = new JButton("Enter");
        loginButton.setBounds(140, 170, 100, 30);
        panel.add(loginButton);

        // Button action
        loginButton.addActionListener(e -> login());

        add(panel);
    }

    private void login() {

        String name = nameField.getText();
        String id = idField.getText();

        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields.");
            return;
        }

        // Create student object
        Student student = new Student(
                name,
                19,
                "student@uni.edu",
                id,
                2,
                "Software Engineering"
        );

        // Load saved data
        student.getPlanner().load("planner_data.txt");
        student.getQuiz().load("oop_quiz.txt");

        // Open dashboard
        new DashboardFrame(student);

        dispose();
    }
}
