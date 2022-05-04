module es.joseluisgs.changescenes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.joseluisgs.changescenes to javafx.fxml;
    exports es.joseluisgs.changescenes;
    exports es.joseluisgs.changescenes.controllers;
    opens es.joseluisgs.changescenes.controllers to javafx.fxml;
    exports es.joseluisgs.changescenes.SceneManager;
    opens es.joseluisgs.changescenes.SceneManager to javafx.fxml;
}