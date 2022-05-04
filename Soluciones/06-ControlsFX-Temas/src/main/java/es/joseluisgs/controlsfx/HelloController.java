package es.joseluisgs.controlsfx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Toggle;
import org.controlsfx.control.ToggleSwitch;
import org.kordamp.bootstrapfx.BootstrapFX;

public class HelloController {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ToggleSwitch toggleSwitch;
    @FXML
    private ListView<String> listView;

    @FXML
    private void initialize() {
        comboBox.getItems().addAll("DAM", "Metro Dark", "Modena", "Bootstrapt3", "BootstraptFX", "Light");
        comboBox.getSelectionModel().select(0);
        listView.getItems().addAll("DAM", "Metro Dark", "Modena", "Bootstrapt3", "BootstraptFX", "Light");
        listView.getSelectionModel().select(0);
        toggleSwitch.setSelected(true);
    }

    @FXML
    private void handleComboBoxAction() {
        String style = comboBox.getSelectionModel().getSelectedItem();
        System.out.println("Estilo: " + style);
        switch (style) {
            case "DAM":
                changeTheme(comboBox, "styles/dam/dam.css");
                break;
            case "Metro Dark":
                changeTheme(comboBox, "styles/metro/metrodark.css");
                break;
            case "Modena":
                changeTheme(comboBox, "styles/modena/modena.css");
                break;
            case "BootstraptFX":
                changeBootstrapFX(comboBox);
                break;
            case "Bootstrapt3":
                changeTheme(comboBox, "styles/bootstrapt/bootstrapt3.css");
                break;
            case "Light":
                removeStyle();
                break;
        }
    }



    @FXML
    private void handleToggleSwitchAction() {
        if (toggleSwitch.isSelected()) {
            System.out.println("ToggleSwitch: ON");
            changeTheme(toggleSwitch, "styles/dam/dam.css");
        } else {
            System.out.println("ToggleSwitch: OFF");
            removeStyle();
        }
    }

    private void changeTheme(Node node, String style) {
        node.getScene().getRoot().getStylesheets().clear();
        var newStyle = getClass().getResource(style).toString();
        node.getScene().getRoot().getStylesheets().add(newStyle);
        System.out.println("Estilo: " + newStyle);
    }

    private void removeStyle() {
        toggleSwitch.getScene().getRoot().getStylesheets().clear();
    }

    private void changeBootstrapFX(ComboBox<String> comboBox) {
        comboBox.getScene().getStylesheets().clear();
        comboBox.getScene().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        System.out.println("Estilo: BootstraptFX");
    }
}