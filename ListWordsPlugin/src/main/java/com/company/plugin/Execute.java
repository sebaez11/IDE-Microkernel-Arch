package com.company.plugin;

import com.company.api.IPlugin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Execute implements IPlugin {

    private static final List<String> CONNECTORS = Arrays.asList(
            // Conectores en inglés
            "and", "or", "but", "so", "because", "although", "though", "while", "if", "unless",
            "also", "besides", "furthermore", "moreover", "however", "nevertheless", "otherwise", "therefore",
            // Conectores en español
            "y", "o", "pero", "así", "porque", "aunque", "si", "sino", "también", "además", "más", "sin embargo",
            "no obstante", "por lo tanto", "en primer lugar", "en segundo lugar", "en tercer lugar", "por un lado", 
            "por otro lado", "a diferencia de", "en cambio", "en contraste", "por el contrario", "al contrario", "a pesar de", 
            "a pesar de que", "en cuanto a", "en relación a", "en términos de", "con respecto a", "en vista de", "desde mi punto de vista", 
            "desde mi perspectiva", "según", "a modo de ejemplo", "como resultado", "de esta manera", "de igual manera", "en consecuencia", 
            "por lo tanto", "por ende", "por consiguiente", "a causa de", "debido a", "gracias a", "por ese motivo", "por esa razón", "en otras palabras", 
            "en pocas palabras", "por último", "finalmente", "para concluir", "en conclusión", "en resumen", "en síntesis", "en definitiva"
    );

    @Override
    public void execute(JTextArea sourceCode, JTextArea messagesTextArea, JTextPane processedFileTextArea) {
        String text = sourceCode.getText();

        // Obtener todas las palabras sin repetir excluyendo conectores
        List<String> uniqueWords = getUniqueWords(text);

        StringBuilder resultBuilder = new StringBuilder();
        for (String word : uniqueWords) {
            resultBuilder.append(word).append("\n");
        }

        processedFileTextArea.setText(resultBuilder.toString());
        messagesTextArea.setText("Se han listado correctamente las palabras sin repetir (excluyendo conectores).");
    }

    private List<String> getUniqueWords(String text) {
        List<String> words = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\b\\p{L}+\\b"); // Coincide con palabras en cualquier idioma
        Matcher matcher = pattern.matcher(text.toLowerCase());

        while (matcher.find()) {
            String word = matcher.group();
            if (!CONNECTORS.contains(word)) {
                if (!words.contains(word)) {
                    words.add(word);
                }
            }
        }

        return words;
    }
}
