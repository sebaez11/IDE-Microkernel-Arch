package com.editor.services;

import com.company.api.IPlugin;
import com.editor.utils.FileUtils;
import com.editor.views.EditorPanel;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.DefaultListModel;

public class ComponentService {

    // Agregar el nombre del archivo a loadedComponentsList
    DefaultListModel<String> listModel = new DefaultListModel<>();
    private final String folderPath = "plugins";
    private final FileUtils fileUtils;

    public ComponentService() {
        this.fileUtils = new FileUtils();
    }

    public void list(EditorPanel editorPanel) {
        File pluginsFolder = new File(folderPath);

        if (!pluginsFolder.exists() || !pluginsFolder.isDirectory()) {
            editorPanel.messagesTextArea.setText("La carpeta 'plugins' no existe.");
            return;
        }

        File[] files = pluginsFolder.listFiles();
        if (files == null || files.length == 0) {
            editorPanel.messagesTextArea.setText("No se encontraron archivos en la carpeta 'plugins'.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                listModel.addElement(fileName);
            }
        }
        editorPanel.loadedComponentsList.setModel(listModel);
        editorPanel.messagesTextArea.setText("Se cargaron " + listModel.getSize() + " archivos desde la carpeta 'plugins'.");
    }

    public void load(EditorPanel editorPanel) {
        File selectedFile = fileUtils.showFileChooser(editorPanel);
        if (selectedFile == null) {
            return;
        }

        String fileName = selectedFile.getName();
        if (!fileUtils.isJarFile(fileName)) {
            editorPanel.messagesTextArea.append("Solo se permiten archivos con extensión .jar");
            return;
        }

        // Eliminar el archivo si ya existe
        File targetFile = new File(folderPath, fileName);
        if (targetFile.exists()) {
            if (!targetFile.delete()) {
                editorPanel.messagesTextArea.setText("El plugin se esta ejecutando, no se puede eliminar");
                return;
            }
        }
        if (fileUtils.copyFileToPluginsFolder(selectedFile, folderPath, fileName)) {
            editorPanel.messagesTextArea.setText("Plugin cargado correctamente");
            if (!listModel.contains(fileName)) {
                listModel.addElement(fileName);
                editorPanel.loadedComponentsList.setModel(listModel);
            }
        } else {
            editorPanel.messagesTextArea.setText("Error al cargar plugin, el plugin se esta ejecutando");
        }
    }

    public File searchPluginFile(String nameFile) {
        File pluginsFolder = new File(folderPath);
        File[] files = pluginsFolder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equalsIgnoreCase(nameFile) && file.getName().endsWith(".jar")) {
                    return file;
                }
            }
        }

        return null;  // Si no se encuentra el archivo JAR
    }

    public void execute(EditorPanel editorPanel) {

        editorPanel.messagesTextArea.setText("");

        if (editorPanel.loadedComponentsList.getSelectedValue() == null) {
            editorPanel.messagesTextArea.setText("Error al cargar el plugin : No existe plugin");
            return;
        }

        File filePlugin = searchPluginFile(editorPanel.loadedComponentsList.getSelectedValue());
        try {

            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{filePlugin.toURI().toURL()});

            Class<?> loadedClass = classLoader.loadClass("com.company.plugin.Execute");

            // Crea una instancia de la clase y llama a su método "ejecutar()"
            IPlugin instancePlugin = (IPlugin) loadedClass.getDeclaredConstructor().newInstance();
            instancePlugin.execute(editorPanel.initialFileContent, editorPanel.messagesTextArea, editorPanel.proccessedFileTextArea);

        } catch (Exception e) {
            editorPanel.messagesTextArea.append("Error al ejecutar la clase desde el JAR");
        }

    }

}
