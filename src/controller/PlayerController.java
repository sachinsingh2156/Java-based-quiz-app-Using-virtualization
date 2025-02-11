package controller;

import model.*;
import db.*;

public class PlayerController {

    // Method to register a new player
    public boolean registerPlayer(Player player) {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.registerPlayer(player);
    }

    // Method to log in an existing player
    public int loginPlayer(String username, String password) {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.loginPlayer(username, password); // Return playerId instead of boolean
    }
}


