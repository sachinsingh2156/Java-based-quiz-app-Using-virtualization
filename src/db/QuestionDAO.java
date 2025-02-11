package db;

import model.Question;
import java.sql.*;
import java.util.*;

public class QuestionDAO {

    // Method to add a question to the database
    public boolean addQuestion(Question question) {
        String query = "INSERT INTO questions (question, option1, option2, option3, option4, correct_answer, difficulty) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, question.getQuestion());
            stmt.setString(2, question.getOption1());
            stmt.setString(3, question.getOption2());
            stmt.setString(4, question.getOption3());
            stmt.setString(5, question.getOption4());
            stmt.setInt(6, question.getCorrectAnswer());
            stmt.setString(7, question.getDifficulty());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing question
    public boolean updateQuestion(Question question) {
        String query = "UPDATE questions SET question = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_answer = ?, difficulty = ? WHERE question_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, question.getQuestion());
            stmt.setString(2, question.getOption1());
            stmt.setString(3, question.getOption2());
            stmt.setString(4, question.getOption3());
            stmt.setString(5, question.getOption4());
            stmt.setInt(6, question.getCorrectAnswer());
            stmt.setString(7, question.getDifficulty());
            stmt.setInt(8, question.getQuestionId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a question
    public boolean deleteQuestion(int questionId) {
        String query = "DELETE FROM questions WHERE question_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, questionId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all questions for a specific difficulty level
    public List<Question> getQuestionsByDifficulty(String difficulty) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE difficulty = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, difficulty);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Question question = new Question(
                        rs.getInt("question_id"),
                        rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getInt("correct_answer"),
                        rs.getString("difficulty")
                );
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
