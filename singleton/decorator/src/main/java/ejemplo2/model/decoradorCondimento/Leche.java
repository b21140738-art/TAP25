package ejemplo2.model.decoradorCondimento;

import ejemplo2.model.Bebida;

// Leche.java
public class Leche extends DecoradorCondimento {
    public Leche(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescripcion() {
        return bebida.getDescripcion() + ", Leche";
    }

    @Override
    public double getCosto() {
        return bebida.getCosto() + 5;
    }
}
