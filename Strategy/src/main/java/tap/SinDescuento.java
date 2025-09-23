package tap;

/**
 * Sin descuento aplicado.
 */
public class SinDescuento implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double precioOriginal) {
        return precioOriginal;
    }
}
