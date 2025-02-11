package model;

public class HighScore {
    private int highScoreId;
    private int playerId;
    private int score;
    private String level;

    // Constructor
    public HighScore(int playerId, int score, String level) {
        this.playerId = playerId;
        this.score = score;
        this.level = level;
    }

    // Getters and Setters
    public int getHighScoreId() {
        return highScoreId;
    }

    public void setHighScoreId(int highScoreId) {
        this.highScoreId = highScoreId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
