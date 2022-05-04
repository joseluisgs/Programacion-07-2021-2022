package es.joseluisgs.agenda.dto;

import es.joseluisgs.agenda.models.Persona;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class PersonaDTO implements Serializable {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final String apellidos;
    private final String calle;
    private final String ciudad;
    private final String email;
    private final LocalDate cumpleaños;
    private final String avatar;

    public PersonaDTO(Persona persona) {
        this.nombre = persona.getNombre();
        this.apellidos = persona.getApellidos();
        this.calle = persona.getCalle();
        this.ciudad = persona.getCiudad();
        this.email = persona.getEmail();
        this.cumpleaños = persona.getCumpleaños();
        this.avatar = persona.getAvatar();
    }

    public Persona fromDTO() {
        return new Persona(id, nombre, apellidos, calle, ciudad, email, cumpleaños, avatar);
    }
}
