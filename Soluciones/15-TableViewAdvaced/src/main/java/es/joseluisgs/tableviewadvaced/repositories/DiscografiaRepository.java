package es.joseluisgs.tableviewadvaced.repositories;

import es.joseluisgs.tableviewadvaced.models.Album;
import es.joseluisgs.tableviewadvaced.models.Musica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DiscografiaRepository {
    private static DiscografiaRepository instance;
    // Lista de puntuaciones....
    private final ObservableList<Integer> ratingSample = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5);
    // Lista de artistas...
    private final List<String> artistas = List.of(
            "Bon Jovi",
            "Bryan Adams",
            "Keith Urban",
            "Eclipse",
            "H.E.A.T",
            "Sôber",
            "Amadëus",
            "Gräce",
            "091",
            "Avantasia"
    );
    // Lista de títulos de album...
    private final List<String> discos = List.of(
            "Live at Wembley",
            "Reckless",
            "Gwt Closer",
            "Wired",
            "H.E.A.T II",
            "Sinfonía del paradysso",
            "Black Jack II",
            "Hope",
            "La otra vida",
            "Moonglow"
    );

    private final List<String> canciones = List.of(
            "Livin' on a Prayer",
            "Summer of 69",
            "Long Hot Summer",
            "Viva la Victoria",
            "Come clean",
            "Arrepentido",
            "Hijos de Medea",
            "Atreyu",
            "Sigue estando Dios de nuestro lado",
            "Ghost in the Moon"
    );

    private final ObservableList<Musica> discografia = FXCollections.observableArrayList();

    private DiscografiaRepository() {
        // Cargar datos...
        for (int i = 0; i < 10; i++) {
            String fileName = (i + 1) + ".png";
            Album al = new Album(fileName, artistas.get(i), discos.get(i));
            Musica m = new Musica(artistas.get(i), al, canciones.get(i), i % 5);
            discografia.add(m);
        }
    }

    public static DiscografiaRepository getInstance() {
        if (instance == null) {
            instance = new DiscografiaRepository();
        }
        return instance;
    }

    public ObservableList<Integer> getRatingSample() {
        return ratingSample;
    }

    public ObservableList<Musica> getDiscografia() {
        return discografia;
    }
}
