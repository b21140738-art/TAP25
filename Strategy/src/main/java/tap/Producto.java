package tap;

/**
 * Clase que representa un producto con precio y estrategia de descuento.
 */
public class Producto {
    private String nombre;
    private double precio;
    private EstrategiaDescuento estrategia;

    /**
     * Constructor del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio base del producto.
     */
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.estrategia = new SinDescuento(); // Por defecto
    }

    /**
     * Establece la estrategia de descuento.
     * @param estrategia Estrategia a aplicar.
     */
    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Calcula el precio final con descuento.
     * @return Precio con descuento aplicado.
     */
    public double getPrecioFinal() {
        return estrategia.aplicarDescuento(precio);
    }

    /**
     * Muestra información del producto.
     */
    public void mostrarInfo() {
        System.out.println(nombre + " - Precio final: $" + getPrecioFinal());
    }
}
