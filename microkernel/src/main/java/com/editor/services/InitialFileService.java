/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.editor.services;

import com.editor.utils.FileUtils;
import com.editor.views.EditorPanel;
import java.io.File;
import java.io.IOException;

public class InitialFileService {

    private final FileUtils fileUtils;

    public InitialFileService() {
        this.fileUtils = new FileUtils();

    }

    public void load(EditorPanel editorPanel) {
        File file = fileUtils.showFileChooser(editorPanel);
        if (file == null) {
            return;
        }

        try {
            String content = fileUtils.readFileContent(file);
            editorPanel.initialFileContent.setText(content);
        } catch (IOException ex) {
            String errorMessage = "Error al leer el archivo: " + ex.getMessage();
            System.out.println(errorMessage);
        }
    }

}
