package view;

import javax.swing.*;
import controller.PlayerController;
import java.awt.*;
public class PlayerLoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public PlayerLoginFrame() {
        setTitle("Player Login");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Title label
        JLabel titleLabel = new JLabel("Player Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 255)); // Light blue
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel for username and password
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Button panel for Login and Register buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        // Style the buttons
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(102, 178, 255)); // Light blue
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);

        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(102, 255, 178)); // Light green
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusPainted(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
        
            PlayerController playerController = new PlayerController();
            int playerId = playerController.loginPlayer(username, password);
        
            if (playerId != -1) { // If login is successful
                new CustomDialog(this, "Message", "Login Successful");
                new PlayerDashboardFrame(playerId).setVisible(true); // Navigate to Player Dashboard instead of Quiz
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        });

        // Action listener for register button
        registerButton.addActionListener(e -> {
            new RegisterPlayerFrame().setVisible(true);
            dispose(); // Close the login frame
        });
    }
}