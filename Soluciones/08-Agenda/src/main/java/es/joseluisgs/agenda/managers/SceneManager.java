package es.joseluisgs.agenda.managers;

import es.joseluisgs.agenda.AgendaApplication;
import es.joseluisgs.agenda.controllers.AcercaDeController;
import es.joseluisgs.agenda.controllers.PersonaEditarViewController;
import es.joseluisgs.agenda.models.Persona;
import es.joseluisgs.agenda.utils.Properties;
import es.joseluisgs.agenda.utils.Resources;
import es.joseluisgs.agenda.controllers.AgendaController;
import es.joseluisgs.agenda.views.Views;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;
    private Stage mainStage;

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

    public void changeScene(Node node, Views view) throws IOException {
        System.out.println("Loading scene " + view.get());
        Stage stage = (Stage) node.getScene().getWindow();
        //oldStage.hide(); // Oculto la anterior
        Parent root = FXMLLoader.load(Objects.requireNonNull(appClass.getResource(view.get())));
        Scene newScene = new Scene(root, Properties.APP_WIDTH, Properties.APP_HEIGHT);
        System.out.println("Scene loaded");
        stage.setScene(newScene);
        stage.show();
    }

    public void initMain() throws IOException {
        System.out.println("Iniciando Main");
        Platform.setImplicitExit(true);
        System.out.println("Loading scene " + Views.MAIN.get());
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.MAIN.get())));
        Scene scene = new Scene(fxmlLoader.load(), Properties.APP_WIDTH, Properties.APP_HEIGHT);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.getIcons().add(new Image(Resources.get(AgendaApplication.class, Properties.APP_ICON)));
        stage.setTitle(Properties.APP_TITLE);
        stage.initStyle(StageStyle.DECORATED);
        System.out.println("Scene loaded");
        // Por si salimos
        stage.setOnCloseRequest(event -> {
            fxmlLoader.<AgendaController>getController().onSalirAction();
        });
        stage.setScene(scene);
        mainStage = stage;
        stage.show();
    }

    public void initSplash(Stage stage) throws IOException, InterruptedException {
        Platform.setImplicitExit(false);
        System.out.println("Iniciando Splash");
        FXMLLoader fxmlLoader = new FXMLLoader(AgendaApplication.class.getResource(Views.SPLASH.get()));
        Scene scene = new Scene(fxmlLoader.load(), Properties.SPLASH_WIDTH, Properties.SPLASH_HEIGHT);
        stage.getIcons().add(new Image(Resources.get(AgendaApplication.class, Properties.APP_ICON)));
        stage.setTitle(Properties.APP_TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void initAcercaDe() throws IOException {
        System.out.println("Iniciando AcercaDe");
        FXMLLoader fxmlLoader = new FXMLLoader(AgendaApplication.class.getResource(Views.ACERCADE.get()));
        Scene scene = new Scene(fxmlLoader.load(), Properties.ACERCADE_WIDTH, Properties.ACERCADE_HEIGHT);
        Stage stage =new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initOwner(mainStage);
        stage.setTitle("Acerca de");
        stage.setResizable(false);
        // Le hacemos los setters a los elementos del controlador
        fxmlLoader.<AcercaDeController>getController().setDialogStage(stage);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public boolean initPersonaEditar(boolean editarModo, Persona persona) throws IOException {
        System.out.println("Iniciando PersonaEditar");
        FXMLLoader fxmlLoader = new FXMLLoader(AgendaApplication.class.getResource(Views.PERSONAEDITAR.get()));
        Scene scene = new Scene(fxmlLoader.load(), Properties.PERSONAEDIT_WIDTH, Properties.PERSONAEDITAR_HEIGHT);
        Stage stage =new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initOwner(mainStage); // -importante con windows modal
        stage.setTitle(editarModo ? "Editar Persona" : "Nueva Persona");
        stage.setResizable(false);
        // Le hacemos los setters a los elementos del controlador
        PersonaEditarViewController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setEditarModo(editarModo);
        controller.setPersona(persona);
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }
}
