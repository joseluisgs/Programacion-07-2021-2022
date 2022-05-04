module es.joseluisgs.calculadorafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens es.joseluisgs.calculadorafx to javafx.fxml;
    exports es.joseluisgs.calculadorafx;
    exports es.joseluisgs.calculadorafx.controllers;
    opens es.joseluisgs.calculadorafx.controllers to javafx.fxml;
}