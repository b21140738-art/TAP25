package ejemplo2.model.decoradorCondimento;

import ejemplo2.model.Bebida;

// Azucar.java
public class Azucar extends DecoradorCondimento {
    public Azucar(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescripcion() {
        return bebida.getDescripcion() + ", Azúcar";
    }

    @Override
    public double getCosto() {
        return bebida.getCosto() + 5;
    }
}
