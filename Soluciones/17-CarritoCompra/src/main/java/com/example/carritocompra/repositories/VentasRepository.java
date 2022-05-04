package com.example.carritocompra.repositories;

import com.example.carritocompra.models.Cliente;
import com.example.carritocompra.models.LineaVenta;
import com.example.carritocompra.models.Venta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentasRepository {
    private static VentasRepository instance;
    Map<String, Venta> ventas;

    private VentasRepository() {
        ventas = new HashMap<>();
    }

    public static VentasRepository getInstance() {
        if (instance == null) {
            instance = new VentasRepository();
        }
        return instance;
    }

    public Venta save(List<LineaVenta> lineasVenta, Cliente cliente) {
        System.out.println("Guardando venta...");
        Venta venta = new Venta(lineasVenta, cliente);
        ventas.put(venta.getId(), venta);
        // Simulamos esto que no es necesario
        return ventas.get(venta.getId());
    }
}
