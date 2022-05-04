module es.joseluisgs.dam.holajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.joseluisgs.dam.holajavafx to javafx.fxml;
    exports es.joseluisgs.dam.holajavafx;
}