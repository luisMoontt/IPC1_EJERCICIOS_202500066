package com.ipc1_tarea10.views;

import com.ipc1_tarea10.models.Cliente;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author Admin
 */
public class PanelBarista extends javax.swing.JPanel {
   private final int idBarista;

    private final JLabel lblEstado;
    private final JLabel lblPedido;
    private final JProgressBar barraProgreso;
    private final JLabel lblContador;

    private int clientesAtendidos = 0;

    public PanelBarista(int idBarista) {
        this.idBarista = idBarista;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 90, 43), 2),
            "Barista " + idBarista,
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(139, 90, 43)
        ));
        setBackground(new Color(241, 241, 241));
        setPreferredSize(new Dimension(220, 170));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Estado
        lblEstado = new JLabel("Libre", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstado.setForeground(new Color(0, 128, 0));
        gbc.gridy = 0;
        add(lblEstado, gbc);

        // Tipo de pedido
        lblPedido = new JLabel(" ", SwingConstants.CENTER);
        lblPedido.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblPedido.setForeground(Color.DARK_GRAY);
        gbc.gridy = 1;
        add(lblPedido, gbc);

        // Barra de progreso
        barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);
        barraProgreso.setString("En espera");
        barraProgreso.setForeground(new Color(139, 90, 43));
        barraProgreso.setBackground(new Color(141, 182, 0));
        barraProgreso.setValue(0);
        gbc.gridy = 2;
        add(barraProgreso, gbc);

        // Contador
        lblContador = new JLabel("Atendidos: 0", SwingConstants.CENTER);
        lblContador.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        gbc.gridy = 3;
        add(lblContador, gbc);
    }

    /**
     * Actualiza el panel cuando el barista toma un cliente.
     */
    public void setEstadoAtendiendo(Cliente cliente) {
        lblEstado.setText("Atendiendo a " + cliente.getNombre());
        lblEstado.setForeground(new Color(180, 0, 0));
        lblPedido.setText(cliente.getTipoPedido() + " (" + cliente.getTiempoPreparacion() + "s)");
        barraProgreso.setValue(0);
        barraProgreso.setString("Preparando...");
        setBackground(new Color(255, 235, 200));
    }

    /**
     * Actualiza la barra de progreso durante la preparación.
     */
    public void actualizarProgreso(int porcentaje) {
        barraProgreso.setValue(porcentaje);
        barraProgreso.setString(porcentaje + "%");
    }

    /**
     * Restablece el panel al estado libre.
     */
    public void setEstadoLibre() {
        lblEstado.setText("Libre");
        lblEstado.setForeground(new Color(0, 128, 0));
        lblPedido.setText(" ");
        barraProgreso.setValue(0);
        barraProgreso.setString("En espera");
        setBackground(new Color(255, 248, 240));
    }

    /**
     * Incrementa y muestra el contador de clientes atendidos.
     */
    public void incrementarContador() {
        clientesAtendidos++;
        lblContador.setText("Atendidos: " + clientesAtendidos);
    }

    public int getIdBarista() {
        return idBarista;
    } 
}
