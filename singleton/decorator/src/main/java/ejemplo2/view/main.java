package ejemplo2.view;

// Principal.java
import ejemplo2.controller.ControladorCafeteria;

import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorCafeteria controlador = new ControladorCafeteria();
            VistaCafeteria vista = new VistaCafeteria(controlador);
            vista.setVisible(true);
        });
    }
}