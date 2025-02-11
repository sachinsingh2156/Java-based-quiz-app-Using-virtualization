package controller;

import model.*;
import db.*;
import java.util.List;

public class ReportController {

    // Method to generate a report for the player after the quiz
    public boolean generateReport(int playerId, int correctAnswers, int wrongAnswers, int score, String difficulty) {
        ReportDAO reportDAO = new ReportDAO();
        // Adjust the constructor to match the correct parameters
        Report newReport = new Report(playerId, correctAnswers, wrongAnswers, score, difficulty);
        return reportDAO.addReport(newReport);  // Save the report to the database
    }

    // Method to fetch all reports for a player
    public List<Report> getReportsForPlayer(int playerId) {
        ReportDAO reportDAO = new ReportDAO();
        return reportDAO.getReportsForPlayer(playerId);  // Retrieve reports from the database
    }
}
