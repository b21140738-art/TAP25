package tap.views;

import java.util.Scanner;

import static tap.views.MenuClientesview.menuClientes;
import static tap.views.MenuProductosView.menuProductos;

public class MenuPrincipalview {
    private Scanner scanner;

    public MenuPrincipalview() {
        scanner = new Scanner(System.in);
    }

    class Imprimir {
        public static <T> void imprimir(T elemento) {
            System.out.print(elemento);
        }
    }

    public void MostrarMenu() {
        boolean salir = false;

        while (!salir) {
            Imprimir.imprimir("\n===== SISTEMA DE INVENTARIO =====");
            Imprimir.imprimir("1. Gestionar Productos");
            Imprimir.imprimir("2. Gestionar Clientes");
            Imprimir.imprimir("5. Guardar y Salir");
            Imprimir.imprimir("Seleccione una opción: ");

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
                    Imprimir.imprimir("¡Gracias por usar el sistema!");
                    break;
                default:
                    Imprimir.imprimir("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void guardarDatos() {
    }

}




