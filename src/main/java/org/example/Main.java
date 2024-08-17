package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        UserInteraction userInteraction = new UserInteraction();

        String input;
        while (true) {
            System.out.println("Digite uma pergunta ou atribuição:");
            System.out.println("Digite SAIR para sair:");
            input = scn.nextLine().trim();

            userInteraction.processInput(input);
        }

    }
}
