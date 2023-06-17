/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.api;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public interface IPlugin {
    public void execute(JTextArea sourceCode ,JTextArea messagesTextArea, JTextPane proccessedFileTextArea);
}
