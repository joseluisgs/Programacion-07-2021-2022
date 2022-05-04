package es.joseluisgs.tableviewadvaced.controllers;

import es.joseluisgs.tableviewadvaced.models.Album;
import es.joseluisgs.tableviewadvaced.models.Musica;
import es.joseluisgs.tableviewadvaced.repositories.DiscografiaRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.nio.file.Paths;

public class TableViewController {
    DiscografiaRepository discografiaRepository = DiscografiaRepository.getInstance();
    @FXML
    TableView<Musica> tableMusica;
    @FXML
    TableColumn<Musica, Album> albumArt;
    @FXML
    TableColumn<Musica, String> title;
    @FXML
    TableColumn<Musica, String> artist;
    @FXML
    TableColumn<Musica, Integer> rating;

    @FXML
    private void initialize() {
        tableMusica.setTableMenuButtonVisible(true); // Mostramos el menú de la tabla
        // Asigno las columnas de la tabla
        initTableColumns();
        initCellFactory();
        initData();

    }

    private void initCellFactory() {

    }

    private void initData() {
        System.out.println("Cargando datos...");
        discografiaRepository.getDiscografia().forEach(System.out::println);
        tableMusica.setItems(discografiaRepository.getDiscografia());
    }

    private void initTableColumns() {
        System.out.println("Inicializando columnas...");
        albumArt.setPrefWidth(150); // Puedo cambiar esto sobre la marcha
        albumArt.setEditable(false); // No se puede editar ya lo hemos puesto en el FXML
        title.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        title.setPrefWidth(120);
        artist.setCellValueFactory(cellData -> cellData.getValue().artistaProperty());
        artist.setPrefWidth(120);
        rating.setPrefWidth(120);
        // Celdas personalizadas
        rating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty().asObject());
        setRatingCell();
        albumArt.setCellValueFactory(cellData -> cellData.getValue().albumProperty());
        setAlbumartCell();

    }

    private void setAlbumartCell() {
        albumArt.setCellFactory(param -> new TableCell<>() {
            // Creo la vista del elemento
            @Override
            public void updateItem(Album item, boolean empty) {
                if (item != null) {
                    HBox box = new HBox();
                    box.setSpacing(10);
                    VBox vbox = new VBox();
                    vbox.getChildren().add(new Label(item.getArtist()));
                    vbox.getChildren().add(new Label(item.getAlbum()));

                    ImageView imageview = new ImageView();
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    System.out.println(item.getFilename());
                    // var dirImage = TableApplication.class.getResource(File.separator + "img" + File.separator + item.getFilename());
                    var dirImage = Paths.get(System.getProperty("user.dir") + File.separator + "img" + File.separator + item.getFilename());
                    System.out.println(dirImage);
                    imageview.setImage(new Image(dirImage.toUri().toString()));

                    box.getChildren().addAll(imageview, vbox);
                    // Aplicamos todos los elementos gráficos a la celda
                    setGraphic(box);
                }
            }
        });
    }

    private void setRatingCell() {
        rating.setCellFactory(param -> new TableCell<>() {
            @Override
            public void updateItem(Integer item, boolean empty) {
                if (item != null) {
                    ChoiceBox choice = new ChoiceBox(discografiaRepository.getRatingSample());
                    choice.getSelectionModel().select(discografiaRepository.getRatingSample().indexOf(item));
                    // Aplicamos todos los elementos gráficos a la celda
                    setGraphic(choice);
                }
            }
        });
    }


    private TableView<Musica> getTableView() {


        // Cell Factory para Album ART


        // Cell Factory para Puntuación


        // Añadimos las columnas a la tabla
        return null;
    }


}