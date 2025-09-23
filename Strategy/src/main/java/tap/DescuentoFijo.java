package tap;

/**
 * Descuento fijo del 10%.
 */
public class DescuentoFijo implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double precioOriginal) {
        return precioOriginal * 0.90;
    }
}

