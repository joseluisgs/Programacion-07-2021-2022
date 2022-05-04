package es.joseluisgs.agenda.controllers;

import es.joseluisgs.agenda.AgendaApplication;
import es.joseluisgs.agenda.managers.SceneManager;
import es.joseluisgs.agenda.models.Persona;
import es.joseluisgs.agenda.repositories.PersonasRepository;
import es.joseluisgs.agenda.utils.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;

public class AgendaController {
    Logger logger = LogManager.getLogger(AgendaController.class);
    // Mi repositorio de personas
    PersonasRepository personasRepository = PersonasRepository.getInstance();

    // Tabla de contactos
    @FXML
    private TableView<Persona> personasTable;
    @FXML
    private TableColumn<Persona, String> nombreColumn;
    @FXML
    private TableColumn<Persona, String> apellidosColumn;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label calleLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label cumpleañosLabel;

    @FXML
    private ImageView avatarImageView;

    @FXML
    private void initialize() {
        // Cargo la lista de personas en base al observable
        try {
            loadData();
        } catch (SQLException e) {
            logger.error("No se ha podido cargar la lista de personas");
        }

        // Asigno las columnas de la tabla
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        // Asigno los manejadores de eventos
        personasTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> onPersonaSeleccionada(newValue)
        );

        clearDataInfo();

        personasTable.getSelectionModel().selectFirst();
    }

    private void onPersonaSeleccionada(Persona newValue) {
        if (newValue != null) {
            setDataInfo(newValue);
        } else {
            clearDataInfo();
        }
    }

    private void setDataInfo(Persona persona) {
        logger.info("Se ha seleccionado la persona: " + persona);
        nombreLabel.setText(persona.getNombre());
        apellidosLabel.setText(persona.getApellidos());
        calleLabel.setText(persona.getCalle());
        ciudadLabel.setText(persona.getCiudad());
        emailLabel.setText(persona.getEmail());
        cumpleañosLabel.setText(Utils.getFormattedDate(persona.getCumpleaños()));
        // La imagen, si no eiste cargamos la de por defecto, si no la que tiene
        if (!persona.getAvatar().isBlank() && Files.exists(Paths.get(persona.getAvatar()))) {
            logger.info("Cargando imagen: " + persona.getAvatar());
            Image image = new Image(new File(persona.getAvatar()).toURI().toString());
            logger.info("Imagen cargada: " + image.getUrl());
            avatarImageView.setImage(image);
        } else {
            logger.warn("No existe la imagen. Usando imagen por defecto");
            avatarImageView.setImage(new Image(Resources.get(AgendaApplication.class, "images/person.png")));
            persona.setAvatar(Resources.getPath(AgendaApplication.class, "images/person.png"));
            logger.warn("Nueva información de imagen: " + persona);
        }
    }

    private void clearDataInfo() {
        nombreLabel.setText("");
        apellidosLabel.setText("");
        calleLabel.setText("");
        ciudadLabel.setText("");
        emailLabel.setText("");
        cumpleañosLabel.setText("");
    }

    @FXML
    private void onAcercaDeAction() throws IOException {
        logger.info("Se ha pulsado el menú Acerca de");
        SceneManager.get().initAcercaDe();
    }

    @FXML
    public void onSalirAction() {
        logger.info("Se ha pulsado el menú salir");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Salir de Agenda?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Hacemos el backup!!
            backup();
            Platform.exit();
        } else {
            alert.close();
        }
    }


    @FXML
    private void onNuevoAction() throws IOException {
        logger.info("Se ha pulsado accion Nuevo");
        Persona persona = new Persona();
        boolean aceptarClicked = SceneManager.get().initPersonaEditar(false, persona);
        if (aceptarClicked) {
            // Se ha añadido una persona al repositorio o tabla porque es reactivo
            try {
                personasRepository.create(persona);
            } catch (SQLException | IOException e) {
                logger.error("Error al crear persona: " + e.getMessage());
            }
            // Podemos llamar al evento o solo mostrar los datos...
            // personasTable.getSelectionModel().select(persona);
            setDataInfo(persona);
        }
    }

    @FXML
    private void onBorrarAction() {
        logger.info("Se ha pulsado accion Borrar");
        Persona p = personasTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Borrar");
        alert.setHeaderText("¿Borrar " + p.getNombre() + " " + p.getApellidos() + "?");
        alert.setContentText("¿Está seguro/a? Esta opción no se puede deshacer.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                personasRepository.delete(p);
            } catch (SQLException | IOException e) {
                logger.error("Error al Eliminar: " + e.getMessage());
            }
        }
    }


    @FXML
    private void onEditarAction() throws IOException {
        logger.info("Se ha pulsado accion Editar");
        Persona persona = personasTable.getSelectionModel().getSelectedItem();
        boolean aceptarClicked = SceneManager.get().initPersonaEditar(true, persona);
        if (aceptarClicked) {
            // Se ha editado una persona del repositorio o tabla porque es reactivo
            try {
                personasRepository.update(persona);
            } catch (SQLException | IOException e) {
                logger.error("Error al actualizar persona: " + e.getMessage());
            }
            // Podemos llamar al evento o solo mostrar los datos...
            // personasTable.getSelectionModel().select(persona);
            setDataInfo(persona);
        }
    }

    @FXML
    private void onEstadisticasAction() throws IOException, SQLException {
        logger.info("Se ha pulsado accion Estadisticas");
        SceneManager.get().initEstadisticas(personasRepository.getAll());
    }

    @FXML
    private void onMenuMetroAction() throws IOException {
        logger.info("Se ha pulsado accion Metro");
        Temas.set(this.avatarImageView, Themes.METRO.get());
    }

    @FXML
    private void onMenuDamAction() throws IOException {
        logger.info("Se ha pulsado accion Dam");
        Temas.set(this.avatarImageView, Themes.DAM.get());
    }

    @FXML
    private void onMenuBootstrapt3Action() throws IOException {
        logger.info("Se ha pulsado accion Bootstrap3");
        Temas.set(this.avatarImageView, Themes.BOOTSTRAPT3.get());
    }

    @FXML
    private void onMenuBootstraptFXAction() throws IOException {
        logger.info("Se ha pulsado accion BootstrapFX");
        Temas.changeBootstrapFX(this.avatarImageView);
    }

    @FXML
    private void onMenuModenaAction() throws IOException {
        logger.info("Se ha pulsado accion Modena");
        Temas.set(this.avatarImageView, Themes.MODENA.get());
    }

    @FXML
    private void onMenuLimpiarAction() throws IOException {
        logger.info("Se ha pulsado accion Limpiar");
        Temas.remove(this.avatarImageView);
    }

    @FXML
    private void backup() {
        try {
            personasRepository.backup();
            logger.info("Backup realizado en: " + Properties.BACKUP_DIR);
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error al hacer backup");
            error.setContentText("No se ha podido hacer el backup");
            logger.error("No se ha podido hacer el backup", e.getMessage());
            error.showAndWait();
        }
    }

    @FXML
    private void restaurar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Restaurar Backup");
        //alert.setHeaderText("¿Está seguro de restaurar el backup?");
        alert.setContentText("¿Está seguro de restaurar el backup?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                personasRepository.restore();
                loadData();
                logger.info("Backup restaurado desde: " + Properties.BACKUP_DIR);
            } catch (ClassNotFoundException | SQLException | IOException e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error al hacer backup");
                error.setContentText("No se ha podido hacer el backup");
                logger.error("No se ha podido hacer el backup", e.getMessage());
                error.showAndWait();
            }
        }

    }

    @FXML
    private void loadData() throws SQLException {
        logger.info("Cargando datos...");
        personasTable.setItems(personasRepository.getAll());
    }
}