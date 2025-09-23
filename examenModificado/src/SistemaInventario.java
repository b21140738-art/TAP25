/* 
---------------------- Examen 1 Ejercicio 1----------------------
 1.- Ejecutar y entender el siguiente código "SistemaInventario"
Reestructurar el código utilizando: 
 2.- Programación orientada a objetos
 3.- Patrón de diseño MVC
 4.- Programación genérica


Puntos a calificar
1.- Uso correcto de clases y objetos, abstracción del problema (5pts)
2.- Estructura de carpetas MVC (5pts)
3.- Ubicación correcta de las clases según su funcionalidad (10pts)
4.- Eliminación de código duplicado (10pts)
5.- Implementación correcta de programación genérica "al menos un concepto" (10pts)
*/

import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SistemaInventario {
    
    static String[] codigos = new String[100];
    static String[] nombres = new String[100];
    static double[] precios = new double[100];
    static int[] cantidades = new int[100];
    static String[] categorias = new String[100];
    static String[] fechasVencimiento = new String[100];
    static int totalProductos = 0;
    
    static String[] clientesNombres = new String[50];
    static String[] clientesEmails = new String[50];
    static String[] clientesTelefonos = new String[50];
    static double[] clientesSaldos = new double[50];
    static int totalClientes = 0;
    
    static int[] ventasProductos = new int[200];
    static int[] ventasCantidades = new int[200];
    static double[] ventasPrecios = new double[200];
    static String[] ventasFechas = new String[200];
    static int[] ventasClientes = new int[200];
    static int totalVentas = 0;
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        //model
        //cargarDatos();
        
        boolean salir = false;
        //view
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
                    //guardarDatos();
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    static void menuProductos() {
        boolean volver = false;
        //view
        while (!volver) {
            System.out.println("\n----- GESTIÓN DE PRODUCTOS -----");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Listar Productos");
            System.out.println("5. Buscar Producto");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            //controller
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    modificarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    listarProductos();
                    break;
                case 5:
                    buscarProducto();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    //model
    static void agregarProducto() {
        if (totalProductos >= 100) {
            System.out.println("No se pueden agregar más productos. Límite alcanzado.");
            return;
        }
        
        System.out.println("\n--- AGREGAR NUEVO PRODUCTO ---");
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        // Verificar si el código ya existe
        for (int i = 0; i < totalProductos; i++) {
            if (codigos[i].equals(codigo)) {
                System.out.println("Error: El código ya existe. No se puede agregar el producto.");
                return;
            }
        }
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        
        System.out.print("Fecha de Vencimiento (DD/MM/YYYY) o N/A: ");
        String fechaVencimiento = scanner.nextLine();
        
        codigos[totalProductos] = codigo;
        nombres[totalProductos] = nombre;
        precios[totalProductos] = precio;
        cantidades[totalProductos] = cantidad;
        categorias[totalProductos] = categoria;
        fechasVencimiento[totalProductos] = fechaVencimiento;
        
        totalProductos++;
        
        System.out.println("Producto agregado con éxito.");
    }
    
    static void modificarProducto() {
        System.out.println("\n--- MODIFICAR PRODUCTO ---");
        System.out.print("Ingrese el código del producto a modificar: ");
        String codigo = scanner.nextLine();
        
        int indice = -1;
        for (int i = 0; i < totalProductos; i++) {
            if (codigos[i].equals(codigo)) {
                indice = i;
                break;
            }
        }
        
        if (indice == -1) {
            System.out.println("Producto no encontrado.");
            return;
        }
        
        System.out.println("Producto encontrado: " + nombres[indice]);
        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
        
        System.out.print("Nombre [" + nombres[indice] + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            nombres[indice] = nombre;
        }
        
        System.out.print("Precio [" + precios[indice] + "]: ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) {
            precios[indice] = Double.parseDouble(precioStr);
        }
        
        System.out.print("Cantidad [" + cantidades[indice] + "]: ");
        String cantidadStr = scanner.nextLine();
        if (!cantidadStr.isEmpty()) {
            cantidades[indice] = Integer.parseInt(cantidadStr);
        }
        
        System.out.print("Categoría [" + categorias[indice] + "]: ");
        String categoria = scanner.nextLine();
        if (!categoria.isEmpty()) {
            categorias[indice] = categoria;
        }
        
        System.out.print("Fecha de Vencimiento [" + fechasVencimiento[indice] + "]: ");
        String fechaVencimiento = scanner.nextLine();
        if (!fechaVencimiento.isEmpty()) {
            fechasVencimiento[indice] = fechaVencimiento;
        }
        
        System.out.println("Producto modificado con éxito.");
    }
    
    static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        
        int indice = -1;
        for (int i = 0; i < totalProductos; i++) {
            if (codigos[i].equals(codigo)) {
                indice = i;
                break;
            }
        }
        
        if (indice == -1) {
            System.out.println("Producto no encontrado.");
            return;
        }
        
        System.out.println("Producto encontrado: " + nombres[indice]);
        System.out.print("¿Está seguro que desea eliminar este producto? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            // Desplazar los elementos
            for (int i = indice; i < totalProductos - 1; i++) {
                codigos[i] = codigos[i + 1];
                nombres[i] = nombres[i + 1];
                precios[i] = precios[i + 1];
                cantidades[i] = cantidades[i + 1];
                categorias[i] = categorias[i + 1];
                fechasVencimiento[i] = fechasVencimiento[i + 1];
            }
            
            totalProductos--;
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    static void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        System.out.println("Código\tNombre\t\t\tPrecio\tCantidad\tCategoría\tVencimiento");
        System.out.println("--------------------------------------------------------------------------------");
        
        for (int i = 0; i < totalProductos; i++) {
            System.out.printf("%-7s %-20s %.2f\t%-10d %-15s %s\n",
                    codigos[i], nombres[i], precios[i], cantidades[i], categorias[i], fechasVencimiento[i]);
        }
        
        if (totalProductos == 0) {
            System.out.println("No hay productos registrados.");
        }
    }
    
    static void buscarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.println("1. Buscar por Código");
        System.out.println("2. Buscar por Nombre");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el código a buscar: ");
                String codigo = scanner.nextLine();
                
                boolean encontrado = false;
                for (int i = 0; i < totalProductos; i++) {
                    if (codigos[i].equals(codigo)) {
                        mostrarDetalleProducto(i);
                        encontrado = true;
                        break;
                    }
                }
                
                if (!encontrado) {
                    System.out.println("Producto no encontrado.");
                }
                break;
                
            case 2:
                System.out.print("Ingrese el nombre a buscar: ");
                String nombre = scanner.nextLine().toLowerCase();
                
                boolean encontradoNombre = false;
                for (int i = 0; i < totalProductos; i++) {
                    if (nombres[i].toLowerCase().contains(nombre)) {
                        mostrarDetalleProducto(i);
                        encontradoNombre = true;
                    }
                }
                
                if (!encontradoNombre) {
                    System.out.println("No se encontraron productos con ese nombre.");
                }
                break;
                
            default:
                System.out.println("Opción no válida.");
        }
    }
    
    static void mostrarDetalleProducto(int indice) {
        System.out.println("\nDetalle del Producto:");
        System.out.println("Código: " + codigos[indice]);
        System.out.println("Nombre: " + nombres[indice]);
        System.out.println("Precio: $" + precios[indice]);
        System.out.println("Cantidad en stock: " + cantidades[indice]);
        System.out.println("Categoría: " + categorias[indice]);
        System.out.println("Fecha de Vencimiento: " + fechasVencimiento[indice]);
    }
    
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
    
    static void agregarCliente() {
        if (totalClientes >= 50) {
            System.out.println("No se pueden agregar más clientes. Límite alcanzado.");
            return;
        }
        
        System.out.println("\n--- AGREGAR NUEVO CLIENTE ---");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        
        clientesNombres[totalClientes] = nombre;
        clientesEmails[totalClientes] = email;
        clientesTelefonos[totalClientes] = telefono;
        clientesSaldos[totalClientes] = saldo;
        
        totalClientes++;
        
        System.out.println("Cliente agregado con éxito.");
    }
    
    static void modificarCliente() {
        System.out.println("\n--- MODIFICAR CLIENTE ---");
        System.out.print("Ingrese el número de cliente a modificar (1-" + totalClientes + "): ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea
        
        if (indice < 0 || indice >= totalClientes) {
            System.out.println("Número de cliente no válido.");
            return;
        }
        
        System.out.println("Cliente encontrado: " + clientesNombres[indice]);
        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
        
        System.out.print("Nombre [" + clientesNombres[indice] + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            clientesNombres[indice] = nombre;
        }
        
        System.out.print("Email [" + clientesEmails[indice] + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            clientesEmails[indice] = email;
        }
        
        System.out.print("Teléfono [" + clientesTelefonos[indice] + "]: ");
        String telefono = scanner.nextLine();
        if (!telefono.isEmpty()) {
            clientesTelefonos[indice] = telefono;
        }
        
        System.out.print("Saldo [" + clientesSaldos[indice] + "]: ");
        String saldoStr = scanner.nextLine();
        if (!saldoStr.isEmpty()) {
            clientesSaldos[indice] = Double.parseDouble(saldoStr);
        }
        
        System.out.println("Cliente modificado con éxito.");
    }
    
    static void eliminarCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        System.out.print("Ingrese el número de cliente a eliminar (1-" + totalClientes + "): ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea
        
        if (indice < 0 || indice >= totalClientes) {
            System.out.println("Número de cliente no válido.");
            return;
        }
        
        System.out.println("Cliente encontrado: " + clientesNombres[indice]);
        System.out.print("¿Está seguro que desea eliminar este cliente? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            // Verificar si el cliente tiene ventas asociadas
            boolean tieneVentas = false;
            for (int i = 0; i < totalVentas; i++) {
                if (ventasClientes[i] == indice) {
                    tieneVentas = true;
                    break;
                }
            }
            
            if (tieneVentas) {
                System.out.println("No se puede eliminar el cliente porque tiene ventas asociadas.");
                return;
            }
            
            // Desplazar los elementos
            for (int i = indice; i < totalClientes - 1; i++) {
                clientesNombres[i] = clientesNombres[i + 1];
                clientesEmails[i] = clientesEmails[i + 1];
                clientesTelefonos[i] = clientesTelefonos[i + 1];
                clientesSaldos[i] = clientesSaldos[i + 1];
            }
            
            totalClientes--;
            System.out.println("Cliente eliminado con éxito.");
            
            // Actualizar índices de clientes en ventas
            for (int i = 0; i < totalVentas; i++) {
                if (ventasClientes[i] > indice) {
                    ventasClientes[i]--;
                }
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        System.out.println("ID\tNombre\t\t\tEmail\t\t\tTeléfono\tSaldo");
        System.out.println("--------------------------------------------------------------------------------");
        
        for (int i = 0; i < totalClientes; i++) {
            System.out.printf("%-3d %-20s %-20s %-15s $%.2f\n",
                    (i + 1), clientesNombres[i], clientesEmails[i], clientesTelefonos[i], clientesSaldos[i]);
        }
        
        if (totalClientes == 0) {
            System.out.println("No hay clientes registrados.");
        }
    }
    
    static void buscarCliente() {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        System.out.println("1. Buscar por Nombre");
        System.out.println("2. Buscar por Email");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nombre a buscar: ");
                String nombre = scanner.nextLine().toLowerCase();
                
                boolean encontradoNombre = false;
                for (int i = 0; i < totalClientes; i++) {
                    if (clientesNombres[i].toLowerCase().contains(nombre)) {
                        mostrarDetalleCliente(i);
                        encontradoNombre = true;
                    }
                }
                
                if (!encontradoNombre) {
                    System.out.println("No se encontraron clientes con ese nombre.");
                }
                break;
                
            case 2:
                System.out.print("Ingrese el email a buscar: ");
                String email = scanner.nextLine().toLowerCase();
                
                boolean encontradoEmail = false;
                for (int i = 0; i < totalClientes; i++) {
                    if (clientesEmails[i].toLowerCase().contains(email)) {
                        mostrarDetalleCliente(i);
                        encontradoEmail = true;
                    }
                }
                
                if (!encontradoEmail) {
                    System.out.println("No se encontraron clientes con ese email.");
                }
                break;
                
            default:
                System.out.println("Opción no válida.");
        }
    }
    
    static void mostrarDetalleCliente(int indice) {
        System.out.println("\nDetalle del Cliente:");
        System.out.println("ID: " + (indice + 1));
        System.out.println("Nombre: " + clientesNombres[indice]);
        System.out.println("Email: " + clientesEmails[indice]);
        System.out.println("Teléfono: " + clientesTelefonos[indice]);
        System.out.println("Saldo: $" + clientesSaldos[indice]);
    }
    
}    
