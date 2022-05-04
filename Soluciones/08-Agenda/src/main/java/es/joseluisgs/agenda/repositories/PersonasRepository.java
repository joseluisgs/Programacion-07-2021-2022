package es.joseluisgs.agenda.repositories;

import es.joseluisgs.agenda.dto.PersonaDTO;
import es.joseluisgs.agenda.models.Persona;
import es.joseluisgs.agenda.services.Storage;
import es.joseluisgs.agenda.utils.Properties;
import es.joseluisgs.agenda.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Estamos clases gráficas de JavaFX que necesitan ser informadas sobre los cambios en la lista de personas.
 * Esto es importante, pues de otro modo la vista no estará sincronizada con los datos.
 * Para estos fines, JavaFX ha introducido nuevas clases de colección.
 * https://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm
 * Podemos usar Listas o Mapas y sus observables para esto.
 */
public class PersonasRepository {
    private static PersonasRepository instance;
    private final ObservableList<Persona> repository = FXCollections.observableArrayList();
    private final Storage storage = Storage.getInstance();

    // Datos de ejemplo....
    private PersonasRepository() {
        if (repository.isEmpty()) {
            repository.add(new Persona("Goku", "Son", "Bola Dragon", "Dragon", "goku@dragonball.com", LocalDate.now(), ""));
            repository.add(new Persona("Gohan", "Son", "Bola Dragon", "Dragon", "gohan@dragonball.com", LocalDate.now(), ""));
            repository.add(new Persona("Naruto", "Uzumaki", "Ramen", "Konoha", "naruto@shipudden.com", LocalDate.now(), ""));
            repository.add(new Persona("Sasuke", "Uchija", "Ojo", "Konoha", "sasuke@shipudden.com", LocalDate.now(), ""));
            repository.add(new Persona("Bruce", "Wayne", "Cueva", "Gotham", "batman@dc.com", LocalDate.now(), ""));
            repository.add(new Persona("Peter", "Parker", "Edificios", "New York", "spiderman@marvel.com", LocalDate.now(), ""));
            repository.add(new Persona("Calrk", "Kent", "Periodicos", "Metropolis", "superman@dc.com", LocalDate.now(), ""));
            repository.add(new Persona("Pepino", "Leganoso", "Ermita, 15", "Leganes", "pepino2leganes.es", LocalDate.now(), ""));
            repository.add(new Persona("Link", "Mastersword", "Calle del Reino", "Hyrule", "link@zelda.org", LocalDate.now(), ""));
        }
    }

    public static PersonasRepository getInstance() {
        if (instance == null) {
            instance = new PersonasRepository();
        }
        return instance;
    }

    // Devuelve la lista de personas como una lista observable.
    public ObservableList<Persona> getAll() {
        return repository;
    }

    // Backup de la lista de personas.
    public void backup() throws IOException {
        List<PersonaDTO> personas = repository.stream().map(PersonaDTO::new).collect(Collectors.toList());
        storage.backup(personas);
    }

    // Restaura la lista de personas.
    public void restore() throws IOException, ClassNotFoundException {
        List<PersonaDTO> personas = storage.restore();
        repository.clear();
        personas.forEach(p -> repository.add(p.fromDTO()));
    }

    public void delete(Persona p) {
        repository.remove(p);
    }

    public void create(Persona p) {
        repository.add(p);
    }

    public void update(Persona persona) {
        int index = repository.indexOf(persona);
        repository.set(index, persona);
    }

    public void storeAvatar(Persona p) throws IOException {
        String destination = Properties.IMAGES_DIR + File.separator + p.getId() + "." + Utils.getFileExtension(p.getAvatar()).orElse("png");
        String source = p.getAvatar().replace("file:", "");
        System.out.println("Origen: " + source);
        System.out.println("Destino: " + destination);
        storage.copyFile(source, destination);
        p.setAvatar(destination);
    }

    public void deleteAvatar(Persona p) throws IOException {
        String source = Properties.IMAGES_DIR + File.separator + p.getId() + "." + Utils.getFileExtension(p.getAvatar()).orElse("png");
        storage.deleteFile(source);
    }
}
