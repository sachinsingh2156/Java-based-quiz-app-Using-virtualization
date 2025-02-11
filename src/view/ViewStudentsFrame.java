package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import db.PlayerDAO;
import model.Player;
import java.awt.*;
import java.util.List;

public class ViewStudentsFrame extends JFrame {

    public ViewStudentsFrame() {
        setTitle("View Students");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Table to display student details
        String[] columnNames = {"Student ID", "Name", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable studentTable = new JTable(tableModel);

        // Fetch student data from the database
        PlayerDAO playerDAO = new PlayerDAO();
        List<Player> students = playerDAO.getAllPlayers(); // Assuming `getAllPlayers` is implemented

        // Populate the table with student data
        for (Player student : students) {
            Object[] row = {student.getPlayerId(), student.getName(), student.getUsername()};
            tableModel.addRow(row);
        }

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(studentTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 153, 153));
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        backButton.addActionListener(e -> dispose()); // Close the current frame
        mainPanel.add(backButton, BorderLayout.SOUTH);
    }
}
