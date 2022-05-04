package es.joseluisgs.tableviewadvaced.models;

public class Album {
    private String filename;
    private String artist;
    private String album;
    public Album(String filename, String artist, String album) {
        this.filename = filename;
        this.artist = artist;
        this.album = album;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Album{" +
                "filename='" + filename + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
