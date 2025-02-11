package view;

import javax.swing.*;
import controller.ReportController;
import model.Report;
import java.awt.*;
import java.util.List;

public class ViewReportsFrame extends JFrame {

    private JTextField playerIdField;
    private JTextArea reportsArea;

    public ViewReportsFrame() {
        setTitle("View Reports");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Input panel for player ID, view reports button, and back button
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel playerIdLabel = new JLabel("Enter Player ID to View Reports:");
        playerIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        playerIdField = new JTextField(15);
        playerIdField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton viewReportsButton = new JButton("View Reports");
        viewReportsButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewReportsButton.setBackground(new Color(102, 178, 255)); // Light blue
        viewReportsButton.setForeground(Color.BLACK);
        viewReportsButton.setFocusPainted(false);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 153, 153)); // Light red
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);

        inputPanel.add(playerIdLabel);
        inputPanel.add(playerIdField);
        inputPanel.add(viewReportsButton);
        inputPanel.add(backButton);

        // Text area for displaying reports
        reportsArea = new JTextArea();
        reportsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        reportsArea.setEditable(false);
        reportsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane scrollPane = new JScrollPane(reportsArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Reports"));
        scrollPane.setPreferredSize(new Dimension(550, 350));

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Action listener for the "View Reports" button
        viewReportsButton.addActionListener(e -> {
            try {
                int playerId = Integer.parseInt(playerIdField.getText());

                // Fetch reports for the player
                ReportController reportController = new ReportController();
                List<Report> reports = reportController.getReportsForPlayer(playerId);

                // Display the reports in the text area
                StringBuilder reportText = new StringBuilder();
                if (!reports.isEmpty()) {
                    for (Report report : reports) {
                        reportText.append("Report ID: ").append(report.getReportId())
                                  .append("\nCorrect Answers: ").append(report.getCorrectAnswers())
                                  .append("\nScore: ").append(report.getScore())
                                  .append("\nDifficulty: ").append(report.getDifficulty())
                                  .append("\n--------------------\n");
                    }
                } else {
                    reportText.append("No reports found for Player ID: ").append(playerId);
                }
                reportsArea.setText(reportText.toString());
            } catch (NumberFormatException ex) {
                new CustomDialog(this, "Error", "Please enter a valid Player ID!");
            }
        });

        // Action listener for the "Back" button
        backButton.addActionListener(e -> {
            new AdminDashboardFrame().setVisible(true); // Return to Admin Dashboard
            dispose(); // Close the current frame
        });
    }
}
