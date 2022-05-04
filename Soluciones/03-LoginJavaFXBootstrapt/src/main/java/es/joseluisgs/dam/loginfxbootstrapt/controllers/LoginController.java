package es.joseluisgs.dam.loginfxbootstrapt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    private int loginCounter = 0;
    private String password = "secret";
    @FXML
    private TextField txtUsername;
    @FXML
    public PasswordField txtPassword;
    @FXML
    private Button btnAceptar;

    @FXML
    // Con este nétodo indicamos qué queremos que pase cuando se inicialice la escena
    private void initialize() {
        System.out.println("Inicializando");

        // Incluso podemos inicializar variables o evenetos por codigo
        btnAceptar.setText("Aceptar");
        // De esta manera definimos el evento con un Lambda...
        btnAceptar.setOnAction(event -> {
            System.out.println("Pulsando Aceptar");
            System.out.println(event.getSource());
            loginCounter++;
            procesarLogin();
        });

    }

    @FXML
    // De esta manera podemos ver el evento que se produce al pulsar el botón
    private void btnCancelarAction(ActionEvent actionEvent) {
        System.out.println("Cancelar");
        System.out.println(actionEvent.getSource());
        limpiarCampos();
    }

    private void procesarLogin() {
        if (loginCounter > 3) {
            btnAceptar.setDisable(true);
            txtUsername.setDisable(true);
            txtPassword.setDisable(true);
            return;
        }
        if (txtUsername.getText().equals("admin") && txtPassword.getText().equals(password)) {
            System.out.println("Login correcto");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login correcto");
            alert.setHeaderText("Bienvenido");
            alert.setContentText("Has iniciado sesión correctamente");
            alert.showAndWait();
            limpiarCampos();
            loginCounter = 0;
        } else {
            System.out.println("Login incorrecto");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error en el login");
            alert.setContentText("Usuario o contraseña incorrectos");
            alert.showAndWait();
            limpiarCampos();
        }
    }


    private void limpiarCampos() {
        txtUsername.setText("");
        txtPassword.setText("");
    }
}