package view;

import javax.swing.*;
import java.awt.*;

public class PlayerDashboardFrame extends JFrame {

    private int playerId;

    public PlayerDashboardFrame(int playerId) {
        this.playerId = playerId;

        setTitle("Player Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with layout and padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        add(mainPanel);

        // Buttons for actions
        JButton startQuizButton = new JButton("Start Quiz");
        JButton viewReportButton = new JButton("View Report");
        JButton logoutButton = new JButton("Logout");

        styleButton(startQuizButton, new Color(102, 178, 255)); // Light blue
        styleButton(viewReportButton, new Color(102, 255, 178)); // Light green
        styleButton(logoutButton, new Color(255, 153, 153)); // Light red

        // Add buttons to the panel
        mainPanel.add(startQuizButton);
        mainPanel.add(viewReportButton);
        mainPanel.add(logoutButton);

        // Action listeners for buttons
        startQuizButton.addActionListener(e -> {
            new QuizFrame(playerId).setVisible(true); // Pass playerId when starting the quiz
            dispose();
        });
        

        viewReportButton.addActionListener(e -> {
            new PlayerReportFrame(playerId).setVisible(true); // Navigate to Report
            dispose();
        });

        logoutButton.addActionListener(e -> {
            new PlayerLoginFrame().setVisible(true); // Navigate back to login
            dispose();
        });
    }

    // Method to style buttons
    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }
}
