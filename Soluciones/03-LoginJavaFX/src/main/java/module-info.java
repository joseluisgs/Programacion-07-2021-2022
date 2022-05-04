/**
 * Aquí va todos los módulos y funcionalidad que se pueden usar en el proyecto.
 */

module es.joseluisgs.dam.loginjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.joseluisgs.dam.loginjavafx to javafx.fxml;
    exports es.joseluisgs.dam.loginjavafx;
}