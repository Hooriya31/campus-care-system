package models;

//Quiz holds an ArrayList of Flashcard objects and an ArrayList of QuizResult objects
//implements the Saveable interface which forces them to have save() and load() methods.

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Quiz implements Saveable {
    private ArrayList<Flashcard> flashcards = new ArrayList<>();
    private ArrayList<QuizResult> results = new ArrayList<>();

    //Getters from Gui 
    public ArrayList<Flashcard> getFlashcards() {
    return flashcards;
     }
    public ArrayList<QuizResult> getResults() {
    return results;
       }

    // Add a flashcard manually
    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
        System.out.println("Flashcard added: " + f.getQuestion());
    }

    // Remove a flashcard
    public void removeFlashcard(int index) {
        if (index >= 0 && index < flashcards.size()) {
            System.out.println("Removed: " + 
                flashcards.get(index).getQuestion());
            flashcards.remove(index);
        } else {
            System.out.println("Invalid flashcard number.");
        }
    }

    // Load flashcards from a text file
    public void loadFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(filename));
            String line;
            String subject = "", question = "";
            String optA = "", optB = "", optC = "", optD = "";
            String correct = "";

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("SUBJECT:")) {
                    subject = line.substring(8).trim();
                } else if (line.startsWith("Q:")) {
                    question = line.substring(2).trim();
                } else if (line.startsWith("A:")) {
                    optA = line.substring(2).trim();
                } else if (line.startsWith("B:")) {
                    optB = line.substring(2).trim();
                } else if (line.startsWith("C:")) {
                    optC = line.substring(2).trim();
                } else if (line.startsWith("D:")) {
                    optD = line.substring(2).trim();
                } else if (line.startsWith("CORRECT:")) {
                    correct = line.substring(8).trim();
                    // All fields collected, create flashcard
                    flashcards.add(new Flashcard(
                        subject, question,
                        optA, optB, optC, optD, correct));
                }
            }
            reader.close();
            System.out.println("Loaded " + flashcards.size() 
                + " flashcards from " + filename);

        } catch (IOException e) {
            System.out.println("Could not load file: " + filename);
        }
    }

    // View all flashcards
    public void viewAllFlashcards() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards added yet.");
            return;
        }
        System.out.println("\n===== All Flashcards =====");
        for (int i = 0; i < flashcards.size(); i++) {
            System.out.println("Card #" + (i + 1));
            flashcards.get(i).displayCard();
        }
    }

    // Start quiz on a specific subject
    public void startQuiz(String subject) {
        Scanner scanner = new Scanner(System.in);

        // Filter by subject
        ArrayList<Flashcard> subjectCards = new ArrayList<>();
        for (Flashcard f : flashcards) {
            if (f.getSubject().equalsIgnoreCase(subject)) {
                subjectCards.add(f);
            }
        }

        if (subjectCards.isEmpty()) {
            System.out.println("No flashcards found for: " + subject);
            return;
        }

        System.out.println("\n===== Quiz: " + subject + " =====");
        System.out.println("Total questions: " + subjectCards.size());
        System.out.println("Type A, B, C, or D for each answer.\n");

        int correct = 0;

        for (int i = 0; i < subjectCards.size(); i++) {
            Flashcard card = subjectCards.get(i);
                System.out.println("Question " + (i + 1));
                card.displayCard();
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (card.checkAnswer(userAnswer)) {
                correct++;
            }
            System.out.println();
        }

        // Save result
        QuizResult result = new QuizResult(
            subject, subjectCards.size(), correct);
        results.add(result);
        result.displayResult();
    }

    // View all past results
    public void viewResults() {
        if (results.isEmpty()) {
            System.out.println("No quiz results yet.");
            return;
        }
        System.out.println("\n===== Quiz History =====");
        for (QuizResult r : results) {
            r.displayResult();
        }
    }

    public int getFlashcardCount() { 
        return flashcards.size(); }
    public int getResultCount() { 
        return results.size(); 
    }


 
      @Override
    public void save(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(filename));
            for (Flashcard f : flashcards) {
                writer.write(f.getSubject() + "|" + f.getQuestion() + "|"+ f.getOptionA() + "|" + f.getOptionB() + "|"+ f.getOptionC() + "|" + f.getOptionD() + "|" + f.getCorrectOption());
                writer.newLine();
            }
            writer.close();
            System.out.println("Quiz saved to " + filename);
        } catch (IOException e) {
            System.out.println("Could not save quiz: " + e.getMessage());
        }
    }

    @Override
    public void load(String filename) {
        // already handled by loadFromFile()
        loadFromFile(filename);
    }
}