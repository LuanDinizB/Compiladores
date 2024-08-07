package org.example;

import java.io.*;
import java.util.*;

public class InvertedIndex {
    private final Map<String, Map<String, Integer>> index = new HashMap<>();

    public void loadIndex(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String term = parts[0];
                    String[] postings = parts[1].split(",");
                    Map<String, Integer> docMap = new HashMap<>();
                    for (String posting : postings) {
                        String[] docParts = posting.split("-");
                        if (docParts.length == 2) {
                            docMap.put(docParts[0], Integer.parseInt(docParts[1]));
                        }
                    }
                    index.put(term, docMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveIndex(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Map<String, Integer>> entry : index.entrySet()) {
                String term = entry.getKey();
                Map<String, Integer> docMap = entry.getValue();
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Integer> docEntry : docMap.entrySet()) {
                    sb.append(docEntry.getKey()).append("-").append(docEntry.getValue()).append(",");
                }
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1); // Remove the trailing comma
                }
                bw.write(term + ":" + sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEntry(String term, List<String> documents) {
        Map<String, Integer> docMap = index.getOrDefault(term, new HashMap<>());
        for (String doc : documents) {
            docMap.put(doc, docMap.getOrDefault(doc, 0) + 1);
        }
        index.put(term, docMap);
    }

    public Map<String, Map<String, Integer>> getIndex() {
        return index;
    }

    public void indexDocuments(String documentsDir) {
        File dir = new File(documentsDir);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        indexTerm(line, file.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void indexTerm(String line, String filename) {
        for (String term : ElementRecognizer.getAllTerms()) {
            if (line.toLowerCase().contains(term.toLowerCase())) {
                addEntry(term, Collections.singletonList(filename));
            }
        }
    }
}
