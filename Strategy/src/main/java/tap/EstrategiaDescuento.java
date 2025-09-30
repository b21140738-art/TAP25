package tap;

/**
 * Interfaz que define el contrato para aplicar descuentos.
 */
public interface EstrategiaDescuento {
    /**
     * Aplica un descuento al precio original.
     */
    double aplicarDescuento(double precioOriginal);
}
