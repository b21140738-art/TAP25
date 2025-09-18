package tap;

import java.util.Scanner;

public class SistemaInventario {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        cargarDatos();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== SISTEMA DE INVENTARIO =====");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Clientes");
            System.out.println("5. Guardar y Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    menuProductos();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 5:
                    guardarDatos();
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    }
}
