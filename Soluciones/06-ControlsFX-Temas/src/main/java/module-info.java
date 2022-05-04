module es.joseluisgs.controlsfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.joseluisgs.controlsfx to javafx.fxml;
    exports es.joseluisgs.controlsfx;
}