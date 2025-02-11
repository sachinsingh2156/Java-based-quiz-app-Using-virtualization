package view;

import javax.swing.*;
import controller.AdminController;
import model.Question;
import java.awt.*;

public class AddQuestionFrame extends JFrame {

    private JTextField questionField;
    private JTextField option1Field;
    private JTextField option2Field;
    private JTextField option3Field;
    private JTextField option4Field;
    private JComboBox<String> difficultyComboBox;
    private JComboBox<Integer> correctAnswerComboBox;

    public AddQuestionFrame() {
        setTitle("Add New Question");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 2, 10, 10)); // 8 rows, 2 columns, 10px spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        add(mainPanel);

        // Create the UI components
        JLabel questionLabel = new JLabel("Enter Question:");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        questionField = new JTextField();
        questionField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel option1Label = new JLabel("Option 1:");
        option1Label.setFont(new Font("Arial", Font.PLAIN, 16));
        option1Field = new JTextField();
        option1Field.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel option2Label = new JLabel("Option 2:");
        option2Label.setFont(new Font("Arial", Font.PLAIN, 16));
        option2Field = new JTextField();
        option2Field.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel option3Label = new JLabel("Option 3:");
        option3Label.setFont(new Font("Arial", Font.PLAIN, 16));
        option3Field = new JTextField();
        option3Field.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel option4Label = new JLabel("Option 4:");
        option4Label.setFont(new Font("Arial", Font.PLAIN, 16));
        option4Field = new JTextField();
        option4Field.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel correctAnswerLabel = new JLabel("Correct Answer:");
        correctAnswerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        correctAnswerComboBox = new JComboBox<>(new Integer[] {1, 2, 3, 4});
        correctAnswerComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel difficultyLabel = new JLabel("Difficulty:");
        difficultyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        difficultyComboBox = new JComboBox<>(new String[] {"Beginner", "Intermediate", "Advanced"});
        difficultyComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton addButton = new JButton("Add Question");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(102, 178, 255)); // Light blue
        addButton.setForeground(Color.BLACK);
        addButton.setFocusPainted(false);

        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font("Arial", Font.BOLD, 16));
        doneButton.setBackground(new Color(102, 255, 178)); // Light green
        doneButton.setForeground(Color.BLACK);
        doneButton.setFocusPainted(false);

        // Add components to the panel
        mainPanel.add(questionLabel);
        mainPanel.add(questionField);
        mainPanel.add(option1Label);
        mainPanel.add(option1Field);
        mainPanel.add(option2Label);
        mainPanel.add(option2Field);
        mainPanel.add(option3Label);
        mainPanel.add(option3Field);
        mainPanel.add(option4Label);
        mainPanel.add(option4Field);
        mainPanel.add(correctAnswerLabel);
        mainPanel.add(correctAnswerComboBox);
        mainPanel.add(difficultyLabel);
        mainPanel.add(difficultyComboBox);

        // Add buttons to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(doneButton);
        mainPanel.add(new JLabel()); // Placeholder
        mainPanel.add(buttonPanel);

        // Action listener for "Add Question" button
        addButton.addActionListener(e -> {
            String questionText = questionField.getText();
            String option1 = option1Field.getText();
            String option2 = option2Field.getText();
            String option3 = option3Field.getText();
            String option4 = option4Field.getText();
            int correctAnswer = (int) correctAnswerComboBox.getSelectedItem();
            String difficulty = (String) difficultyComboBox.getSelectedItem();

            Question newQuestion = new Question(0, questionText, option1, option2, option3, option4, correctAnswer, difficulty);

            AdminController adminController = new AdminController();
            boolean isAdded = adminController.addQuestion(newQuestion);

            if (isAdded) {
                new CustomDialog(this, "Success", "Question Added Successfully!");
                clearFields(); // Clear input fields for the next question
            } else {
                new CustomDialog(this, "Error", "Error Adding Question!");
            }
        });

        // Action listener for "Done" button
        doneButton.addActionListener(e -> {
            new AdminDashboardFrame().setVisible(true); // Go back to Admin Dashboard
            dispose(); // Close the current frame
        });
    }

    // Helper method to clear input fields
    private void clearFields() {
        questionField.setText("");
        option1Field.setText("");
        option2Field.setText("");
        option3Field.setText("");
        option4Field.setText("");
        correctAnswerComboBox.setSelectedIndex(0);
        difficultyComboBox.setSelectedIndex(0);
    }
}
