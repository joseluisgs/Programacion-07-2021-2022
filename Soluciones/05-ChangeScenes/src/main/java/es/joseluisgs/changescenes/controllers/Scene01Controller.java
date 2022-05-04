package es.joseluisgs.changescenes.controllers;

import es.joseluisgs.changescenes.SceneManager.SceneManager;
import es.joseluisgs.changescenes.ScenesApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class Scene01Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnScene01;

    // Iniciamos las cosas
    @FXML
    public void initialize() {
        welcomeText.setText("Soy la Scene01");
    }


    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        System.out.println(event.toString());
        welcomeText.setText("Vamos a cambiar de escena");
        System.out.println(ScenesApplication.class.getResource("views/scene02-view.fxml"));
        /*Parent root = FXMLLoader.load(ScenesApplication.class.getResource("views/scene02-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);*/
        SceneManager.get().changeScene((Node) event.getSource(), "views/scene02-view.fxml");
    }
}