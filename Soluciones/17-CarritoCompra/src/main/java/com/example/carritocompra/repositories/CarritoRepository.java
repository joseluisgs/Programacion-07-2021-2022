package com.example.carritocompra.repositories;

import com.example.carritocompra.models.CarritoItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CarritoRepository {
    private static CarritoRepository instance;

    private final ObservableList<CarritoItem> items = FXCollections.observableArrayList();

    private CarritoRepository() {
    }

    public static CarritoRepository getInstance() {
        if (instance == null) {
            instance = new CarritoRepository();
        }
        return instance;
    }

    public ObservableList<CarritoItem> getItems() {
        return items;
    }

    public void addItem(CarritoItem item) {
        items.add(item);
    }

    public void removeItem(CarritoItem item) {
        items.remove(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(CarritoItem::getTotal).sum();
    }

    public void clear() {
        items.clear();
    }
}
