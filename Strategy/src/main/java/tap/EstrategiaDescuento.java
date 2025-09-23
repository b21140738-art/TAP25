package tap;

/**
 * Interfaz que define el contrato para aplicar descuentos.
 */
public interface EstrategiaDescuento {
    /**
     * Aplica un descuento al precio original.
     * @param precioOriginal Precio sin descuento.
     * @return Precio final con descuento aplicado.
     */
    double aplicarDescuento(double precioOriginal);
}
