package view;

import javax.swing.*;
import java.awt.*;

public class CustomDialog extends JDialog {

    public CustomDialog(JFrame parent, String title, String message) {
        super(parent, title, true);

        // Set dialog size and layout
        setSize(300, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent); // Center the dialog relative to the parent frame

        // Title Label
        JLabel messageLabel = new JLabel(message, JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(new Color(0, 102, 0)); // Dark green for success messages

        // OK Button
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setBackground(new Color(102, 178, 255)); // Light blue
        okButton.setForeground(Color.BLACK);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> dispose()); // Close dialog on click

        // Add components to the dialog
        add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(okButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog to visible
        setVisible(true);
    }
}
