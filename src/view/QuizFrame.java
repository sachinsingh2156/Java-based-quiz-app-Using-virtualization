package view;

import javax.swing.*;
import controller.QuizController;
import db.PlayerDAO;
import db.ReportDAO;
import model.Question;
import model.Report;

import java.awt.*;
import java.util.List;

public class QuizFrame extends JFrame {

    private int playerId;
    private String difficulty; // Store the player's difficulty level
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int correctAnswers = 0;
    private ButtonGroup buttonGroup;

    public QuizFrame(int playerId) {
        this.playerId = playerId;

        // Fetch player's difficulty level dynamically
        PlayerDAO playerDAO = new PlayerDAO();
        this.difficulty = playerDAO.getPlayerDifficulty(playerId);

        setTitle("Quiz - " + difficulty + " Level");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fetch questions based on the player's level
        QuizController quizController = new QuizController();
        questions = quizController.getQuestionsByLevel(difficulty);

        if (questions == null || questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions available for this level.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        JLabel questionLabel = new JLabel("<html><h3>" + questions.get(currentQuestionIndex).getQuestion() + "</h3></html>");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JRadioButton option1 = new JRadioButton();
        JRadioButton option2 = new JRadioButton();
        JRadioButton option3 = new JRadioButton();
        JRadioButton option4 = new JRadioButton();

        buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        loadQuestionOptions(option1, option2, option3, option4);

        JPanel buttonPanel = new JPanel();
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(102, 178, 255));
        nextButton.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(nextButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> {
            Question currentQuestion = questions.get(currentQuestionIndex);

            if (option1.isSelected() && currentQuestion.getCorrectAnswer() == 1) {
                score += 10;
                correctAnswers++;
            } else if (option2.isSelected() && currentQuestion.getCorrectAnswer() == 2) {
                score += 10;
                correctAnswers++;
            } else if (option3.isSelected() && currentQuestion.getCorrectAnswer() == 3) {
                score += 10;
                correctAnswers++;
            } else if (option4.isSelected() && currentQuestion.getCorrectAnswer() == 4) {
                score += 10;
                correctAnswers++;
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                loadQuestionOptions(option1, option2, option3, option4);
                questionLabel.setText("<html><h3>" + questions.get(currentQuestionIndex).getQuestion() + "</h3></html>");
                buttonGroup.clearSelection();
            } else {
                saveReport();
                JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score);
                dispose();
                new PlayerDashboardFrame(playerId).setVisible(true);
            }
        });
    }

    private void loadQuestionOptions(JRadioButton option1, JRadioButton option2, JRadioButton option3, JRadioButton option4) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        option1.setText(currentQuestion.getOption1());
        option2.setText(currentQuestion.getOption2());
        option3.setText(currentQuestion.getOption3());
        option4.setText(currentQuestion.getOption4());
    }

    private void saveReport() {
        int totalQuestions = questions.size();
        int wrongAnswers = totalQuestions - correctAnswers;

        // Save report with correct difficulty level
        Report report = new Report(playerId, correctAnswers, wrongAnswers, score, difficulty);
        ReportDAO reportDAO = new ReportDAO();
        boolean isSaved = reportDAO.addReport(report);

        if (!isSaved) {
            JOptionPane.showMessageDialog(this, "Error saving score!");
        }
    }
}
