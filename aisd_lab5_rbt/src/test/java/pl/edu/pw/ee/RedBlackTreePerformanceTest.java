package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static pl.edu.pw.ee.utils.DataIO.prepareListOfWords;
import static pl.edu.pw.ee.utils.DataIO.saveMeasuredHeight;

public class RedBlackTreePerformanceTest {

    @Test
    public void generateTimeResultsAcordingToNumsOfItems() {
        int stepToMeasure = 100;

        generateAndSaveTimeResultsWithAscItems(stepToMeasure);
        generateAndSaveTimeResultsWithDescItems(stepToMeasure);
        generateAndSaveTimeResultsWithRendItems(stepToMeasure);

    }

    private static void generateAndSaveTimeResultsWithAscItems(int stepToMeasure) {
        String resultFilename = "rbt_height_ascending.csv";
        String[] words = prepareListOfWords();

        List<String> rbtHeightResults = putAllWordsAndMeasureHeightEveryNumOfWords(words, stepToMeasure);

        saveMeasuredHeight(rbtHeightResults, resultFilename);
    }

    private static void generateAndSaveTimeResultsWithDescItems(int stepToMeasure) {
        String resultFilename = "rbt_heigth_descending.csv";
        String[] words = prepareListOfWords();
        String[] dscWords = reverseArray(words);

        List<String> rbtHeightResults = putAllWordsAndMeasureHeightEveryNumOfWords(dscWords, stepToMeasure);

        saveMeasuredHeight(rbtHeightResults, resultFilename);
    }
    
    private static void generateAndSaveTimeResultsWithRendItems(int stepToMeasure) {
        String resultFilename = "rbt_height_shuffled.csv";
        String[] words = prepareListOfWords();
        Collections.shuffle(Arrays.asList(words));

        List<String> rbtHeightResults = putAllWordsAndMeasureHeightEveryNumOfWords(words, stepToMeasure);

        saveMeasuredHeight(rbtHeightResults, resultFilename);
    }

    private static List<String> putAllWordsAndMeasureHeightEveryNumOfWords(String[] words, int stepToMeasure) {
        validateInputs(words, stepToMeasure);

        RedBlackTree<String, Integer> rbt = new RedBlackTree<>();
        int index = 0;
        int putCounter;
        List<String> hightResults = new ArrayList<>();

        for (String word : words) {
            putCounter = rbt.put(word, index++);

            if (index % stepToMeasure == 0) {
                hightResults.add(String.format("%d,%d", index, putCounter));
            }
        }

        return hightResults;
    }

    private static void validateInputs(String[] words, int stepToMeasure) {
        if (words == null) {
            throw new IllegalArgumentException("List of words cannot be null!");
        }
        if (stepToMeasure < 1) {
            throw new IllegalArgumentException("StepToMeasure value cannot be less than zero!");
        }
    }

    private static String [] reverseArray(String[] words) {
        int size = words.length;
        String[] tmp = new String[size];
        for (int i = 0; i < size; i++) {
            tmp[i] = words[size - i - 1];
        }
        return tmp;
    }

}
