package db;

import model.Report;
import java.sql.*;
import java.util.*;

public class ReportDAO {

    // Method to add a new report
    public boolean addReport(Report report) {
        String query = "INSERT INTO reports (player_id, correct_answers, score, difficulty) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            // Set parameters
            stmt.setInt(1, report.getPlayerId());
            stmt.setInt(2, report.getCorrectAnswers());
            stmt.setInt(3, report.getScore());
            stmt.setString(4, report.getDifficulty());

            // Execute update and retrieve auto-generated reportId
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Get the auto-generated reportId and set it in the Report object
                    report.setReportId(generatedKeys.getInt(1));
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all reports for a specific player
    public List<Report> getReportsForPlayer(int playerId) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT *, (correct_answers + (10 - correct_answers)) AS total_questions FROM reports WHERE player_id = ?"; 
        // Replace `(10 - correct_answers)` with actual logic for calculating total questions.
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Report report = new Report();
                report.setReportId(rs.getInt("report_id"));
                report.setPlayerId(rs.getInt("player_id"));
                report.setCorrectAnswers(rs.getInt("correct_answers"));
                report.setScore(rs.getInt("score"));
                report.setDifficulty(rs.getString("difficulty"));
                report.setTotalQuestions(rs.getInt("total_questions")); // Set total questions
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
    
}
