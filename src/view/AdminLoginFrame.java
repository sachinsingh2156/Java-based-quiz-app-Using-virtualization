package view;

import javax.swing.*;
import controller.AdminController;
import java.awt.*;

public class AdminLoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLoginFrame() {
        setTitle("Admin Login");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Title label
        JLabel titleLabel = new JLabel("Admin Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 255)); // Light blue
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel for username and password
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(formPanel, BorderLayout.CENTER);

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

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");

        // Style the button
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(102, 178, 255)); // Light blue
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(loginButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            AdminController adminController = new AdminController();
            boolean isLoggedIn = adminController.loginAdmin(username, password);

            if (isLoggedIn) {
                new CustomDialog(this, "Success", "Login Successful");
                new AdminDashboardFrame().setVisible(true);
                dispose();
            } else {
                new CustomDialog(this, "Error", "Invalid username or password");
            }
        });
    }
}
