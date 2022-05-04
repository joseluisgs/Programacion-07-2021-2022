package es.joseluisgs.agenda.repositories;

import es.joseluisgs.agenda.AgendaApplication;
import es.joseluisgs.agenda.dto.PersonaDTO;
import es.joseluisgs.agenda.managers.DataBaseManager;
import es.joseluisgs.agenda.models.Persona;
import es.joseluisgs.agenda.services.Storage;
import es.joseluisgs.agenda.utils.Properties;
import es.joseluisgs.agenda.utils.Resources;
import es.joseluisgs.agenda.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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
    Logger logger = LogManager.getLogger(PersonasRepository.class);
    DataBaseManager db = DataBaseManager.getInstance();

    // Datos de ejemplo....
    private PersonasRepository() {
        // si no tenemos nada.. Esto es opcional, lo ideal es empezar vacío....
        //initData();
    }

    private void initData() {
        if (repository.isEmpty()) {
            logger.info("Inicializando datos de ejemplo");
            try {
                create(new Persona(UUID.randomUUID().toString(), "Goku", "Son", "Bola Dragon", "Dragon", "goku@dragonball.com", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Gohan", "Son", "Bola Dragon", "Dragon", "gohan@dragonball.com", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Naruto", "Uzumaki", "Ramen", "Konoha", "naruto@shipudden.com", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Sasuke", "Uchija", "Ojo", "Konoha", "sasuke@shipudden.com", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Bruce", "Wayne", "Cueva", "Gotham", "batman@dc.com", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Peter", "Parker", "Edificios", "New York", "spiderman@marvel.com", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Calrk", "Kent", "Periodicos", "Metropolis", "superman@dc.com", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Pepino", "Leganoso", "Ermita, 15", "Leganes", "pepino2leganes.es", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
                create(new Persona(UUID.randomUUID().toString(), "Link", "Mastersword", "Calle del Reino", "Hyrule", "link@zelda.org", LocalDate.now(), Resources.getPath(AgendaApplication.class, "images/person.png")));
            } catch (SQLException | IOException e) {
                logger.error("Error al inicializar datos de ejemplo con la base de datos", e);
            }
        }
    }

    public static PersonasRepository getInstance() {
        if (instance == null) {
            instance = new PersonasRepository();
        }
        return instance;
    }

    // Devuelve la lista de personas como una lista observable.
    public ObservableList<Persona> getAll() throws SQLException {
        String sql = "SELECT * FROM personas";
        db.open();
        ResultSet rs = db.select(sql).orElseThrow(() -> new SQLException("Error al obtener todos las personas"));
        repository.clear();
        while (rs.next()) {
            // Lo llevamos a memoria
            repository.add(
                    new Persona(
                            rs.getString("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("calle"),
                            rs.getString("ciudad"),
                            rs.getString("email"),
                            LocalDate.parse(rs.getString("cumpleaños")),
                            rs.getString("avatar")
                    )
            );
        }
        db.close();
        if (repository.isEmpty()) {
            initData();
        }
        return repository;
    }

    public Persona findById(String id) throws SQLException {
        return repository.stream().filter(persona -> persona.getId().equals(id)).findFirst().orElseThrow(() -> new SQLException("No existe usuario con ese ID"));
    }

    // Backup de la lista de personas.
    public void backup() throws IOException {
        List<PersonaDTO> personas = repository.stream().map(PersonaDTO::new).collect(Collectors.toList());
        storage.backup(personas);
    }

    // Restaura la lista de personas.
    public void restore() throws IOException, ClassNotFoundException, SQLException {
        // Recogemos la lista de personas del backup.
        List<PersonaDTO> personas = storage.restore();
        repository.clear();
        // eliminamos la lista de personas de la base de datos.
        String sql = "DELETE FROM personas";
        db.open();
        db.update(sql);
        db.close();
        // Y la volvemos a añadir a la lista observable y la base de datos
        personas.forEach(p -> {
            try {
                create(p.fromDTO());
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void delete(Persona persona) throws IOException, SQLException {
        // Eliminamos aquí su avatar.
        deleteAvatar(persona);
        // Eliminamos de bd.
        String sql = "DELETE FROM personas WHERE id = ?";
        db.open();
        db.delete(sql, persona.getId());
        db.close();
        // Eliminamos de memoria
        repository.remove(persona);
    }

    public void create(Persona persona) throws IOException, SQLException {
        // Salvamos su avatar.
        storeAvatar(persona);
        // Salvamos en la BBDD.
        String sql = "INSERT INTO personas (id, nombre, apellidos, calle, ciudad, email, cumpleaños, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        db.open();
        db.insert(sql, persona.getId(), persona.getNombre(), persona.getApellidos(), persona.getCalle(), persona.getCiudad(), persona.getEmail(), persona.getCumpleaños().toString(), persona.getAvatar());
        db.close();
        // Salvamos en memoria
        repository.add(persona);

    }

    public void update(Persona persona) throws IOException, SQLException {
        int index = repository.indexOf(persona);
        // Actualizamos su avatar.
        storeAvatar(persona);
        // Actualizamos en la base de datos
        String sql = "UPDATE personas SET nombre = ?, apellidos = ?, calle = ?, ciudad = ?, email = ?, cumpleaños = ?, avatar = ? WHERE id = ?";
        db.open();
        db.update(sql, persona.getNombre(), persona.getApellidos(), persona.getCalle(), persona.getCiudad(), persona.getEmail(), persona.getCumpleaños().toString(), persona.getAvatar(), persona.getId());
        db.close();
        // Salvamos en memoria
        repository.set(index, persona);

    }

    private void storeAvatar(Persona p) throws IOException {
        String destination = Properties.IMAGES_DIR + File.separator + p.getId() + "." + Utils.getFileExtension(p.getAvatar()).orElse("png");
        String source = p.getAvatar().replace("file:", "");
        logger.info("Origen: " + source);
        logger.info("Destino: " + destination);
        storage.copyFile(source, destination);
        p.setAvatar(destination);
    }

    private void deleteAvatar(Persona p) throws IOException {
        String source = Properties.IMAGES_DIR + File.separator + p.getId() + "." + Utils.getFileExtension(p.getAvatar()).orElse("png");
        storage.deleteFile(source);
    }
}
