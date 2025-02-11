package view;

import javax.swing.*;
import controller.AdminController;
import java.awt.*;

public class DeleteQuestionFrame extends JFrame {

    private JTextField questionIdField;

    public DeleteQuestionFrame() {
        setTitle("Delete Question");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, 10px vertical spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        add(mainPanel);

        // Create the UI components
        JLabel questionIdLabel = new JLabel("Enter Question ID to Delete:");
        questionIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        questionIdField = new JTextField();
        questionIdField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton deleteButton = new JButton("Delete Question");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setBackground(new Color(255, 153, 153)); // Light red
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFocusPainted(false);

        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font("Arial", Font.BOLD, 16));
        doneButton.setBackground(new Color(102, 255, 178)); // Light green
        doneButton.setForeground(Color.BLACK);
        doneButton.setFocusPainted(false);

        // Add components to the panel
        mainPanel.add(questionIdLabel);
        mainPanel.add(questionIdField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(deleteButton);
        buttonPanel.add(doneButton);
        mainPanel.add(buttonPanel);

        // Action listener for the "Delete Question" button
        deleteButton.addActionListener(e -> {
            try {
                int questionId = Integer.parseInt(questionIdField.getText());

                // Call AdminController to delete the question
                AdminController adminController = new AdminController();
                boolean isDeleted = adminController.deleteQuestion(questionId);

                if (isDeleted) {
                    new CustomDialog(this, "Success", "Question Deleted Successfully!");
                    questionIdField.setText(""); // Clear the input field
                } else {
                    new CustomDialog(this, "Error", "Error Deleting Question!");
                }
            } catch (NumberFormatException ex) {
                new CustomDialog(this, "Error", "Invalid Question ID!");
            }
        });

        // Action listener for the "Done" button
        doneButton.addActionListener(e -> {
            new AdminDashboardFrame().setVisible(true); // Return to Admin Dashboard
            dispose(); // Close the current frame
        });
    }
}
