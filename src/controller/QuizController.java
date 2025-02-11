package controller;

import model.*;
import db.*;
import java.util.*;

public class QuizController {

    // Method to fetch all questions for a specific difficulty level
    public List<Question> getQuestionsByLevel(String difficulty) {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.getQuestionsByDifficulty(difficulty);
    }

    // Method to calculate the score for the quiz
    public int calculateScore(List<Question> questions, List<Integer> answers) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrectAnswer() == answers.get(i)) {
                score++;
            }
        }
        return score;
    }
}

