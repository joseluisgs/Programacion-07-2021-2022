package es.joseluisgs.changescenes.controllers;

import es.joseluisgs.changescenes.SceneManager.SceneManager;
import es.joseluisgs.changescenes.ScenesApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.io.IOException;

public class Scene02Controller {
    @FXML
    private Label welcomeText;

    // Iniciamos las cosas
    @FXML
    public void initialize() {
        welcomeText.setText("Soy la Scene02");
    }


    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        System.out.println(event.toString());
        welcomeText.setText("Vamos a cambiar de escena");
        System.out.println(ScenesApplication.class.getResource("views/scene01-view.fxml"));
        SceneManager.get().changeScene((Node) event.getSource(), "views/scene01-view.fxml");

    }
}