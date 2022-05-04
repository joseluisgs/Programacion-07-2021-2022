module es.joseluisgs.dam.layoutsdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.joseluisgs.dam.layoutsdemo to javafx.fxml;
    exports es.joseluisgs.dam.layoutsdemo;
}