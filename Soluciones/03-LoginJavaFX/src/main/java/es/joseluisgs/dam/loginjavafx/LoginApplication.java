package es.joseluisgs.dam.loginjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Lo que hacemos aquí es que nuestra aplicación extiende de la clase Application de JavaFX.
 */
public class LoginApplication extends Application {
    // Ejecuta el lunch, que lanza todo de Java FX
    public static void main(String[] args) {
        launch();
    }

    /**
     * El método estar de la clase Application
     * Busca la vista, que es el fichero fxml
     * Crea la escena, es como una ona obra de teatro, podemos cargar la escena que queramos,
     * o cargar una escena por defecto
     * o ir cambiando las escenas.
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        final int WIN_WIDTH = 400;
        final int WIN_HEIGHT = 230;


        // Cargamos el fichero fxml, es importante fijat vien las rutas y carpetas y paquetes
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
        // Creamos una escena, y le pasamos el fxmlLoader con la vista, es decir, el fichero fxml, dentro de la ventana
        Scene scene = new Scene(fxmlLoader.load(), WIN_WIDTH, WIN_HEIGHT);
        // Le ponemos un icono personalizado
        stage.getIcons()
                .add(new Image(Utils.getResource("icons/java.png")));
        stage.setTitle("Login con JavaFX");
        stage.setScene(scene);
        stage.show();
    }
}