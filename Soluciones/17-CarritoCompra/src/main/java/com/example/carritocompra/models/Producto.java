package com.example.carritocompra.models;

import java.time.LocalDate;
import java.util.UUID;

public class Producto {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final double precio;
    private final String imagen;
    private final LocalDate createdAt = LocalDate.now();
    private final boolean isOferta;

    public Producto(String nombre, double precio, String imagen, boolean isOferta) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.isOferta = isOferta;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public boolean isOferta() {
        return isOferta;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre=" + nombre +
                ", precio=" + precio +
                ", avatar=" + imagen +
                ", createdAt=" + createdAt +
                ", isOferta=" + isOferta +
                '}';
    }
}
