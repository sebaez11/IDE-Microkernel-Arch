/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.editor.controllers;

import com.editor.services.InitialFileService;
import com.editor.views.EditorPanel;


/**
 *
 * @author sebae
 */
public class InitialFileController {

        private final InitialFileService initialFileService;

        public InitialFileController(){
            this.initialFileService = new InitialFileService();
        }

        public void loadInitialFile(EditorPanel editorPanel){
            initialFileService.load(editorPanel);
        }

}
