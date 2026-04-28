package com.ipc1_tarea10.main;

import com.ipc1_tarea10.controllers.SistemaController;
import com.ipc1_tarea10.views.VentanaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class T10_colasBaristas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            // 1. Crear Vista
            VentanaPrincipal vista = new VentanaPrincipal();

            // 2. Crear Controlador (recibe la vista)
            SistemaController controller = new SistemaController(vista);

            // 3. Inyectar controlador en la vista (listeners)
            vista.setController(controller);

            // 4. Iniciar hilos de baristas
            controller.iniciarSistema();

            // 5. Mostrar ventana
            vista.setVisible(true);
        });
    }
}
