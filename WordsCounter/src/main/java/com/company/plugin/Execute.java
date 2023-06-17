/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.plugin;

import com.company.api.IPlugin;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Execute implements IPlugin {

    @Override
    public void execute(JTextArea sourceCode, JTextArea messagesTextArea, JTextPane processedFileTextArea) {
        String content = sourceCode.getText();

        // Eliminar caracteres especiales y convertir todo a minúsculas
        content = content.replaceAll("[^a-zA-Z0-9] ", "").toLowerCase();

        // Dividir el contenido en palabras
        String[] words = content.split("\\s+");

        // Contar la frecuencia de cada palabra
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Generar el resultado
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            result.append(word).append(": ").append(count).append("\n");
        }

        // Mostrar el resultado en el campo de texto "processedFileTextArea"
        processedFileTextArea.setText(result.toString());
        
        messagesTextArea.setText("El conteo de palabras se ha realizado correctamente");

    }  

}
