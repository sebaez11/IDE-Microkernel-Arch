/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.company.plugin;

import com.company.api.IPlugin;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Execute implements IPlugin {

    private static final List<String> JAVA_KEYWORDS = Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile",
            "while"
    );

   @Override
    public void execute(JTextArea sourceCode, JTextArea messagesTextArea, JTextPane proccessedFileTextArea) {
        String text = sourceCode.getText();
        StyledDocument styledDocument = new DefaultStyledDocument();

        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
        AttributeSet keywordStyle = styleContext.addAttribute(defaultStyle, StyleConstants.Foreground, Color.BLUE);

        proccessedFileTextArea.setText(""); // Limpiar el texto del área de procesado

        try {
            styledDocument.insertString(0, text, defaultStyle);

            for (String keyword : JAVA_KEYWORDS) {
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b");
                Matcher matcher = pattern.matcher(text);

                while (matcher.find()) {
                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    styledDocument.setCharacterAttributes(startIndex, endIndex - startIndex, keywordStyle, false);
                }
            }
            proccessedFileTextArea.setDocument(styledDocument);
            messagesTextArea.setText("El archivo se ha resaltado correctamente");

        } catch (BadLocationException e) {
            messagesTextArea.setText("Error al resaltar las palabras");
            e.printStackTrace();
        }
    }
}
