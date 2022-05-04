module es.joseluisgs.tableviewadvaced {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens es.joseluisgs.tableviewadvaced to javafx.fxml;
    exports es.joseluisgs.tableviewadvaced;
    exports es.joseluisgs.tableviewadvaced.controllers;
    opens es.joseluisgs.tableviewadvaced.controllers to javafx.fxml;
}