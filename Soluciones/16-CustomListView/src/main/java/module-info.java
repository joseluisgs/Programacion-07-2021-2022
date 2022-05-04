module com.example.customlistview {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.customlistview to javafx.fxml;
    exports com.example.customlistview;
}