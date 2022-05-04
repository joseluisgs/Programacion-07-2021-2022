package es.joseluisgs.agenda.controllers;

import es.joseluisgs.agenda.AgendaApplication;
import es.joseluisgs.agenda.managers.SceneManager;
import es.joseluisgs.agenda.models.Persona;
import es.joseluisgs.agenda.repositories.PersonasRepository;
import es.joseluisgs.agenda.utils.Properties;
import es.joseluisgs.agenda.utils.Resources;
import es.joseluisgs.agenda.utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class AgendaController {
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
        // Inicializamos el repositorio desde el backup
        try {
            personasRepository.restore();
            System.out.println("Backup restaurado desde: " + Properties.BACKUP_DIR);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se ha podido restaurar el backup");
        }
        // Cargo la lista de personas en base al observable
        personasTable.setItems(personasRepository.getAll());

        // Asigno las columnas de la tabla
        /*nombreColumn.setCellValueFactory(new CellValueFactory<Persona, String>() {
            @Override
            public String getValue(Persona persona) {
                return persona.getNombre();
            }
        });*/

        // Obteniendo los datos de las columnas de un modelo
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        personasTable.setEditable(true);
        nombreColumn.setEditable(true);
        // Creando factories peersonalizados para los tipos de datos
        // https://linuxtut.com/en/7c0e82fa527cd5fae9c2/
        //nombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn("Goku", "Don Palillo", "Daminificado"));

        // Asigno los manejadores de eventos
        personasTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> onPersonaSeleccionada(newValue)
        );

        clearDataInfo();

        personasTable.getSelectionModel().selectFirst();
    }

    private void onPersonaSeleccionada(Persona newValue) {
        System.out.println("Se ha seleccionado una persona");
        if (newValue != null) {
            setDataInfo(newValue);
        } else {
            clearDataInfo();
        }
    }

    private void setDataInfo(Persona persona) {
        System.out.println("Se ha seleccionado la persona: " + persona);
        nombreLabel.setText(persona.getNombre());
        apellidosLabel.setText(persona.getApellidos());
        calleLabel.setText(persona.getCalle());
        ciudadLabel.setText(persona.getCiudad());
        emailLabel.setText(persona.getEmail());
        cumpleañosLabel.setText(Utils.getFormattedDate(persona.getCumpleaños()));
        // La imagen, si no eiste cargamos la de por defecto, si no la que tiene
        if (!persona.getAvatar().isBlank() && Files.exists(Paths.get(persona.getAvatar()))) {
            System.out.println("Cargando imagen: " + persona.getAvatar());
            Image image = new Image(new File(persona.getAvatar()).toURI().toString());
            System.out.println("Imagen cargada: " + image.getUrl());
            avatarImageView.setImage(image);
        } else {
            System.out.println("No existe la imagen. Usando imagen por defecto");
            avatarImageView.setImage(new Image(Resources.get(AgendaApplication.class, "images/person.png")));
            persona.setAvatar(Resources.getPath(AgendaApplication.class, "images/person.png"));
            System.out.println("Nueva información de imagen: " + persona);
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
        System.out.println("Se ha pulsado el menú Acerca de");
        SceneManager.get().initAcercaDe();
    }

    @FXML
    public void onSalirAction() {
        System.out.println("Se ha pulsado el menú salir");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Salir de Agenda?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Hacemos el backup!!
            try {
                personasRepository.backup();
                System.out.println("Backup realizado en: " + Properties.BACKUP_DIR);
            } catch (IOException e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error al hacer backup");
                error.setContentText("No se ha podido hacer el backup");
                e.printStackTrace();
                error.showAndWait();
            } finally {
                Platform.exit();
            }
        } else {
            alert.close();
        }
    }


    @FXML
    private void onNuevoAction() throws IOException {
        System.out.println("Se ha pulsado accion Nuevo");
        Persona persona = new Persona();
        boolean aceptarClicked = SceneManager.get().initPersonaEditar(false, persona);
        if (aceptarClicked) {
            // Se ha añadido una persona al repositorio o tabla porque es reactivo
            salvarAvatar(persona);
            personasRepository.create(persona);
            // Podemos llamar al evento o solo mostrar los datos...
            // personasTable.getSelectionModel().select(persona);
            setDataInfo(persona);
        }
    }

    @FXML
    private void onBorrarAction() {
        System.out.println("Se ha pulsado accion Borrar");
        Persona p = personasTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Borrar");
        alert.setHeaderText("¿Borrar " + p.getNombre() + " " + p.getApellidos() + "?");
        alert.setContentText("¿Está seguro/a? Esta opción no se puede deshacer.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Podemos borralo de la tabla y se borrar del repositorio o viceversa, porque es reactivo
            borrarAvatar(p);
            personasRepository.delete(p);
            // personasTable.getItems().remove(p);
        }
    }


    @FXML
    private void onEditarAction() throws IOException {
        System.out.println("Se ha pulsado accion Editar");
        Persona persona = personasTable.getSelectionModel().getSelectedItem();
        boolean aceptarClicked = SceneManager.get().initPersonaEditar(true, persona);
        if (aceptarClicked) {
            // Se ha editado una persona del repositorio o tabla porque es reactivo
            salvarAvatar(persona);
            personasRepository.update(persona);
            // Podemos llamar al evento o solo mostrar los datos...
            // personasTable.getSelectionModel().select(persona);
            setDataInfo(persona);
        }
    }

    private void borrarAvatar(Persona persona) {
        try {
            personasRepository.deleteAvatar(persona);
        } catch (IOException e) {
            System.out.println("No se ha podido borrar el avatar de la persona: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void salvarAvatar(Persona persona) {
        try {
            personasRepository.storeAvatar(persona);
        } catch (IOException e) {
            System.out.println("No se ha podido salvar el avatar de la persona: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void confirmandoNombreColumnEvento(TableColumn.CellEditEvent<Persona, String> personaStringCellEditEvent) {
        System.out.println("Se ha pulsado accion Confirmar en la Columna Nombre de la Tabla Personas");
        Persona persona = personaStringCellEditEvent.getRowValue();
        persona.setNombre(personaStringCellEditEvent.getNewValue());
        personasRepository.update(persona);

        // Para actualizar la vista
        personasTable.refresh();

        // Vamos a recorrer la tabla
        personasTable.getItems().forEach(p -> {
            System.out.println("Nombre: " + p.getNombre());
        });

        Persona personaSeleccionada = personasTable.getSelectionModel().getSelectedItem();
        personaSeleccionada.setNombre("Goku");
        personasRepository.update(personaSeleccionada);
        //personasTable.refresh();
    }

    public void editandoNombreColumnEvento(TableColumn.CellEditEvent<Persona, String> personaStringCellEditEvent) {
        System.out.println("Se ha pulsado accion Editar en la Columna Nombre de la Tabla Personas");
        System.out.println("Nuevo valor: " + personaStringCellEditEvent.getNewValue());

    }
}