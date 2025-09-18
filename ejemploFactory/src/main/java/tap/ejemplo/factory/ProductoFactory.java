package tap.ejemplo.factory;

import tap.ejemplo.model.Producto;
import tap.ejemplo.model.ProductoA;
import tap.ejemplo.model.ProductoB;

/**
 * Fábrica para crear instancias de productos según su tipo.
 */
public class ProductoFactory {

    /**
     * Crea un producto según el tipo especificado.
     * @param tipo tipo de producto ("A" o "B").
     * @return instancia de Producto correspondiente.
     * @throws IllegalArgumentException si el tipo es desconocido.
     */
    public static Producto crearProducto(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "A" -> new ProductoA();
            case "B" -> new ProductoB();
            default -> throw new IllegalArgumentException("Tipo de producto desconocido: " + tipo);
        };
    }
}