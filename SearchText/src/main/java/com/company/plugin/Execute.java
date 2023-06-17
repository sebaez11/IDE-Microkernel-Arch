/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.plugin;

import com.company.api.IPlugin;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class Execute implements IPlugin {

    @Override
    public void execute(JTextArea sourceCode, JTextArea messagesTextArea, JTextPane processedFileTextArea) {
        // Obtener el texto de sourceCode
        String code = sourceCode.getText();

        // Crear una ventana de entrada de texto para que el usuario ingrese el texto a buscar
        String searchText = JOptionPane.showInputDialog(null, "Ingrese el texto a buscar:");

        // Validar si se ingresó un texto para buscar
        if (searchText == null || searchText.trim().isEmpty()) {
            messagesTextArea.setText("No se ingresó texto a buscar.");
            return;
        }

        // Buscar el texto dentro del código fuente
        StringBuilder result = new StringBuilder();
        String[] lines = code.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int index = line.indexOf(searchText);
            int columnNumber = 0;

            while (index >= 0) {
                int lineNumber = i + 1; // Sumar 1 para mostrar el número de línea correcto
                columnNumber = index + 1; // Sumar 1 para mostrar la columna correcta

                result.append("El texto \"").append(searchText)
                        .append("\" fue encontrado en la línea ").append(lineNumber)
                        .append(", columna ").append(columnNumber).append("\n");

                index = line.indexOf(searchText, index + 1);
            }
        }

        // Mostrar el resultado en el campo de texto "processedFileTextArea"
        if (result.length() > 0) {
            processedFileTextArea.setText(result.toString());
            messagesTextArea.setText("El filtrado de texto se ha realizado correctamente.");

        } else {
            messagesTextArea.setText("No se encontraron coincidencias del texto \"" + searchText + "\".");
        }
        
        
    }

}


