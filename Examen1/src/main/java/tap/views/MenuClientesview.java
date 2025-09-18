package tap.views;

public class MenuClientesview {

    static void menuClientes() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n----- GESTIÓN DE CLIENTES -----");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Buscar Cliente");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    modificarCliente();
                    break;
                case 3:
                    eliminarCliente();
                    break;
                case 4:
                    listarClientes();
                    break;
                case 5:
                    buscarCliente();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
