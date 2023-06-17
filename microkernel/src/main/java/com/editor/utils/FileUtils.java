/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.editor.utils;

import com.editor.views.EditorPanel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;

public class FileUtils {
    public File showFileChooser(EditorPanel editorPanel) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(editorPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public String readFileContent(File file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }

    public boolean isJarFile(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
        return fileExtension.equalsIgnoreCase("jar");
    }

    public boolean copyFileToPluginsFolder(File selectedFile, String folderPath, String fileName) {
        File pluginsFolder = new File(folderPath);
        if (!pluginsFolder.exists()) {
            if (pluginsFolder.mkdirs()) {
                System.out.println("Carpeta 'plugins' creada correctamente.");
            } else {
                System.out.println("Error al crear la carpeta 'plugins'.");
                return false;
            }
        }

        File destinationFile = new File(folderPath + File.separator + fileName);
        try {
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("Error al cargar plugin: " + e.getMessage());
            return false;
        }
    }
}
