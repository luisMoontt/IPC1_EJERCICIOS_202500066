package com.ipc1_tarea10.models;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Admin
 */
public class Cliente {
     // Contador estático para generar IDs únicos
    private static final AtomicInteger contadorId = new AtomicInteger(1);

    private static final String[] TIPOS_PEDIDO = {
        "Espresso", "Cappuccino", "Latte", "Americano",
        "Mocha", "Frappuccino", "Té Verde", "Chocolate Caliente"
    };

    private final int id;
    private final String nombre;
    private final String tipoPedido;
    private final int tiempoPreparacion; // en segundos (2-5)
    private final long tiempoIngreso;    // milisegundos epoch

    public Cliente(String nombre) {
        this.id = contadorId.getAndIncrement();
        this.nombre = nombre;
        this.tipoPedido = TIPOS_PEDIDO[new Random().nextInt(TIPOS_PEDIDO.length)];
        this.tiempoPreparacion = 2 + new Random().nextInt(4); // 2, 3, 4 o 5
        this.tiempoIngreso = System.currentTimeMillis();
    }

    // --- Getters ---

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public long getTiempoIngreso() {
        return tiempoIngreso;
    }

    /**
     * Calcula cuánto tiempo esperó el cliente desde que ingresó
     * hasta que fue atendido (en milisegundos).
     */
    public long calcularTiempoEspera() {
        return System.currentTimeMillis() - tiempoIngreso;
    }

    @Override
    public String toString() {
        return String.format("#%d - %s (%s, %ds)", id, nombre, tipoPedido, tiempoPreparacion);
    }
}
