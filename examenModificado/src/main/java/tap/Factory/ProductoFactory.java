package tap.Factory;


/**
 * clase factory para la creación de instancias de InventarioModel.
 */
public class ProductoFactory {

    /**
     * Crea un nuevo producto con los parámetros indicados.
     *
     * @param codigo            Código del producto.
     * @param nombre            Nombre del producto.
     * @param precio            Precio del producto.
     * @param cantidad          Cantidad disponible.
     * @param categoria         Categoría del producto.
     * @param fechaVencimiento  Fecha de vencimiento del producto.
     * @return Una nueva instancia de InventarioModel.
     */
    public static tap.Model.InventarioModel crearProducto(String codigo, String nombre, double precio, int cantidad, String categoria, String fechaVencimiento) {
        return new tap.Model.InventarioModel(codigo, nombre, precio, cantidad, categoria, fechaVencimiento);
    }
}
