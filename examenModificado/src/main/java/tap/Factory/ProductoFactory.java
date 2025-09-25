package tap.Factory;


import tap.Model.InventarioModel;

/**
 * clase factory para la creación de instancias de InventarioModel.
 */
public class ProductoFactory {


    public static tap.Model.InventarioModel crearProducto(String codigo, String nombre, double precio, int cantidad, String categoria, String fechaVencimiento) {


        double porcentajeDescuento = obtenerPorcentajeDescuento(categoria);
        double precioDescuento = precio * (1 - porcentajeDescuento / 100);

        return new InventarioModel(codigo, nombre, precio, precioDescuento, porcentajeDescuento,cantidad, categoria, fechaVencimiento);
    }

    private static double obtenerPorcentajeDescuento(String categoria) {
       if ("ALIMENTOS".equals(categoria)) {
            return 20.0;
        } else if ("ROPA".equals(categoria)) {
            return 25.0;
        } else if ("ELECTRODOMESTICOS".equals(categoria)) {
            return 5.0;
        }
        return 0;


    }
}

