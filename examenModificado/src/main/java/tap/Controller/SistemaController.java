package tap.Controller;

import tap.Factory.ClienteFactory;
import tap.Factory.InventarioFactory;
import tap.Factory.ProductoFactory;
import tap.Model.ClienteModel;
import tap.Model.InventarioModel;
import tap.Model.ListaGenerica;
import tap.View.SistemaView;

/**
 * Controlador principal del sistema de inventario.
 * Se encarga de coordinar entre la vista, los modelos y las fábricas.
 */
public class SistemaController {
    private final ListaGenerica<InventarioModel> listaInventario;
    private final ListaGenerica<ClienteModel> listaClientes;
    private final SistemaView view;

    /**
     * Constructor del controlador.
     *
     * @param view La vista que interactuará con el usuario.
     */
    public SistemaController(SistemaView view) {
        this.view = view;
        this.listaInventario = new ListaGenerica<>();
        this.listaClientes = new ListaGenerica<>();
    }

    /**
     * Inicia el sistema. Controla el menú principal y las opciones del usuario.
     */
    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            view.menuPrincipal();
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1 -> productos();
                case 2 -> clientes();
                case 3 -> pruebaFactoryProducto();
                case 5 -> {
                    view.mensaje("Guardando y saliendo...");
                    view.menuDespedida();
                    salir = true;
                }
                default -> view.error("Opción inválida");
            }
        }
    }

    /**
     * Menú de gestión de productos.
     */
    public void productos() {
        boolean volver = false;
        while (!volver) {
            view.menuProductos();
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> modificarProducto();
                case 3 -> eliminarProducto();
                case 4 -> listarProductos();
                case 5 -> menuBuscar();
                case 6 -> volver = true;
                default -> view.error("Opción no válida");
            }
        }
    }

    /**
     * Menú de gestión de clientes.
     */
    public void clientes() {
        boolean volver = false;
        while (!volver) {
            view.menuClientes();
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> modificarCliente();
                case 3 -> eliminarCliente();
                case 4 -> listarClientes();
                case 5 -> menuBuscarClientes();
                case 6 -> volver = true;
                default -> view.error("Opción no válida");
            }
        }
    }

    /**
     * Agrega un nuevo cliente utilizando ClienteFactory.
     */
    public void agregarCliente() {
        int id = view.pedirId();
        String nombre = view.pedirNombre();
        String email = view.pedirEmail();
        String telefono = view.pedirTelefono();
        double saldo = view.pedirSaldo();

        ClienteModel nuevoCliente = ClienteFactory.crearCliente(id, nombre, email, telefono, saldo);
        listaClientes.agregar(nuevoCliente);
        view.mensaje("Cliente agregado correctamente.");
    }

    /**
     * Modifica los datos de un cliente existente.
     */
    public void modificarCliente() {
        view.mensaje("----------- Modificar Cliente -----------");
        view.mensaje("Ingrese el numero del cliente a modificar (1 - " + listaClientes.size() + "): ");
        int index = view.leerOpcion();
        if (index < 1 || index > listaClientes.size()) {
            view.error("Número inválido");
            return;
        }
        ClienteModel clienteViejo = listaClientes.get(index - 1);
        view.mensaje("Cliente encontrado: " + clienteViejo.getNombre());
        view.mensaje("Ingrese los nuevos datos:");
        int id = view.pedirId();
        String nombre = view.pedirNombre();
        String email = view.pedirEmail();
        String telefono = view.pedirTelefono();
        double saldo = view.pedirSaldo();
        ClienteModel clienteModificado = ClienteFactory.crearCliente(id, nombre, email, telefono, saldo);
        listaClientes.actualizar(index - 1, clienteModificado);
        view.mensaje("Cliente modificado correctamente.");
    }

    /**
     * Elimina un cliente después de confirmación.
     */
    public void eliminarCliente() {
        view.mensaje("----------- Eliminar Cliente -----------");
        view.mensaje("Ingrese el numero del cliente a eliminar (1 - " + listaClientes.size() + "): ");
        int index = view.leerOpcion();
        if (index < 1 || index > listaClientes.size()) {
            view.error("Número inválido");
            return;
        }
        ClienteModel cliente = listaClientes.get(index - 1);
        view.mensaje("Cliente encontrado: " + cliente.getNombre());
        String confirm = view.confirmacion();
        if (confirm.equalsIgnoreCase("s")) {
            listaClientes.eliminar(index - 1);
            view.mensaje("Cliente eliminado.");
        } else {
            view.mensaje("Operación cancelada.");
        }
    }

    /**
     * Lista todos los clientes registrados.
     */
    public void listarClientes() {
        view.mensaje("----------- Lista de Clientes -----------");
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteModel c = listaClientes.get(i);
            view.mensaje((i + 1) + ". ID: " + c.getId()
                    + ", Nombre: " + c.getNombre()
                    + ", Email: " + c.getEmail()
                    + ", Teléfono: " + c.getTelefono()
                    + ", Saldo: " + c.getSaldo());
        }
    }

    /**
     * Agrega un nuevo producto utilizando ProductoFactory.
     */
    public void agregarProducto() {
        if (listaInventario.size() >= 100) {
            view.error("No se pueden agregar más productos, inventario lleno");
            return;
        }

        String codigo = view.pedirCodigo();
        String nombre = view.pedirNombreProducto();
        double precio = view.pedirPrecio();
        int cantidad = view.pedirCantidad();
        String categoria = view.pedirCategoria();
        String fechaVencimiento = view.pedirFechaVencimiento();

        InventarioModel nuevoProducto = InventarioFactory.crearProducto(codigo, nombre, precio, cantidad, categoria, fechaVencimiento);
        listaInventario.agregar(nuevoProducto);
        view.mensaje("Producto agregado correctamente.");
    }

    /**
     * Modifica un producto existente por código.
     */
    public void modificarProducto() {
        view.mensaje("----------- Modificar Producto -----------");
        String codigo = view.pedirCodigo();
        for (int i = 0; i < listaInventario.size(); i++) {
            InventarioModel p = listaInventario.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                view.mensaje("Producto encontrado: " + p.getNombre());
                view.mensaje("Ingrese los nuevos datos:");
                String nombre = view.pedirNombreProducto();
                double precio = view.pedirPrecio();
                int cantidad = view.pedirCantidad();
                String categoria = view.pedirCategoria();
                String fechaVencimiento = view.pedirFechaVencimiento();
                InventarioModel modificado = ProductoFactory.crearProducto(codigo, nombre, precio, cantidad, categoria, fechaVencimiento);
                listaInventario.actualizar(i, modificado);
                view.mensaje("Producto modificado correctamente.");
                return;
            }
        }
        view.error("Producto no encontrado.");
    }

    /**
     * Elimina un producto dado su código.
     */
    public void eliminarProducto() {
        view.mensaje("----------- Eliminar Producto -----------");
        String codigo = view.pedirCodigo();
        for (int i = 0; i < listaInventario.size(); i++) {
            if (listaInventario.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                listaInventario.eliminar(i);
                view.mensaje("Producto eliminado correctamente.");
                return;
            }
        }
        view.error("Producto no encontrado.");
    }

    /**
     * Lista todos los productos en inventario.
     */
    public void listarProductos() {
        view.mensaje("----------- Lista de Productos -----------");
        for (int i = 0; i < listaInventario.size(); i++) {
            InventarioModel p = listaInventario.get(i);
            view.mensaje((i + 1) + ". Código: " + p.getCodigo()
                    + ", Nombre: " + p.getNombre()
                    + ", Precio: " + p.getPrecio()
                    + ", Cantidad: " + p.getCantidad()
                    + ", Categoría: " + p.getCategoria()
                    + ", Fecha de Vencimiento: " + p.getFechaVencimiento());
        }
    }

    /**
     * Buscar producto por nombre.
     */
    public void buscarProductoByNombre() {
        String nombre = view.pedirNombreProducto();
        for (int i = 0; i < listaInventario.size(); i++) {
            if (listaInventario.get(i).getNombre().equalsIgnoreCase(nombre)) {
                mostrarDetallesProducto(listaInventario.get(i).getCodigo());
                return;
            }
        }
        view.error("Producto no encontrado.");
    }

    /**
     * Buscar producto por código.
     */
    public void buscarProductoByCodigo() {
        String codigo = view.pedirCodigo();
        for (int i = 0; i < listaInventario.size(); i++) {
            if (listaInventario.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                mostrarDetallesProducto(codigo);
                return;
            }
        }
        view.error("Producto no encontrado.");
    }

    /**
     * Muestra los detalles del producto con el código dado.
     *
     * @param codigo Código del producto a mostrar.
     */
    public void mostrarDetallesProducto(String codigo) {
        for (int i = 0; i < listaInventario.size(); i++) {
            InventarioModel p = listaInventario.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                view.mensaje("\n---------- Detalles del Producto ----------");
                view.mensaje("Código: " + p.getCodigo());
                view.mensaje("Nombre: " + p.getNombre());
                view.mensaje("Precio: " + p.getPrecio());
                view.mensaje("Cantidad: " + p.getCantidad());
                view.mensaje("Categoría: " + p.getCategoria());
                view.mensaje("Fecha de Vencimiento: " + p.getFechaVencimiento());
                return;
            }
        }
        view.error("Producto no encontrado.");
    }

    /**
     * Menú para buscar productos.
     */
    public void menuBuscar() {
        boolean salir = false;
        while (!salir) {
            view.menuBuscarProd();
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1 -> { buscarProductoByNombre(); salir = true; }
                case 2 -> { buscarProductoByCodigo(); salir = true; }
                case 3 -> salir = true;
                default -> view.error("Opción no válida");
            }
        }
    }

    /**
     * Busca cliente por nombre.
     */
    public void buscarClienteByNombre() {
        String nombre = view.pedirNombre();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                mostrarDetallesCliente(listaClientes.get(i).getId());
                return;
            }
        }
        view.error("Cliente no encontrado.");
    }

    /**
     * Busca cliente por ID.
     */
    public void buscarClienteById() {
        int id = view.pedirId();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == id) {
                mostrarDetallesCliente(id);
                return;
            }
        }
        view.error("Cliente no encontrado.");
    }

    /**
     * Muestra los detalles del cliente con el ID dado.
     *
     * @param id ID del cliente a mostrar.
     */
    public void mostrarDetallesCliente(int id) {
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteModel c = listaClientes.get(i);
            if (c.getId() == id) {
                view.mensaje("\n---------- Detalles del Cliente ----------");
                view.mensaje("ID: " + c.getId());
                view.mensaje("Nombre: " + c.getNombre());
                view.mensaje("Email: " + c.getEmail());
                view.mensaje("Teléfono: " + c.getTelefono());
                view.mensaje("Saldo: " + c.getSaldo());
                return;
            }
        }
        view.error("Cliente no encontrado.");
    }

    /**
     * Menú para buscar clientes.
     */
    public void menuBuscarClientes() {
        boolean salir = false;
        while (!salir) {
            view.menuBuscarCliente();
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1 -> { buscarClienteByNombre(); salir = true; }
                case 2 -> { buscarClienteById(); salir = true; }
                case 3 -> salir = true;
                default -> view.error("Opción no válida");
            }
        }
    }
    public void pruebaFactoryProducto() {
        view.mensaje("Probando creación de producto con precio inválido y fecha vacía...");

        InventarioModel producto = InventarioFactory.crearProducto("P001", "Producto de Prueba", -50.0, 10, "General","10/10/2025");

        view.mensaje("Producto creado:");
        view.mensaje("Nombre: " + producto.getNombre());
        view.mensaje("Precio: " + producto.getPrecio());
        view.mensaje("Fecha Vencimiento: " + producto.getFechaVencimiento());

        if (producto.getPrecio() == 1.0 && producto.getFechaVencimiento().equals("01/01/2099")) {
            view.mensaje("✅ Factory de productos aplicó las reglas correctamente.");
        } else {
            view.error("❌ El Factory de productos NO aplicó correctamente las reglas.");
        }
    }


}
