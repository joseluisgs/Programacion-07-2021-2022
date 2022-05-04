package es.joseluisgs.dam.loginfxbootstrapt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Cargamos el fichero fxml de la vista
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("views/login-view.fxml"));
        // Creamos la escena con el fxml
        Scene scene = new Scene(fxmlLoader.load(), 547, 260);
        // Aplicamos BootstrapFX
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        // Le ponemo un icono
        stage.getIcons().add(new Image(LoginApplication.class.getResourceAsStream("icons/login.png")));
        stage.setTitle("Hola Login!");
        // Añadimos la escena al stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}