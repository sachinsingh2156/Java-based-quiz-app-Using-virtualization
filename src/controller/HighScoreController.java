package controller;

import db.HighScoreDAO;
import model.Player;
import java.util.List;

public class HighScoreController {

    // Method to fetch high scores by difficulty level
    public List<Player> getHighScoresByLevel(String level) {
        HighScoreDAO highScoreDAO = new HighScoreDAO();
        return highScoreDAO.getHighScoresByLevel(level);
    }

    // Method to add a high score to the database
    public boolean addHighScore(int playerId, int score, String level) {
        HighScoreDAO highScoreDAO = new HighScoreDAO();
        return highScoreDAO.addHighScore(playerId, score, level);
    }

    // Method to fetch all high scores
    public List<Player> getAllHighScores() {
        HighScoreDAO highScoreDAO = new HighScoreDAO();
        return highScoreDAO.getAllHighScores();
    }
}
