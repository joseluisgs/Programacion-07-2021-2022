package es.joseluisgs.agenda;

import es.joseluisgs.agenda.managers.DataBaseManager;
import es.joseluisgs.agenda.managers.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

// https://code.makery.ch/blog/
public class AgendaApplication extends Application {
    static Logger logger = LogManager.getLogger(AgendaApplication.class);

    public static void main(String[] args) {
        checkServer();
        launch();
    }

    private static void checkServer() {
        System.out.println("Comprobamos la conexión al Servidor BD");
        DataBaseManager controller = DataBaseManager.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello world'");
            if (rs.isPresent()) {
                rs.get().next();
                controller.close();
                logger.info("Conexión correcta a la Base de Datos");
            }
        } catch (SQLException e) {
            logger.error("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        // Cargo mi SceneManager
        SceneManager sceneManager = SceneManager.getInstance(AgendaApplication.class);
        sceneManager.initSplash(stage);
    }
}