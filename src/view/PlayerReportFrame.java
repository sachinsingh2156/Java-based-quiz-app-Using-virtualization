package view;

import javax.swing.*;
import controller.ReportController;
import model.Report;
import db.HighScoreDAO;
import java.awt.*;
import java.util.List;

public class PlayerReportFrame extends JFrame {

    private int playerId;

    public PlayerReportFrame(int playerId) {
        this.playerId = playerId;

        setTitle("Player Report");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        add(mainPanel);

        // Text area to display report details
        JTextArea reportArea = new JTextArea();
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        reportArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Report Details"));

        JButton backButton = new JButton("Back");
        styleBackButton(backButton);

        // Fetch player report and highest score
        fetchPlayerReport(reportArea);

        backButton.addActionListener(e -> {
            new PlayerDashboardFrame(playerId).setVisible(true); // Back to Player Dashboard
            dispose();
        });

        // Layout
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
    }

    private void fetchPlayerReport(JTextArea reportArea) {
        ReportController reportController = new ReportController();
        List<Report> reports = reportController.getReportsForPlayer(playerId);
    
        HighScoreDAO highScoreDAO = new HighScoreDAO();
        Report highestReport = highScoreDAO.getHighestScore();
    
        StringBuilder reportText = new StringBuilder();
        if (!reports.isEmpty()) {
            for (Report report : reports) {
                reportText.append("Correct Answers: ").append(report.getCorrectAnswers())
                          .append("\nWrong Answers: ").append(report.getTotalQuestions() - report.getCorrectAnswers())
                          .append("\nScore: ").append(report.getScore())
                          .append("\n-------------------------\n");
            }
        } else {
            reportText.append("No reports available for this player.\n");
        }
    
        reportText.append("\nHighest Scorer:\n");
        if (highestReport != null) {
            reportText.append("Name: ").append(highestReport.getPlayerName())
                      .append("\nScore: ").append(highestReport.getScore());
        } else {
            // Treat the current player's score as the high score if no other scores exist
            reportText.append("Name: Current Player")
                      .append("\nScore: ").append(reports.isEmpty() ? 0 : reports.get(0).getScore());
        }
    
        reportArea.setText(reportText.toString());
    }
    
    

    // Method to style the back button
    private void styleBackButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(255, 153, 153)); // Light red
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }
}
