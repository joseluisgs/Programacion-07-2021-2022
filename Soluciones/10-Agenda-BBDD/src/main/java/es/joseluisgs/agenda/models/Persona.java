package es.joseluisgs.agenda.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Que son las propiedades
 * Con JavaFX es habitual usar Propiedades para todos los atributos de un clase usada como modelo.
 * Una Propiedad permite, entre otras cosas, recibir notificaciones automáticamente cuando el valor
 * de una variable cambia (por ejemplo si cambia lastName. Esto ayuda a mantener sincronizados la vista y los datos.
 * Implementa el patrón Observer y con ello tener ractividad
 * Para aprender más sobre Propiedades lee Using JavaFX Properties and Binding.
 * https://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm
 */
public class Persona {
    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty calle;
    private final StringProperty ciudad;
    private final StringProperty email;
    private final ObjectProperty<LocalDate> cumpleaños;
    private final StringProperty avatar;
    private String id = UUID.randomUUID().toString();

    /**
     * Default constructor.
     */
    public Persona() {
        this(null, null, null, null, null, null, null);
    }

    public Persona(String nombre, String apellidos, String calle, String ciudad, String email, LocalDate cumpleaños, String avatar) {
        this.id = id;
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.calle = new SimpleStringProperty(calle);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.email = new SimpleStringProperty(email);
        this.cumpleaños = new SimpleObjectProperty<LocalDate>(cumpleaños);
        this.avatar = new SimpleStringProperty(avatar);
    }

    public Persona(String id, String nombre, String apellidos, String calle, String ciudad, String email, LocalDate cumpleaños, String avatar) {
        this.id = id;
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.calle = new SimpleStringProperty(calle);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.email = new SimpleStringProperty(email);
        this.cumpleaños = new SimpleObjectProperty<LocalDate>(cumpleaños);
        this.avatar = new SimpleStringProperty(avatar);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public String getCalle() {
        return calle.get();
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public StringProperty calleProperty() {
        return calle;
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public LocalDate getCumpleaños() {
        return cumpleaños.get();
    }

    public void setCumpleaños(LocalDate cumpleaños) {
        this.cumpleaños.set(cumpleaños);
    }

    public ObjectProperty<LocalDate> cumpleañosProperty() {
        return cumpleaños;
    }

    public String getAvatar() {
        return avatar.get();
    }

    public void setAvatar(String avatar) {
        this.avatar.set(avatar);
    }

    public StringProperty avatarProperty() {
        return avatar;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", nombre=" + nombre +
                ", apellidos=" + apellidos +
                ", calle=" + calle +
                ", ciudad=" + ciudad +
                ", email=" + email +
                ", cumpleaños=" + cumpleaños +
                ", avatar=" + avatar +
                '}';
    }
}