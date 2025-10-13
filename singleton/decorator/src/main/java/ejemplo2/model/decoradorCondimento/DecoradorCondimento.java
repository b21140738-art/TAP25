package ejemplo2.model.decoradorCondimento;

import ejemplo2.model.Bebida;

// DecoradorCondimento.java - Clase abstracta para decoradores
public abstract class DecoradorCondimento implements Bebida {
    protected Bebida bebida;

    public DecoradorCondimento(Bebida bebida) {
        this.bebida = bebida;
    }

    @Override
    public abstract String getDescripcion();

    @Override
    public abstract double getCosto();
}

