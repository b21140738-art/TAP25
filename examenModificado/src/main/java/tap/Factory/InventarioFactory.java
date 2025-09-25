package tap.Factory;


import tap.Model.InventarioModel;

/**
 * Fábrica para crear instancias de productos (InventarioModel),
 * aplicando reglas de negocio antes de la creación.
 */
public class InventarioFactory {

    /**
     * Crea un nuevo producto con validaciones previas.
     *
     * @param codigo            Código único del producto.
     * @param nombre            Nombre del producto.
     * @param precio            Precio del producto.
     * @param cantidad          Cantidad disponible del producto.
     * @param categoria         Categoría del producto.
     * @param fechaVencimiento  Fecha de vencimiento del producto.
     * @return Instancia de InventarioModel validada y creada.
     */
    public static InventarioModel VERIFICARPRECIO(String codigo, String nombre, double precio, double precioDescuento, double porcentajeDescuento, int cantidad, String categoria, String fechaVencimiento) {
        if (precio <= 0) {
            precio = 1.0; // Precio mínimo
        }

        if (fechaVencimiento == null || fechaVencimiento.trim().isEmpty()) {
            fechaVencimiento = "01/01/2029"; // Fecha por defecto si está vacía
        }

        return new InventarioModel(codigo, nombre, precio,precioDescuento, porcentajeDescuento, cantidad, categoria, fechaVencimiento);
    }
}
