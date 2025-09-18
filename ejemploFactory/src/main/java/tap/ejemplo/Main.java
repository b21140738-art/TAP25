package tap.ejemplo;

import tap.ejemplo.controller.ProductoController;
import java.util.Scanner;

/**
 * Clase principal que inicia la aplicación.
 */
public class Main {
    /**
     * Método principal que ejecuta el flujo de la aplicación.
     * @param args argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        ProductoController controller = new ProductoController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema de Productos ===");
        while (true) {
            System.out.print("Ingrese tipo de producto (A/B) A)ELECTRONICO B)ALIMENTO, 'ver' para mostrar, 'salir' para terminar: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("salir")) break;
            if (input.equalsIgnoreCase("ver")) {
                controller.mostrarProductos();
            } else {
                controller.agregarProducto(input);
            }
        }

        System.out.println("Programa finalizado.");
    }
}