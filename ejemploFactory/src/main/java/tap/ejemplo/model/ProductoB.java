package tap.ejemplo.model;

/**
 * Implementación de Producto tipo B (por ejemplo, alimento).
 */
public class ProductoB implements Producto {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescripcion() {
        return "Producto B: Alimento";
    }
}