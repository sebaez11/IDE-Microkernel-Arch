/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.editor.controllers;

import com.editor.services.ComponentService;
import com.editor.views.EditorPanel;

/**
 *
 * @author sebae
 */
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController() {
        this.componentService = new ComponentService();
    }

    public void load(EditorPanel editorPanel) {
        componentService.load(editorPanel);
    }

    public void execute(EditorPanel editorPanel) {
        componentService.execute(editorPanel);
    }

    public void list(EditorPanel editorPanel) {
        componentService.list(editorPanel);
    }
}
