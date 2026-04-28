package com.ipc1_tarea10.controllers;



import com.ipc1_tarea10.models.Cliente;
import com.ipc1_tarea10.models.Cola;
import com.ipc1_tarea10.views.PanelBarista;
import com.ipc1_tarea10.views.VentanaPrincipal;
import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controlador principal del sistema de atención.
 * Gestiona la cola, los baristas y actualiza las estadísticas.
 */
public class SistemaController {

    private static final int NUM_BARISTAS = 3;

    private final Cola<Cliente> cola;
    private final VentanaPrincipal vista;

    private final BaristaWorker[] baristas;
    private final Thread[] hilosBaristas;

    // Estadísticas
    private final AtomicInteger totalAtendidos = new AtomicInteger(0);
    private final AtomicLong sumaEsperas = new AtomicLong(0);

    public SistemaController(VentanaPrincipal vista) {
        this.vista = vista;
        this.cola = new Cola<>();
        this.baristas = new BaristaWorker[NUM_BARISTAS];
        this.hilosBaristas = new Thread[NUM_BARISTAS];
    }

    /**
     * Inicializa y lanza los 3 hilos de baristas.
     */
    public void iniciarSistema() {
        PanelBarista[] paneles = vista.getPanelesBaristas();

        for (int i = 0; i < NUM_BARISTAS; i++) {
            baristas[i] = new BaristaWorker(i + 1, cola, paneles[i], this);
            hilosBaristas[i] = new Thread(baristas[i], "Barista-" + (i + 1));
            hilosBaristas[i].setDaemon(true); // se cierra con la app
            hilosBaristas[i].start();
        }
    }

    /**
     * Agrega un nuevo cliente a la cola y actualiza la vista.
     */
    public void agregarCliente(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            JOptionPane.showMessageDialog(vista,
                "Por favor ingresa el nombre del cliente.",
                "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nombre.trim());
        cola.encolar(cliente);

        SwingUtilities.invokeLater(() -> {
            vista.agregarClienteALista(cliente);
            vista.actualizarEstado("Cliente " + cliente.getNombre() + " agregado a la cola.");
        });
    }

    /**
     * Llamado por un BaristaWorker cuando termina de atender a un cliente.
     */
    public void clienteAtendido(Cliente cliente, long tiempoEsperaMs) {
        int total = totalAtendidos.incrementAndGet();
        sumaEsperas.addAndGet(tiempoEsperaMs);
        long promedioMs = sumaEsperas.get() / total;

        SwingUtilities.invokeLater(() -> {
            vista.removerClienteDeLista(cliente);
            vista.actualizarEstadisticas(total, promedioMs);
        });
    }

    /**
     * Detiene todos los baristas limpiamente.
     */
    public void detenerSistema() {
        for (BaristaWorker b : baristas) {
            if (b != null) b.detener();
        }
        for (Thread t : hilosBaristas) {
            if (t != null) t.interrupt();
        }
    }
}