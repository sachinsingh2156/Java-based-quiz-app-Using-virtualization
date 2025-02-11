package db;

import model.Player;
import model.Report;

import java.sql.*;
import java.util.*;

public class HighScoreDAO {

    // Method to add a new high score
    public boolean addHighScore(int playerId, int score, String level) {
        String query = "INSERT INTO high_scores (player_id, score, level) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, playerId);
            stmt.setInt(2, score);
            stmt.setString(3, level);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get high scores by difficulty level
    public List<Player> getHighScoresByLevel(String level) {
        List<Player> highScores = new ArrayList<>();
        String query = "SELECT p.name, hs.score FROM high_scores hs JOIN players p ON hs.player_id = p.player_id WHERE hs.level = ? ORDER BY hs.score DESC LIMIT 10";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, level);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                // Use the parameterized constructor
                Player player = new Player(rs.getString("name"), "", "", "");  // Add default values for password and level if needed
                player.setScore(rs.getInt("score"));
                highScores.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScores;
    }

    // Method to get all high scores
    public List<Player> getAllHighScores() {
        List<Player> highScores = new ArrayList<>();
        String query = "SELECT p.name, hs.score, hs.level FROM high_scores hs JOIN players p ON hs.player_id = p.player_id ORDER BY hs.score DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                // Use the parameterized constructor
                Player player = new Player(rs.getString("name"), "", "", "");  // Add default values for password and level if needed
                player.setScore(rs.getInt("score"));
                player.setLevel(rs.getString("level"));
                highScores.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScores;
    }

    public Report getHighestScore() {
    String query = "SELECT p.name, hs.score FROM high_scores hs JOIN players p ON hs.player_id = p.player_id ORDER BY hs.score DESC LIMIT 1";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Report report = new Report();
            report.setPlayerName(rs.getString("name"));
            report.setScore(rs.getInt("score"));
            return report;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
