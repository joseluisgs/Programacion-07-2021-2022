package com.example.holamundojavafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    private int clicCounter = 0;
    @FXML
    private TextField txtName;
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnHola;


    @FXML
    protected void onHelloButtonClick() {
        String name = txtName.getText();
        welcomeText.setText("Hola " + name);
        clicCounter++;
        btnHola.setText("Hola: " + clicCounter);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hola");
        alert.setHeaderText("Hola " + name);
        alert.show();
    }
}