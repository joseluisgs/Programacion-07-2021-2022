package es.joseluisgs.calculadorafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class CalculadoraViewController {
    private int numeroA, numeroB, resultado;
    @FXML
    private TextField txtNumeroA;
    @FXML
    private TextField txtNumeroB;
    @FXML
    private TextField txtResultado;
    @FXML
    private Button buttonLimpiar;

    @FXML
    private void initialize() {
        System.out.println("Inicializando");

        // Eventos por codigo
        buttonLimpiar.setOnAction(event -> {
            System.out.println(event.getSource());
            limpiarCampos();
        });


        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNumeroA.setText("0");
        txtNumeroB.setText("0");
        txtResultado.setText("0");

    }


    @FXML
    private void onMenuCerrarAction(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir de Calculadora");
        alert.setHeaderText("¿Está seguro de que desea cerrar la aplicación?");
        //alert.setContentText("¿Está seguro de que desea cerrar la aplicación?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.exit(0); // Cerrar la aplicación
        }

    }

    @FXML

    private void onBotonSumarAction(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        if (comprobarCampos()) {
            resultado = numeroA + numeroB;
            txtResultado.setText(String.valueOf(resultado));
        } else {
            mensajeError("Error en los campos. No son números");
        }
    }

    private void mensajeError(String mensajeError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error en los campos");
        alert.setContentText(mensajeError);
        alert.showAndWait();
    }

    @FXML
    private void onBotonRestarAction(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getSource());
        if (comprobarCampos()) {
            resultado = numeroA - numeroB;
            txtResultado.setText(String.valueOf(resultado));
        } else {
            mensajeError("Error en los campos. No son números");
        }

    }

    @FXML

    private void onBotonMultiplicarAction(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getSource());
        if (comprobarCampos()) {
            resultado = numeroA * numeroB;
            txtResultado.setText(String.valueOf(resultado));
        } else {
            mensajeError("Error en los campos. No son números");
        }
    }

    @FXML
    private void onBotonDividirAction(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getSource());
        if (comprobarCampos() && numeroB != 0) {
            resultado = numeroA / numeroB;
            txtResultado.setText(String.valueOf(resultado));
        } else {
            mensajeError("Error en los campos. No son números o el segundo número es 0");
        }
    }

    private boolean comprobarCampos() {
        boolean resultado = !txtNumeroA.getText().isEmpty();
        if (txtNumeroB.getText().isEmpty()) {
            resultado = false;
        }

        try {
            numeroA = Integer.parseInt(txtNumeroA.getText());
            numeroB = Integer.parseInt(txtNumeroB.getText());
        } catch (NumberFormatException e) {
            resultado = false;
        }

        return resultado;
    }
}