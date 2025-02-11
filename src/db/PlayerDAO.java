package db;

import model.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    // Method to check if the username is already taken
    public boolean isUsernameAvailable(String username) {
        String query = "SELECT * FROM players WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            // If a player with this username exists, return false (username is not available)
            return !rs.next();  // If rs.next() is false, the username is available
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        }
    }

    // Method to register a new player
    public boolean registerPlayer(Player player) {
        String query = "INSERT INTO players (username, password, name, level) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, player.getUsername());
            stmt.setString(2, player.getPassword()); // Ensure password is hashed if required
            stmt.setString(3, player.getName());
            stmt.setString(4, player.getLevel());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to login a player
    public int loginPlayer(String username, String password) {
        String query = "SELECT player_id FROM players WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // In real applications, use hashed passwords
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("player_id"); // Return the player's ID if login is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if login fails
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String query = "SELECT player_id, name, username FROM players";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Player player = new Player();
                player.setPlayerId(rs.getInt("player_id"));
                player.setName(rs.getString("name"));
                player.setUsername(rs.getString("username"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
    public String getPlayerDifficulty(int playerId) {
        String query = "SELECT level FROM players WHERE player_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return rs.getString("level"); // Fetch the difficulty level dynamically
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Beginner"; // Default to Beginner if no level is found
    }
    
}


