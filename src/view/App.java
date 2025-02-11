package view;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // Create a custom JFrame for the mode selection
        JFrame modeSelectionFrame = new JFrame("Quiz App");
        modeSelectionFrame.setSize(400, 200);
        modeSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modeSelectionFrame.setLocationRelativeTo(null);
        modeSelectionFrame.setLayout(new BorderLayout());

        // Add a title label with custom styling
        JLabel titleLabel = new JLabel("Select Mode:", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.DARK_GRAY);
        modeSelectionFrame.add(titleLabel, BorderLayout.NORTH);

        // Create buttons for Admin and Player
        JButton adminButton = new JButton("Admin");
        JButton playerButton = new JButton("Player");

        // Style the buttons
        adminButton.setFont(new Font("Arial", Font.PLAIN, 16));
        adminButton.setBackground(new Color(102, 178, 255));  // Light blue
        adminButton.setForeground(Color.BLACK);
        adminButton.setFocusPainted(false);

        playerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        playerButton.setBackground(new Color(102, 255, 178));  // Light green
        playerButton.setForeground(Color.BLACK);
        playerButton.setFocusPainted(false);

        // Add action listeners to buttons
        adminButton.addActionListener(e -> {
            new AdminLoginFrame().setVisible(true);  // Open Admin Login
            modeSelectionFrame.dispose();  // Close the selection frame
        });

        playerButton.addActionListener(e -> {
            new PlayerLoginFrame().setVisible(true);  // Open Player Login
            modeSelectionFrame.dispose();  // Close the selection frame
        });

        // Add buttons to a panel with spacing
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));  // Horizontal layout with spacing
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Add padding
        buttonPanel.add(adminButton);
        buttonPanel.add(playerButton);

        modeSelectionFrame.add(buttonPanel, BorderLayout.CENTER);

        // Show the frame
        modeSelectionFrame.setVisible(true);
    }
}
