package model;

public class Player {
    private int playerId;
    private String username;
    private String password;
    private String name;
    private String level;
    private int score;

    // No-argument constructor
    public Player() {
    }

    // Constructor with parameters
    public Player(String username, String password, String name, String level) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.level = level;
    }

    // Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
