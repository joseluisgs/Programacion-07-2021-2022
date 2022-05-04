module com.example.holamundojavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.holamundojavafx to javafx.fxml;
    exports com.example.holamundojavafx;
    exports com.example.holamundojavafx.controllers;
    opens com.example.holamundojavafx.controllers to javafx.fxml;
}