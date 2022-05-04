package es.joseluisgs.agenda.controllers;

import es.joseluisgs.agenda.AgendaApplication;
import es.joseluisgs.agenda.managers.SceneManager;
import es.joseluisgs.agenda.views.Views;
import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SplashController implements Initializable {
    @FXML
    private ImageView fondo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Una animación de fondo
        FadeTransition transition = new FadeTransition(Duration.millis(3000),fondo);
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(event -> {
            // Nos cerramos
            Stage ventana = (Stage) fondo.getScene().getWindow();
            ventana.hide();
            // Mostramos la ventana principal
            SceneManager sceneManager = SceneManager.get();
            try {
                sceneManager.initMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}