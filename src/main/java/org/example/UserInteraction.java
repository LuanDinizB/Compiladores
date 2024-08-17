package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserInteraction {
    private static final Map<String, String> symbolTable = new LinkedHashMap<>();
    private static final Set<String> requiredElements = new HashSet<>(Set.of("<instrumento>", "<gênero>", "<método>"));
    private static final String INVERTED_INDEX_FILE = "src/files/inverted_index.txt";
    private static final String DOCUMENTS_DIR = "src/files/documents/";
    private static final InvertedIndex invertedIndex = new InvertedIndex();
    private static Scanner scn = new Scanner(System.in);

    public UserInteraction() {
        // Index documents when the UserInteraction is initialized
        invertedIndex.indexDocuments(DOCUMENTS_DIR);
        invertedIndex.saveIndex(INVERTED_INDEX_FILE);
    }

    public void processInput(String input) {
        if(Objects.equals(input, "sair")) {
            System.out.println("Até mais!");
            System.exit(0);
        }
        String response = processPartialInput(input);
        System.out.println(response);
        if (response.equals("Elemento adicionado à tabela de símbolos: " + getSymbolTable())) {
            // Update the inverted index if needed
            updateInvertedIndex();
        }
        String answer = provideAnswer();
        System.out.println(answer);
    }

    private String processPartialInput(String input) {
        String keyword = extractKeyword(input);
        if (keyword != null) {
            String type = ElementRecognizer.identifyType(keyword);
            if (type != null) {
                symbolTable.put(keyword, type);
                return "Entendi que estamos falando de " + keyword + ".\nTabela de simbolos: " + getSymbolTable();
            }
        }
        return handleIncompleteInput(input);
    }

    private String handleIncompleteInput(String input) {
        for (String question : QuestionTemplate.perguntas.keySet()) {
            if (question.toLowerCase().contains(input.toLowerCase())) {
                return askForMissingElement(question, input, "questao");
            }
        }
        for (String atribuicao : QuestionTemplate.atribuicoes.keySet()) {
            if (atribuicao.toLowerCase().contains(input.toLowerCase())) {
                return askForMissingElement(atribuicao, input, "atribuicao");
            }
        }
        return "Não entendi.";
    }

    private String extractKeyword(String input) {
        String[] words = input.toLowerCase().split("\\s+");
        for (String word : words) {
            String type = ElementRecognizer.identifyElement(word);
            if (type != null) {
                return word;
            }
        }
        return null;
    }

    private String askForMissingElement(String fullText, String partialInput, String tipo) {
        Set<String> fullTextWords = new HashSet<>(Arrays.asList(fullText.toLowerCase().split("\\s+")));
        Set<String> partialTextWords = new HashSet<>(Arrays.asList(partialInput.toLowerCase().split("\\s+")));
        Set<String> missingKeyWords = findMissingKeywords(fullTextWords, partialTextWords);

        List<String> missingElements = new ArrayList<>();
        String missingElementType = null;

        for (String keyword : missingKeyWords) {
            String elementType = ElementRecognizer.identifyType(keyword);
            if (elementType != null) {
                missingElements.add(elementType);
                symbolTable.put(tipo, keyword);
                missingElementType = elementType;
            }
        }

        if (missingElements.isEmpty()) {
            // Quando o texto é reconhecido corretamente, pergunta o que o usuário deseja
            return askForElement();
        } else {
            return "Qual " + missingElementType + " você deseja?";
        }
    }

    private String askForElement() {
        System.out.print("Qual instrumento você deseja? ");
        String response = scn.nextLine().trim();
        String type = ElementRecognizer.identifyType(response.toLowerCase());
        if (type != null) {
            symbolTable.put(response, type);
            return "Elemento adicionado à tabela de símbolos: " + getSymbolTable();
        } else {
            return "Desculpe, não reconheci o instrumento. Tente novamente.";
        }
    }

    private Set<String> findMissingKeywords(Set<String> fullTextWords, Set<String> partialTextWords) {
        Set<String> missingKeywords = new HashSet<>(fullTextWords);
        missingKeywords.removeAll(partialTextWords);
        return missingKeywords;
    }

    private String getSymbolTable() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : symbolTable.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        return "[" + sb.toString() + "]";
    }

    private void updateInvertedIndex() {
        invertedIndex.indexDocuments(DOCUMENTS_DIR);
        invertedIndex.saveIndex(INVERTED_INDEX_FILE);
    }

    private String provideAnswer() {
        invertedIndex.loadIndex(INVERTED_INDEX_FILE);
        TFIDFCalculator tfidfCalculator = new TFIDFCalculator(invertedIndex, symbolTable, getTotalDocuments());
        String bestMatch = tfidfCalculator.calculateBestMatch();
        if(bestMatch == null) {
            return "Desculpe, não consegui encontrar uma resposta.";
        }
        return "A resposta padrão mais provável é: " + readDocument(bestMatch);
    }

    private int getTotalDocuments() {
        File dir = new File(DOCUMENTS_DIR);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        return files != null ? files.length : 0;
    }

    public static String readDocument(String docName) {
        StringBuilder content = new StringBuilder();
        File file = new File("src/files/documents/" + docName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o documento: " + e.getMessage());
        }

        return content.toString();
    }
}
