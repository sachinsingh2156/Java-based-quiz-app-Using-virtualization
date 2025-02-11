package controller;

import model.*;
import db.*;
import java.sql.*;

public class AdminController {

    // Method for admin login validation
    public boolean loginAdmin(String username, String password) {
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // Password should be hashed in real apps
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If admin exists, return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a new question
    public boolean addQuestion(Question question) {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.addQuestion(question);  // Ensure this method is properly calling the DAO
    }

    // Method to update an existing question
    public boolean updateQuestion(Question question) {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.updateQuestion(question);
    }

    // Method to delete a question
    public boolean deleteQuestion(int questionId) {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.deleteQuestion(questionId);
    }
}
