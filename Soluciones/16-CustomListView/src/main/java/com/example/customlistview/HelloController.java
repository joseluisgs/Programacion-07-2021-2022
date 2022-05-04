package com.example.customlistview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ListView<Person> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Person> wordsList = FXCollections.observableArrayList();
        wordsList.add(new Person("Isaac", "Newton"));
        wordsList.add(new Person("Albert", "Einstein"));
        wordsList.add(new Person("Ludwig", "Boltzmann"));
        listView.setItems(wordsList);
    }

    public void defaultButtonClick() {
        listView.setCellFactory(null);
    }

    public void cellFactoryButtonClick() {
        // listView.setCellFactory(new PersonCellFactory());
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getFirstName().toUpperCase() + " " + item.getLastName().toUpperCase());
                }
            }
        });
    }

    public void checkboxCellFactoryButtonClick() {
        //listView.setCellFactory(new CheckboxCellFactory());
        listView.setCellFactory(param -> new ListCell<Person>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (item != null) {
                    setText(null);
                    setGraphic(new CheckBox(item.getFirstName() + " " + item.getLastName()));
                } else {
                    setText("null");
                    setGraphic(null);
                }
            }
        });
    }
}