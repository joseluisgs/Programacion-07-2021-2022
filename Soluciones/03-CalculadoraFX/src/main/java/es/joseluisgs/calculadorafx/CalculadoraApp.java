package es.joseluisgs.calculadorafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class CalculadoraApp extends Application {
    private static final int HEIGHT = 250;
    private static final int WIDTH = 400;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculadoraApp.class.getResource("views/calculadora-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        // Le ponemos el icono a la ventana (stage)
        stage.getIcons().add(new Image(CalculadoraApp.class.getResourceAsStream("icons/calculadora.png")));
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Mi Calculadora");
        stage.setScene(scene);
        stage.show();
    }
}