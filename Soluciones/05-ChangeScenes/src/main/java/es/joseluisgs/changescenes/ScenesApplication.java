package es.joseluisgs.changescenes;

import es.joseluisgs.changescenes.SceneManager.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScenesApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Cargo el manager de escenas nada mas comenzar la aplicacion
        SceneManager sceneManager = SceneManager.getInstance(ScenesApplication.class);

        FXMLLoader fxmlLoader = new FXMLLoader(ScenesApplication.class.getResource("views/scene01-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Cambiando de escena");
        stage.setScene(scene);
        stage.show();
    }
}