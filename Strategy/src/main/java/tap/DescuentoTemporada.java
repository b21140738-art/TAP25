package tap;

//Descuento por temporada: 25%.
public class DescuentoTemporada implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double precioOriginal) {
        return precioOriginal * 0.75;
    }
}
