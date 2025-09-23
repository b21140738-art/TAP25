package tap.View;

import tap.Controller.SistemaController;


/**
 * Clase principal para arrancar la aplicación.
 */
public class Main {
    public static void main(String[] args) {
        tap.View.SistemaView view = new tap.View.SistemaView();
        SistemaController controller = new SistemaController(view);
        controller.iniciar();
    }
}
