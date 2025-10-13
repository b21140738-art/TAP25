package ejemplo2.model;

// BebidaAbstracta.java - Clase abstracta base
public abstract class BebidaAbstracta implements Bebida {
    protected String descripcion = "Bebida desconocida";

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public abstract double getCosto();
}
