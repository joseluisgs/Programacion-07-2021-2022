module es.joseluisgs.dam.loginfxbootstrapt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens es.joseluisgs.dam.loginfxbootstrapt to javafx.fxml;
    exports es.joseluisgs.dam.loginfxbootstrapt;
    exports es.joseluisgs.dam.loginfxbootstrapt.controllers;
    opens es.joseluisgs.dam.loginfxbootstrapt.controllers to javafx.fxml;
}