package view;

import javax.swing.*;
import controller.HighScoreController;
import model.Player;
import java.awt.*;
import java.util.List;

public class HighScoreFrame extends JFrame {

    public HighScoreFrame() {
        setTitle("High Scores");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        HighScoreController highScoreController = new HighScoreController();

        // Create the UI components
        JLabel levelLabel = new JLabel("Select Difficulty Level:");
        JComboBox<String> levelComboBox = new JComboBox<>(new String[] {"Beginner", "Intermediate", "Advanced"});
        JButton viewScoresButton = new JButton("View High Scores");

        // Layout
        setLayout(new FlowLayout());
        add(levelLabel);
        add(levelComboBox);
        add(viewScoresButton);

        // Display the scores in a list
        JList<String> highScoreList = new JList<>();
        add(new JScrollPane(highScoreList));

        viewScoresButton.addActionListener(e -> {
            String selectedLevel = (String) levelComboBox.getSelectedItem();
            List<Player> highScores = highScoreController.getHighScoresByLevel(selectedLevel);
        
            // Display the high scores in the list
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Player player : highScores) {
                listModel.addElement(player.getName() + " - " + player.getScore());
            }
            highScoreList.setModel(listModel);
        });
        
    }
}
