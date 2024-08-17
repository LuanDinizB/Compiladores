package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class TFIDFCalculator {
    private final InvertedIndex invertedIndex;
    private final Map<String, String> symbolTable;
    private final int totalDocuments;

    public TFIDFCalculator(InvertedIndex invertedIndex, Map<String, String> symbolTable, int totalDocuments) {
        this.invertedIndex = invertedIndex;
        this.symbolTable = symbolTable;
        this.totalDocuments = totalDocuments;
    }

    public String calculateBestMatch() {
        Map<String, Double> scores = new HashMap<>();
        for (String term : symbolTable.keySet()) {
            if (invertedIndex.getIndex().containsKey(term)) {
                Map<String, Integer> docMap = invertedIndex.getIndex().get(term);
                double idf = calculateIDF(docMap.size());
                for (Map.Entry<String, Integer> entry : docMap.entrySet()) {
                    String doc = entry.getKey();
                    int termFrequency = entry.getValue();
                    double tfidf = calculateTFIDF(termFrequency, idf);
                    scores.put(doc, scores.getOrDefault(doc, 0.0) + tfidf);
                }
            }
        }

        if (scores.isEmpty()) {
            return null;
        }

        return scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Desculpe, não consegui encontrar uma resposta.");
    }

    private double calculateTFIDF(int termFrequency, double idf) {
        return termFrequency * idf;
    }

    private double calculateIDF(int docFrequency) {
        return Math.log((double) totalDocuments / (docFrequency + 1)) + 1;
    }
}
