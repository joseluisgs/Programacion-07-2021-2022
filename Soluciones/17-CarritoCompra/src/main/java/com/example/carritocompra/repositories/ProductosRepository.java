package com.example.carritocompra.repositories;

import com.example.carritocompra.models.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;


public class ProductosRepository {
    private static ProductosRepository instance;

    private final ObservableList<Producto> productos = FXCollections.observableArrayList();

    private ProductosRepository() {
        initData();
    }

    public static ProductosRepository getInstance() {
        if (instance == null) {
            instance = new ProductosRepository();
        }
        return instance;
    }

    private void initData() {
        productos.addAll(
                new Producto("Producto 01", 20.00, "producto01.png", true),
                new Producto("Producto 02", 30.00, "producto02.png", true),
                new Producto("Producto 03", 40.00, "producto03.png", false),
                new Producto("Producto 04", 50.00, "producto04.png", false),
                new Producto("Producto 05", 25.00, "producto05.png", false),
                new Producto("Producto 06", 35.00, "producto06.png", false),
                new Producto("Producto 07", 50.00, "producto07.png", true),
                new Producto("Producto 08", 10.50, "producto08.png", false),
                new Producto("Producto 09", 5.25, "producto09.png", false),
                new Producto("Producto 10", 15.00, "producto10.png", true),
                new Producto("Producto 11", 20.00, "producto01.png", true),
                new Producto("Producto 12", 30.00, "producto02.png", true),
                new Producto("Producto 13", 40.00, "producto03.png", false),
                new Producto("Producto 14", 50.00, "producto04.png", false),
                new Producto("Producto 15", 25.00, "producto05.png", false),
                new Producto("Producto 16", 35.00, "producto06.png", false),
                new Producto("Producto 17", 50.00, "producto07.png", true),
                new Producto("Producto 18", 10.50, "producto08.png", false),
                new Producto("Producto 19", 5.25, "producto09.png", false),
                new Producto("Producto 20", 15.00, "producto10.png", true)
        );
    }

    public ObservableList<Producto> getAllProductos() {
        return productos;
    }

    public ObservableList<Producto> getProductosWithOferta() {
        List<Producto> ofertas = productos.stream().filter(Producto::isOferta).collect(Collectors.toList());
        return FXCollections.observableArrayList(ofertas);
    }
}
