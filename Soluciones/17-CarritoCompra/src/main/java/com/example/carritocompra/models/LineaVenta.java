package com.example.carritocompra.models;

public class LineaVenta {
    private final String producto;
    private final int cantidad;
    private final double precio;
    private final double total;

    public LineaVenta(String producto, int cantidad, double precio, double total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public String getProducto() {
        return producto;
    }


    public int getCantidad() {
        return cantidad;
    }


    public double getPrecio() {
        return precio;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "LineaVenta{" +
                "producto='" + producto + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", total=" + total +
                '}';
    }
}
