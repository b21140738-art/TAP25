package tap.ejemplo.model;

/**
 * Implementación de Producto tipo A (por ejemplo, electrónico).
 */
public class ProductoA implements Producto {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescripcion() {
        return "Producto A: Electrónico";
    }
}
