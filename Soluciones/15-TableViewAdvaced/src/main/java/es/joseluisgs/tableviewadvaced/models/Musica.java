package es.joseluisgs.tableviewadvaced.models;

import javafx.beans.property.*;

public class Musica {
    // Defino mis datos del modelo de forma reactiva
    private final SimpleStringProperty artista = new SimpleStringProperty();
    private final ObjectProperty albumArt = new SimpleObjectProperty();
    private final StringProperty titulo = new SimpleStringProperty();
    private final IntegerProperty puntuacion = new SimpleIntegerProperty();

    public Musica(String artista, Album album, String titulo, Integer puntuacion) {
        setArtista(artista);
        setAlbum(album);
        setTitulo(titulo);
        setRating(puntuacion);
    }

    public String getArtista() {
        return artista.get();
    }

    // Para artista
    public void setArtista(String art) {
        artista.set(art);
    }

    public StringProperty artistaProperty() {
        return artista;
    }

    public Object getAlbum() {
        return albumArt.get();
    }

    // Para Album
    public void setAlbum(Album alb) {
        albumArt.set(alb);
    }

    public ObjectProperty albumProperty() {
        return albumArt;
    }

    public String getTitulo() {
        return titulo.get();
    }

    //Para Título
    public void setTitulo(String tit) {
        titulo.set(tit);
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public Integer getRating() {
        return puntuacion.get();
    }

    //Puntuacion
    public void setRating(int rat) {
        puntuacion.set(rat);
    }

    public IntegerProperty ratingProperty() {
        return puntuacion;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "artista=" + artista +
                ", albumArt=" + albumArt +
                ", titulo=" + titulo +
                ", puntuacion=" + puntuacion +
                '}';
    }
}
