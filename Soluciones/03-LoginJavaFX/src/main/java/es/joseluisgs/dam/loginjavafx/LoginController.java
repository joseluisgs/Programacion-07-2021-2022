package es.joseluisgs.dam.loginjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Optional;

public class LoginController {
    // Los elementos de las vistas para usarlos se enlazan con @FXML, y
    // Es importante que se ponga el nombre exacto del elemento como hemos puesto en el id de code de la vista.

    // Elementos de la vista
    @FXML
    private TextField txtNombre; // Nombre de usuario
    @FXML
    private TextField txtApellidos; // Apellido de usuario

    // Métodos de eventos
    @FXML
    private void btnSalirClick(ActionEvent event) {
        System.out.println("Salir");
        accionSalir();
    }

    @FXML
    private void btnEnviarClick(ActionEvent event) {
        System.out.println("Enviar");
        accionEnviar();
    }

    @FXML
    private void btnLimpiarClick(ActionEvent event) {
        System.out.println("Limpiar");
        accionLimpiar();
    }

    @FXML
    private void linkGitHub(ActionEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://github.com/joseluisgs").start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }

    // Mi código
    private void accionSalir() {
        //Mostramos mensaje de confirmación para cerrar la aplicación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar aplicación...");
        alert.setHeaderText(null); // Evitamos que nos salga el doble panel...
        alert.setContentText("¿Desea cerrar la aplicación?");

        // Detectamos qué estamos pulsando
        Optional<ButtonType> result = alert.showAndWait();
        //Si se ha pulsado el botón "Aceptar"
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            //Si se ha pulsado el botón "Cancelar" enfocamos en el TextField Nombre
            txtNombre.requestFocus();
        }
    }

    private void accionLimpiar() {
        txtApellidos.setText("");
        txtNombre.setText("");
        txtNombre.requestFocus();
    }

    private void accionEnviar() {
        //Obtenemos el nombre y apellidos introducidos por
        //el usuario en los campos TextField de la ventana gráfica
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();

        //Mostramos el nombre y apellidos por la consula introducido en la consola (solo para depuración)
        System.out.println("Nombre: [" + nombre + "]");
        System.out.println("Apellidos: " + apellidos + "]");

        //Si el usuario ha introducido nombre y apellidos los mostramos en un mensaje
        //Si falta algún dato le mostramos un mensaje indicándolo
        Alert alert;
        if (nombre.isEmpty() || apellidos.isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Faltan datos...");
            alert.setHeaderText("Faltan datos por introducir en el formulario.");
            alert.setContentText("Debe introducir el nombre y los apellidos.");
            txtNombre.requestFocus();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos introducidos en el formulario...");
            alert.setHeaderText("Ha introducido correctamente los " +
                    "datos en el formulario. Se muestran a continuación.");
            alert.setContentText("Nombre: " + nombre +
                    System.lineSeparator() + "Apellidos: " + apellidos);
        }
        alert.showAndWait();
    }

}