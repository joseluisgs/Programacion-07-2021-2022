package es.joseluisgs.agenda.controllers;

import es.joseluisgs.agenda.managers.SceneManager;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {
    Logger logger = LogManager.getLogger(SplashController.class);
    @FXML
    private ImageView fondo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Una animación de fondo
        FadeTransition transition = new FadeTransition(Duration.millis(3000), fondo);
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
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });

    }
}