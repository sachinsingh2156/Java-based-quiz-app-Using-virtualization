package view;

import javax.swing.*;
import controller.PlayerController;
import model.Player;
import java.awt.*;

public class RegisterPlayerFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JComboBox<String> levelComboBox;

    public RegisterPlayerFrame() {
        setTitle("Register New Player");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 10px spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        add(mainPanel, BorderLayout.CENTER);

        // Create a top panel for the back button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Padding at the top
        add(topPanel, BorderLayout.NORTH);

        // Create the back button
        JButton backButton = new JButton("Back");
        styleBackButton(backButton);
        topPanel.add(backButton, BorderLayout.WEST); // Align the button to the left

        // Create UI components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel levelLabel = new JLabel("Level:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        nameField = new JTextField();
        levelComboBox = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Advanced"});

        JButton registerButton = new JButton("Register");

        // Style the labels and fields
        styleLabel(usernameLabel);
        styleLabel(passwordLabel);
        styleLabel(nameLabel);
        styleLabel(levelLabel);

        styleTextField(usernameField);
        styleTextField(passwordField);
        styleTextField(nameField);
        styleComboBox(levelComboBox);

        // Style the register button
        styleRegisterButton(registerButton);

        // Add components to the main panel
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(levelLabel);
        mainPanel.add(levelComboBox);
        mainPanel.add(new JLabel()); // Placeholder for alignment
        mainPanel.add(registerButton);

        // Action listener for the register button
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String level = (String) levelComboBox.getSelectedItem();

            Player newPlayer = new Player(username, password, name, level);
            PlayerController playerController = new PlayerController();
            boolean isRegistered = playerController.registerPlayer(newPlayer);

            if (isRegistered) {
                new CustomDialog(this, "Success", "Registration Successful!");
                dispose(); // Close the registration frame
                new PlayerLoginFrame().setVisible(true); // Navigate to Player Login
            } else {
                new CustomDialog(this, "Error", "Error Registering Player!");
            }
        });

        // Action listener for the back button
        backButton.addActionListener(e -> {
            new PlayerLoginFrame().setVisible(true); // Return to Player Login
            dispose(); // Close the current frame
        });
    }

    // Method to style labels
    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    // Method to style text fields and combo box
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    // Method to style the register button
    private void styleRegisterButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(102, 178, 255)); // Light blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }

    // Method to style the back button
    private void styleBackButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBackground(new Color(255, 153, 153)); // Light red
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }
}
