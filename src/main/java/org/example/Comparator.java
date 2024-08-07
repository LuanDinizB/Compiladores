package org.example;

import java.util.*;

public class Comparator {

    public static List<String> checkSimilarity(List<String> words) {
        List<String> uniqueWords = new ArrayList<>();
        Set<String> processed = new HashSet<>();
        boolean similarFound;
        double jaccardMinimum = 0.5;

        for (String word : words) {
            if (!processed.contains(word)) {
                similarFound = false;
                for (String uniqueWord : uniqueWords) {
                    double jaccard = calcularJaccard(word, uniqueWord);
                    if (jaccard >= jaccardMinimum) {
                        similarFound = true;
                        break;
                    }
                }
                if (!similarFound) {
                    uniqueWords.add(word);
                }
                processed.add(word);
            }
        }
        return uniqueWords;
    }

    private static double calcularJaccard(String firstWord, String secondWord) {
        Set<Character> groupOne = new HashSet<>();
        Set<Character> groupTwo = new HashSet<>();

        for (char c : firstWord.toCharArray()) {
            groupOne.add(c);
        }

        for (char c : secondWord.toCharArray()) {
            groupTwo.add(c);
        }

        int intersection = 0;
        int union = groupOne.size() + groupTwo.size();

        for (char c : groupOne) {
            if (groupTwo.contains(c)) {
                intersection++;
                union--;
            }
        }
        return (double) intersection / union;
    }
}
