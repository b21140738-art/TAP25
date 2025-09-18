package tap.ejemplo.controller;

import tap.ejemplo.factory.ProductoFactory;
import tap.ejemplo.model.Producto;
import tap.ejemplo.model.RepositorioGenerico;
import tap.ejemplo.view.ProductoView;

/**
 * Controlador que gestiona la lógica entre vista y modelo.
 */
public class ProductoController {
    private final RepositorioGenerico<Producto> repositorio = new RepositorioGenerico<>();
    private final ProductoView vista = new ProductoView();

    /**
     * Agrega un producto al repositorio usando la fábrica.
     * @param tipo tipo de producto ("A" o "B").
     */
    public void agregarProducto(String tipo) {
        try {
            Producto producto = ProductoFactory.crearProducto(tipo);
            repositorio.agregar(producto);
            vista.mostrarMensaje("Producto agregado: " + producto.getDescripcion());
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    /**
     * Muestra todos los productos almacenados.
     */
    public void mostrarProductos() {
        vista.mostrarLista(repositorio.obtenerTodos());
    }
}
