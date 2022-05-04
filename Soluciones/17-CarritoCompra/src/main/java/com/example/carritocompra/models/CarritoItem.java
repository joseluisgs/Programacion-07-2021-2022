package com.example.carritocompra.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CarritoItem {
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty imagen;
    private final SimpleDoubleProperty precio;
    private final SimpleIntegerProperty cantidad;
    private double total;

    public CarritoItem(String nombre, String imagen, double precio, int cantidad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.imagen = new SimpleStringProperty(imagen);
        this.precio = new SimpleDoubleProperty(precio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.total = precio * cantidad;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public String getImagen() {
        return imagen.get();
    }

    public void setImagen(String imagen) {
        this.imagen.set(imagen);
    }

    public SimpleStringProperty imagenProperty() {
        return imagen;
    }

    public double getPrecio() {
        return precio.get();
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
        this.total = precio.get() * cantidad;
    }

    public SimpleIntegerProperty cantidadProperty() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }


    @Override
    public String toString() {
        return "CarritoItem{" +
                "nombre=" + nombre +
                ", imagen=" + imagen +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }
}
