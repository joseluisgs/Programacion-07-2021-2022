package es.joseluisgs.changescenes.SceneManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;
        System.out.println("SceneManager created");
    }

    public static SceneManager getInstance(Class<?> appClass) {
        if (instance == null) {
            instance = new SceneManager(appClass);
        }
        return instance;
    }

    public static SceneManager get() {
        return instance;
    }

    public void changeScene(Node node, String scene) throws IOException {
        System.out.println("Loading scene " + scene);
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(appClass.getResource(scene)));
        Scene newScene = new Scene(root);
        System.out.println("Scene loaded");
        stage.setScene(newScene);
    }
}
