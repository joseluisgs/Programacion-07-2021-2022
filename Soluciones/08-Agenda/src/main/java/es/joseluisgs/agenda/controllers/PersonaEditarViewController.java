package es.joseluisgs.agenda.controllers;

import es.joseluisgs.agenda.AgendaApplication;
import es.joseluisgs.agenda.models.Persona;
import es.joseluisgs.agenda.utils.Resources;
import es.joseluisgs.agenda.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class PersonaEditarViewController {
    // Mis elementos de la vista a los que hago Binding
    @FXML
    TextField nombreTxt;
    @FXML
    TextField apellidosTxt;
    @FXML
    TextField calleTxt;
    @FXML
    TextField ciudadTxt;
    @FXML
    TextField emailTxt;
    @FXML
    DatePicker cumpleañosDatePicker;
    @FXML
    ImageView avatarImageView;
    // estos son propiedades que necesitaré para poder interactuar con la vista
    private Stage dialogStage;
    private Persona persona;
    private boolean aceptarClicked = false;
    private boolean editarModo = false;

    // GETTERS AND SETTERS
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
        System.out.println("Persona asociada: " + persona);
        if (editarModo) {
            setDataInfo();
        }
        nombreTxt.requestFocus(); // Obtiene el foco
    }

    private void setDataInfo() {
        System.out.println("SetDataInfo");
        nombreTxt.setText(persona.getNombre());
        apellidosTxt.setText(persona.getApellidos());
        calleTxt.setText(persona.getCalle());
        ciudadTxt.setText(persona.getCiudad());
        emailTxt.setText(persona.getEmail());
        cumpleañosDatePicker.setValue(persona.getCumpleaños());
        // La imagen
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

    public void setEditarModo(boolean editarModo) {
        this.editarModo = editarModo;
        System.out.println("Modo Editar: " + editarModo);
    }

    public boolean isAceptarClicked() {
        return aceptarClicked;
    }

    @FXML
    private void initialize() {
        System.out.println("Editar o nueva persona");
        cumpleañosDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void onAceptarAction() {
        System.out.println("Aceptar");
        System.out.println("Validar datos");
        if (isInputValid()) {
            persona.setNombre(nombreTxt.getText());
            persona.setApellidos(apellidosTxt.getText());
            persona.setCalle(calleTxt.getText());
            persona.setCiudad(ciudadTxt.getText());
            persona.setEmail(emailTxt.getText());
            persona.setCumpleaños(cumpleañosDatePicker.getValue());
            persona.setAvatar(avatarImageView.getImage().getUrl());
            aceptarClicked = true;
            dialogStage.close();
        } else {
            System.out.println("Datos no validos");
        }
    }

    @FXML
    private void onCancelarAction() {
        System.out.println("Has pulsado Cancelar");
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreTxt.getText() == null || nombreTxt.getText().isBlank()) {
            errorMessage += "El nombre no puede estar en blanco\n";
        }
        if (apellidosTxt.getText() == null || apellidosTxt.getText().isBlank()) {
            errorMessage += "Los apellidos no pueden estar en blanco\n";
        }
        if (calleTxt.getText() == null || calleTxt.getText().isBlank()) {
            errorMessage += "La calle no puede estar en blanco\n";
        }

        if (ciudadTxt.getText() == null || ciudadTxt.getText().isBlank()) {
            errorMessage += "La ciudad no puede estar en blanco\n";
        }

        if (emailTxt.getText() == null || emailTxt.getText().isBlank() || !Utils.isEmail(emailTxt.getText())) {
            errorMessage += "El email no puede estar en blanco o no es válido\n";
        }

        if (cumpleañosDatePicker.getValue().isAfter(LocalDate.now())) {
            errorMessage += "La fecha de cumpleaños no puede ser superior a la actual\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = Utils.getAlertErrorDetails("Error en datos", "Datos de persona incorrrectos", "Existen problemas al validar.", errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void onAvatarAction() {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Selecciona un avatar");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.jpg", "*.png"));
        File file = filechooser.showOpenDialog(avatarImageView.getScene().getWindow());

        if (file != null) {
            System.out.println("Se ha seleccionado el archivo: " + file.getAbsolutePath());
            avatarImageView.setImage(new Image(file.toURI().toString()));
            // Se lo asignamos a la persona...
            persona.setAvatar(file.getAbsolutePath());
            System.out.println("Se ha asignado el avatar a la persona desde: " + persona.getAvatar());
        }
    }
}
