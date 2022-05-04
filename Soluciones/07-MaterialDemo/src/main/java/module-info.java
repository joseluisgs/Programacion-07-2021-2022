module es.joseluisgs.materialdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.joseluisgs.materialdemo to javafx.fxml;
    exports es.joseluisgs.materialdemo;
}