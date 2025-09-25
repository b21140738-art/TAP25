package tap.View;

import java.util.Scanner;

/**
 * Vista del sistema, responsable de la interacción con el usuario:
 * mostrar menús, solicitar datos, imprimir mensajes.
 */
public class SistemaView {
    private final Scanner sc;

    public SistemaView() {
        sc = new Scanner(System.in);
    }

    public String pedirNombre() {
        System.out.print("Ingrese el nombre del cliente: ");
        return sc.nextLine();
    }

    public String pedirEmail() {
        System.out.print("Ingrese el email del cliente: ");
        return sc.nextLine();
    }

    public String pedirTelefono(){
        System.out.print("Ingrese el telefono del cliente: ");
        return sc.nextLine();
    }

    public double pedirSaldo(){
        System.out.print("Ingrese el saldo del cliente: ");
        return Double.parseDouble(sc.nextLine());
    }

    public String pedirCodigo() {
        System.out.print("Ingrese el código del producto: ");
        return sc.nextLine();
    }

    public int pedirId(){
        System.out.print("Ingrese el ID del cliente: ");
        return Integer.parseInt(sc.nextLine());
    }

    public String pedirNombreProducto(){
        System.out.print("Ingrese el nombre del producto: ");
        return sc.nextLine();
    }

    public double pedirPrecio(){
        System.out.print("Ingrese el precio del producto: ");
        return Double.parseDouble(sc.nextLine());
    }

    public int pedirCantidad(){
        System.out.print("Ingrese la cantidad del producto: ");
        return Integer.parseInt(sc.nextLine());
    }

    public String pedirCategoria(){
        System.out.print("Ingrese la categoria del producto: ");
        return sc.nextLine();
    }

    public String pedirFechaVencimiento(){
        System.out.print("Ingrese la fecha de vencimiento del producto (dd/mm/aaaa): ");
        return sc.nextLine();
    }

    public void menuPrincipal(){
        System.out.println("\n===== SISTEMA DE INVENTARIO =====");
        System.out.println("1. Gestionar Productos");
        System.out.println("2. Gestionar Clientes");
        System.out.println("3. Prueba Factory inventario productos ");
        System.out.println("5. Guardar y Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void menuProductos(){
        System.out.println("\n----- GESTIÓN DE PRODUCTOS -----");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Modificar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Listar Productos");
        System.out.println("5. Buscar Producto");
        System.out.println("6. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    public void menuClientes(){
        System.out.println("\n----- GESTIÓN DE CLIENTES -----");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Modificar Cliente");
        System.out.println("3. Eliminar Cliente");
        System.out.println("4. Listar Clientes");
        System.out.println("5. Buscar Cliente");
        System.out.println("6. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    public void menuDespedida(){
        System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
    }

    public void menuBuscarProd(){
        System.out.println("1. Buscar por Nombre");
        System.out.println("2. Buscar por Código");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void menuBuscarCliente(){
        System.out.println("1. Buscar por Nombre");
        System.out.println("2. Buscar por Id");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion(){
        return Integer.parseInt(sc.nextLine());
    }

    public void mensaje(String mensaje){
        System.out.println(mensaje);
    }

    public void error(String mensaje){
        System.out.println("ERROR: " + mensaje);
    }

    public String confirmacion() {
        mensaje("Desea eliminar este cliente? (s/n): ");
        return sc.nextLine();
    }
        //metodo para pedir el tipo de cliente
    public String pedirTipoCliente(){
        System.out.print("Ingrese tipo de cliente (VIP-FRECUENTE-NORMAL: ");
        return sc.nextLine();
    }
}

