package es.joseluisgs.agenda;

import es.joseluisgs.agenda.managers.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

// https://code.makery.ch/blog/
public class AgendaApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        // Cargo mi SceneManager
        SceneManager sceneManager = SceneManager.getInstance(AgendaApplication.class);
        sceneManager.initSplash(stage);
    }
}