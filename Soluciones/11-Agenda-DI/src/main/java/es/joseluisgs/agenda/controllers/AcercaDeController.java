package es.joseluisgs.agenda.controllers;

import es.joseluisgs.agenda.utils.Properties;
import es.joseluisgs.agenda.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AcercaDeController {
    Logger logger = LogManager.getLogger(AcercaDeController.class);
    // Mi elementos...
    private Stage dialogStage;

    @FXML
    private Label version;
    @FXML
    private Label autor;
    @FXML
    private Label titulo;
    @FXML
    private ImageView githubIcon;
    @FXML
    private ImageView twitterIcon;
    @FXML
    private Hyperlink githubLink;
    @FXML
    private Hyperlink twitterLink;

    // SETTERS
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        titulo.setText(Properties.APP_TITLE);
        version.setText("ver.: " + Properties.APP_VERSION);
        autor.setText(Properties.APP_AUTHOR);
        githubLink.setText(Properties.APP_AUTHOR_GITHUB);
        twitterLink.setText(Properties.APP_AUTHOR_TWITTER);

        // Puedo programar los eventos de los enlaces con lambas o usando el diseñador de JavaFX
        githubLink.setOnAction(event -> openGitHub());
        twitterLink.setOnAction(event -> openTwitter());
        //githubIcon.setOnMouseClicked(event -> openGitHub());  Lo hare con FXML
        // twitterIcon.setOnMouseClicked(event -> openTwitter());
    }

    @FXML
    private void openGitHub() {
        try {
            Utils.openBrowser(Properties.APP_AUTHOR_GITHUB);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            logger.error(e.getMessage());
        }
    }

    @FXML
    private void openTwitter() {
        try {
            Utils.openBrowser(Properties.APP_AUTHOR_TWITTER);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            logger.error(e.getMessage());
        }
    }

    @FXML
    private void aceptarAction() {
        dialogStage.close();
    }

}
