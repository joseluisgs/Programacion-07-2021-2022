module es.joseluisgs.keepingnotes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens es.joseluisgs.keepingnotes to javafx.fxml;
    exports es.joseluisgs.keepingnotes;
    exports es.joseluisgs.keepingnotes.controllers;
    opens es.joseluisgs.keepingnotes.controllers to javafx.fxml;
}