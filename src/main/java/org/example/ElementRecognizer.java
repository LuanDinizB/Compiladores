package org.example;

import java.util.*;

public class ElementRecognizer {
    private static final Set<String> instrumentos = new HashSet<>(Set.of(
            "violão", "piano", "teclado", "guitarra", "bateria", "flauta", "violino", "saxofone", "clarinete", "trompete"));
    private static final Set<String> generos = new HashSet<>(Set.of(
            "rock", "jazz", "clássico", "blues", "pop", "samba", "MPB", "bossa nova", "eletrônica", "folk"));
    private static final Set<String> metodos = new HashSet<>(Set.of(
            "escalas", "acordes", "partituras", "improvisação", "técnicas básicas"));

    public static String identifyType(String word) {
        if (instrumentos.contains(word)) {
            return "<instrumento>";
        } else if (generos.contains(word)) {
            return "<gênero>";
        } else if (metodos.contains(word)) {
            return "<método>";
        }
        return null;
    }

    public static String identifyElement(String word) {
        if (instrumentos.contains(word)) {
            return "<instrumento>";
        } else if (generos.contains(word)) {
            return "<gênero>";
        } else if (metodos.contains(word)) {
            return "<método>";
        }
        return null;
    }
}
