package es.joseluisgs.keepingnotes;

import es.joseluisgs.keepingnotes.Utils.Resources;
import es.joseluisgs.keepingnotes.controllers.NotasController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class KeepingNotes extends Application {
    private static final String APP_TITLE = "Keeping Notes DAM";
    private static final int APP_HEIGHT = 400;
    private static final int APP_WIDTH = 600;
    private static final String APP_ICON = "icons/app-icon.png";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KeepingNotes.class.getResource("views/notas-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), APP_WIDTH, APP_HEIGHT);
        // Icono y título
        stage.getIcons().add(new Image(Resources.get(KeepingNotes.class, APP_ICON)));
        stage.setTitle(APP_TITLE);
        // Añadimos el evento de cerrar la aplicación
        stage.setOnCloseRequest(event -> {
            // Así a lo burro
            //System.exit(0);
            // Obtengo su controlador casteado a NotesController
            fxmlLoader.<NotasController>getController().exit();
        });
        // Añadimos la escena
        stage.setScene(scene);
        stage.show();
    }
}