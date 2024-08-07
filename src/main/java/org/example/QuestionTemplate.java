package org.example;

import java.util.*;

public class QuestionTemplate {
    public static final Map<String, String> perguntas = Map.of(
            "quantas notas estão presentes em um piano padrão?", "<instrumento>",
            "quero comprar um", "<instrumento>",
            "qual é o melhor tipo de", "<instrumento>",
            "como devo começar a aprender a tocar", "<instrumento>"
    );

    public static final Map<String, String> atribuicoes = Map.of(
            "quero um", "<instrumento>",
            "recomendo um", "<instrumento>",
            "sim, quero começar com um", "<instrumento>",
            "escolhi um", "<instrumento>",
            "quero informações sobre o", "<instrumento>",
            "quero aprender a tocar", "<gênero>",
            "você pode começar estudando", "<método>",
            "prefiro estudar", "<método>",
            "meu gênero musical preferido é", "<gênero>"
    );
}
