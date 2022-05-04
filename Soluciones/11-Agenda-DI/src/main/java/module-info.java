module es.joseluisgs.agenda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires lombok;
    requires org.mybatis;
    requires javax.inject;
    requires dagger;
    requires java.compiler;

    exports es.joseluisgs.agenda;
    exports es.joseluisgs.agenda.controllers;
    opens es.joseluisgs.agenda.controllers to javafx.fxml;
    exports es.joseluisgs.agenda.managers;
    opens es.joseluisgs.agenda.managers to javafx.fxml;
    exports es.joseluisgs.agenda.views;
    opens es.joseluisgs.agenda.views to javafx.fxml;
    exports es.joseluisgs.agenda.dto;
    opens es.joseluisgs.agenda.dto to javafx.fxml;
    exports es.joseluisgs.agenda.utils;
    opens es.joseluisgs.agenda.utils to javafx.fxml;
}