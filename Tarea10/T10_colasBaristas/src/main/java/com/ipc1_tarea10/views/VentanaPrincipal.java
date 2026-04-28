package com.ipc1_tarea10.views;
import com.ipc1_tarea10.controllers.SistemaController;
import com.ipc1_tarea10.models.Cliente;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Admin
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    private final PanelBarista[] panelesBaristas;

    // Panel de ingreso
    private JTextField txtNombreCliente;
    private JButton btnAgregar;

    // Lista de cola
    private DefaultListModel<String> modeloLista;
    private JList<String> listaCola;

    // Estadísticas
    private JLabel lblTotalAtendidos;
    private JLabel lblTiempoPromedio;

    // Estado
    private JLabel lblEstadoSistema;

    // Referencia al controlador (se inyecta después)
    private SistemaController controller;

    public VentanaPrincipal() {
        panelesBaristas = new PanelBarista[3];
        initComponents();
        configurarVentana();
    }

    /**
     * Inyecta el controlador y configura los listeners.
     */
    public void setController(SistemaController controller) {
        this.controller = controller;

        btnAgregar.addActionListener(e -> {
            controller.agregarCliente(txtNombreCliente.getText());
            txtNombreCliente.setText("");
            txtNombreCliente.requestFocus();
        });

        txtNombreCliente.addActionListener(e -> btnAgregar.doClick());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.detenerSistema();
                dispose();
                System.exit(0);
            }
        });
    }

    private void configurarVentana() {
        //setTitle("☕ Sistema de Atención - Cafetería");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(900, 620));
        setLocationRelativeTo(null);
        pack();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 235, 220));

        add(crearPanelNorte(), BorderLayout.NORTH);
        add(crearPanelCentro(), BorderLayout.CENTER);
        //add(crearPanelSur(), BorderLayout.SOUTH);
    }

    // ── NORTE: ingreso + estadísticas ──────────────────────────────────────

    private JPanel crearPanelNorte() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(245, 235, 220));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        // Título
        JLabel titulo = new JLabel("CAFETERIA", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        //titulo.setForeground(new Color(255, 255, ));
        panel.add(titulo, BorderLayout.NORTH);

        // Subpanel: ingreso + estadísticas
        JPanel subPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        subPanel.setOpaque(false);
        //subPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        subPanel.add(crearPanelIngreso());
        subPanel.add(crearPanelEstadisticas());
        panel.add(subPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelIngreso() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 90, 43)),
            "Agregar Cliente",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            new Color(0, 0, 0)
        ));

        panel.add(new JLabel("Nombre:"));

        txtNombreCliente = new JTextField(18);
        txtNombreCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtNombreCliente);

        btnAgregar = new JButton("Agregar a Cola");
        btnAgregar.setBackground(new Color(139, 90, 43));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(btnAgregar);

        return panel;
    }

    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 5));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 90, 43)),
            "Estadísticas Globales",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            new Color(139, 90, 43)
        ));

        lblTotalAtendidos = new JLabel("  Atendidos: 0");
        lblTotalAtendidos.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        lblTiempoPromedio = new JLabel("  Tiempo promedio de espera: 0s");
        lblTiempoPromedio.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        panel.add(lblTotalAtendidos);
        panel.add(lblTiempoPromedio);

        return panel;
    }

    // ── CENTRO: cola de espera + baristas ─────────────────────────────────

    private JPanel crearPanelCentro() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        panel.add(crearPanelCola(), BorderLayout.WEST);
        panel.add(crearPanelBaristas(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelCola() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 248, 240));
        panel.setPreferredSize(new Dimension(230, 0));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 90, 43)),
            "Cola de Espera",
            TitledBorder.CENTER, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 13),
            new Color(139, 90, 43)
        ));

        modeloLista = new DefaultListModel<>();
        listaCola = new JList<>(modeloLista);
        listaCola.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        listaCola.setBackground(new Color(255, 252, 245));
        listaCola.setCellRenderer(new ClienteListRenderer());

        JScrollPane scroll = new JScrollPane(listaCola);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelBaristas() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 90, 43)),
            "Servicio",
            TitledBorder.CENTER, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 13),
            new Color(139, 90, 43)
        ));

        for (int i = 0; i < 3; i++) {
            panelesBaristas[i] = new PanelBarista(i + 1);
            panel.add(panelesBaristas[i]);
        }

        return panel;
    }

    // ── SUR: barra de estado ───────────────────────────────────────────────

//    private JPanel crearPanelSur() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBackground(new Color(139, 90, 43));
//        panel.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
//
//        lblEstadoSistema = new JLabel("Sistema iniciado. Baristas listos.");
//        lblEstadoSistema.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        lblEstadoSistema.setForeground(Color.WHITE);
//        panel.add(lblEstadoSistema, BorderLayout.WEST);
//
//        return panel;
//    }

    // ── Métodos públicos para el controlador ──────────────────────────────

    public void agregarClienteALista(Cliente cliente) {
        modeloLista.addElement(cliente.toString());
    }

    public void removerClienteDeLista(Cliente cliente) {
        String texto = cliente.toString();
        modeloLista.removeElement(texto);
    }

    public void actualizarEstadisticas(int total, long promedioMs) {
        lblTotalAtendidos.setText("  Total atendidos: " + total);
        lblTiempoPromedio.setText(String.format("  Tiempo promedio de espera: %.1fs",
            promedioMs / 1000.0));
    }

    public void actualizarEstado(String mensaje) {
        //lblEstadoSistema.setText(mensaje);
    }

    public PanelBarista[] getPanelesBaristas() {
        return panelesBaristas;
    }

    // ── Renderer personalizado para la lista ──────────────────────────────

    private static class ClienteListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
            label.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
            if (!isSelected) {
                label.setBackground(index % 2 == 0
                    ? new Color(255, 252, 245)
                    : new Color(245, 235, 220));
            }
            return label;
        }
    }
}
