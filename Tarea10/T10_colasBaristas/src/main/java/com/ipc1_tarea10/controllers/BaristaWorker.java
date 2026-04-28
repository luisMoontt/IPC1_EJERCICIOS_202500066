package com.ipc1_tarea10.controllers;

import com.ipc1_tarea10.models.Cliente;
import com.ipc1_tarea10.models.Cola;
import com.ipc1_tarea10.views.PanelBarista;


import javax.swing.*;

/**
 * @author Admin
 */
public class BaristaWorker implements Runnable{
    private final int idBarista;
    private final Cola<Cliente> cola;
    private final PanelBarista panelBarista;
    private final SistemaController controller;

    private volatile boolean activo = true;

    public BaristaWorker(int idBarista, Cola<Cliente> cola,
                         PanelBarista panelBarista, SistemaController controller) {
        this.idBarista = idBarista;
        this.cola = cola;
        this.panelBarista = panelBarista;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (activo) {
            // Intentar tomar un cliente de la cola (sincronizado en Cola)
            Cliente cliente = cola.desencolar();

            if (cliente != null) {
                atenderCliente(cliente);
            } else {
                // Cola vacía: esperar brevemente antes de revisar de nuevo
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    /**
     * Simula la atención de un cliente con progreso visual.
     */
    private void atenderCliente(Cliente cliente) {
        long tiempoEspera = cliente.calcularTiempoEspera();
        int totalMs = cliente.getTiempoPreparacion() * 1000;
        int intervalMs = 100; // actualizar barra cada 100ms
        int pasos = totalMs / intervalMs;

        // Actualizar UI: barista ocupado
        SwingUtilities.invokeLater(() ->
            panelBarista.setEstadoAtendiendo(cliente)
        );

        // Simular preparación con progreso
        for (int i = 1; i <= pasos && activo; i++) {
            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            int progreso = (int) ((i / (double) pasos) * 100);
            SwingUtilities.invokeLater(() ->
                panelBarista.actualizarProgreso(progreso)
            );
        }

        // Notificar al controller que terminó
        controller.clienteAtendido(cliente, tiempoEspera);

        // Actualizar UI: barista libre
        SwingUtilities.invokeLater(() -> {
            panelBarista.setEstadoLibre();
            panelBarista.incrementarContador();
        });
    }

    public void detener() {
        activo = false;
    }

    public int getIdBarista() {
        return idBarista;
    }  
}
