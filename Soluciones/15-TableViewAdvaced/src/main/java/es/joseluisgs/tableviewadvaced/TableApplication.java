package es.joseluisgs.tableviewadvaced;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class TableApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TableApplication.class.getResource("table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 480, Color.web("#666666"));
        stage.setTitle("Table View!");
        stage.setScene(scene);
        stage.show();
    }
}