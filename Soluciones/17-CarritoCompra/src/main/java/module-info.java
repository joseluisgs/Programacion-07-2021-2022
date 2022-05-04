module com.example.carritocompra {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    requires com.google.gson;


    exports com.example.carritocompra;
    exports com.example.carritocompra.controllers;
    opens com.example.carritocompra.controllers to javafx.fxml;

    opens com.example.carritocompra.models to com.google.gson;

}