package tap.Model;

/**
 * Clase que representa un producto en el inventario.
 */
public class InventarioModel {
    private final String codigo;
    private final String nombre;
    private final double precio;
    private final double precioDescuento;
    private final double porcentajeDescuento;
    private final int cantidad;
    private final String categoria;
    private final String fechaVencimiento;
    public int totalProductos;

    /**
     * Constructor de la clase InventarioModel.
     *
     * @param codigo          Código del producto.
     * @param nombre          Nombre del producto.
     * @param precio          Precio del producto.
     * @param cantidad        Cantidad disponible del producto.
     * @param categoria       Categoría del producto.
     * @param fechaVencimiento Fecha de vencimiento del producto.
     * @param precioDescuento parametro para los descuentos de los productos
     */
    public InventarioModel(String codigo, String nombre, double precio, double precioDescuento, double porcentajeDescuento, int cantidad, String categoria, String fechaVencimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.precioDescuento = precioDescuento;
        this.porcentajeDescuento = porcentajeDescuento;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.totalProductos = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPrecioDescuento() { return  precioDescuento; }
public double getPorcentajeDescuento() {
        return porcentajeDescuento;
}
    public int getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

}
