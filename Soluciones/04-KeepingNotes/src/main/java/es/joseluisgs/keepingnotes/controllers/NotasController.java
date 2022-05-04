package es.joseluisgs.keepingnotes.controllers;

import es.joseluisgs.keepingnotes.repositories.NotasRepository;
import es.joseluisgs.keepingnotes.storage.Storage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.time.LocalDate;

public class NotasController {
    // Para alamcenar las notas con las fechas
    private NotasRepository notasRepository = new NotasRepository();
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea notas;

    @FXML
    // Este método nos inicializa todos los componenetes de la clase
    private void initialize() {
        // Cargamos las notas
        loadNotas();
        // Añadimos un evento por código para que cuando se cambie el valor del DatePicker
        datePicker.valueProperty().addListener((observable, oldDate, date) -> {
            System.out.println("Cambio de fecha de " + oldDate + " a " + date);
            // Ponemos en el area las notas almacenadas por fecha
            notas.setText(notasRepository.getOrDefault(date, ""));
        });
        // Poner la fecha actual al iniciarse la aplicación
        datePicker.setValue(LocalDate.now());
    }

    public void exit() {
        saveNotas();
        Platform.exit();
    }


    @FXML
    private void actionSave() {
        System.out.println("Salvando nota...");
        System.out.println("Fecha: " + datePicker.getValue());
        System.out.println("Nota: " + notas.getText());
        // Guardamos la fecha y la nota, así tenemos las fechas y sus notas
        notasRepository.put(datePicker.getValue(), notas.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nota guardada");
        alert.setHeaderText(null);
        alert.setContentText("Nota guardada correctamente");
        alert.showAndWait();
    }

    private void saveNotas() {
        // Guardamos las notas en un fichero
        System.out.println("Guardando notas...");
        try {
            Storage storage = Storage.getInstance();
            storage.save(notasRepository);
            System.out.println("Notas guardadas");
        } catch (Exception e) {
            System.err.println("Error al guardar las notas: " + e.getMessage());
        }

    }

    private void loadNotas() {
        // Cargamos las notas de un fichero
        System.out.println("Cargando notas...");
        try {
            Storage storage = Storage.getInstance();
            notasRepository = storage.load();
            System.out.println("Notas cargadas");
        } catch (Exception e) {
            System.err.println("Error al cargar las notas: " + e.getMessage());
        }
    }
}