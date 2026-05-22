package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import models.*;

public class QuizFrame extends JFrame {

    private Student student;
    private ArrayList<Flashcard> questions;

    private int currentIndex = 0;
    private int correct = 0;

    private JLabel questionLabel;
    private JRadioButton optA, optB, optC, optD;
    private ButtonGroup group;
    private JButton nextButton;

    public QuizFrame(Student student, String subject) {

        this.student = student;

        setTitle("Flashcard Quiz - " + subject);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        questions = new ArrayList<>();

        // filter questions by subject
        for (Flashcard f : student.getQuiz().getFlashcards()) {
            if (f.getSubject().equalsIgnoreCase(subject)) {
                questions.add(f);
            }
        }

        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No questions found for this subject!");
            dispose();
            return;
        }

        setLayout(new BorderLayout());

        // QUESTION LABEL
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        add(questionLabel, BorderLayout.NORTH);

        // OPTIONS PANEL
        JPanel centerPanel = new JPanel(new GridLayout(4, 1));

        optA = new JRadioButton();
        optB = new JRadioButton();
        optC = new JRadioButton();
        optD = new JRadioButton();

        group = new ButtonGroup();

        group.add(optA);
        group.add(optB);
        group.add(optC);
        group.add(optD);

        centerPanel.add(optA);
        centerPanel.add(optB);
        centerPanel.add(optC);
        centerPanel.add(optD);

        add(centerPanel, BorderLayout.CENTER);

        // BUTTON
        nextButton = new JButton("Next");
        add(nextButton, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> nextQuestion());

        loadQuestion();

        setVisible(true);
    }

    private void loadQuestion() {

        if (currentIndex >= questions.size()) {
            showResult();
            return;
        }

        Flashcard f = questions.get(currentIndex);

        questionLabel.setText("Q: " + f.getQuestion());

        optA.setText("A) " + f.getOptionA());
        optB.setText("B) " + f.getOptionB());
        optC.setText("C) " + f.getOptionC());
        optD.setText("D) " + f.getOptionD());

        group.clearSelection();
    }

    private void nextQuestion() {

        Flashcard f = questions.get(currentIndex);

        String selected = "";

        if (optA.isSelected()) selected = "A";
        else if (optB.isSelected()) selected = "B";
        else if (optC.isSelected()) selected = "C";
        else if (optD.isSelected()) selected = "D";

        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an answer!");
            return;
        }

        if (selected.equalsIgnoreCase(f.getCorrectOption())) {
            correct++;
        }

        currentIndex++;
        loadQuestion();
    }

    private void showResult() {

        int total = questions.size();

        JOptionPane.showMessageDialog(this,
                "Quiz Finished!\nScore: " + correct + "/" + total);

        // Save result into backend
        QuizResult result = new QuizResult(
                questions.get(0).getSubject(),
                total,
                correct
        );

        student.getQuiz().getResults().add(result);

        dispose();
    }
}
