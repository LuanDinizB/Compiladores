package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DocumentGenerator {
    private static final String DIRECTORY = "src/files/documents/";

    public static void main(String[] args) {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String[] documents = {
            "piano é um instrumento musical que possui teclas. O piano é muito usado em músicas clássicas.",
            "violão é um instrumento de cordas amplamente utilizado em diversos gêneros musicais como rock e MPB.",
            "a bateria é um conjunto de tambores e pratos usados em muitas bandas. Bateristas são essenciais em bandas de rock.",
            "o saxofone é um instrumento de sopro criado por Adolphe Sax. É comum em jazz e música clássica.",
            "o teclado é semelhante ao piano, mas com sons eletrônicos. Usado em muitos gêneros musicais, incluindo pop e eletrônica."
        };

        for (int i = 0; i < documents.length; i++) {
            String fileName = "doc" + (i + 1) + ".txt";
            File file = new File(DIRECTORY + fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(documents[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Documentos gerados com sucesso!");
    }
}
