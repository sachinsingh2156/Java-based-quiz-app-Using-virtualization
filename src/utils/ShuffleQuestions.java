package utils;

import model.Question;
import java.util.Collections;
import java.util.List;

public class ShuffleQuestions {

    // Method to shuffle a list of questions
    public static void shuffle(List<Question> questions) {
        Collections.shuffle(questions);
    }
}
