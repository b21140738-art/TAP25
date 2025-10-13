package ejemplo2.model.decoradorCondimento;

import ejemplo2.model.Bebida;

// Caramelo.java
public class Caramelo extends DecoradorCondimento {
    public Caramelo(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescripcion() {
        return bebida.getDescripcion() + ", Caramelo";
    }

    @Override
    public double getCosto() {
        return bebida.getCosto() + 10;
    }
}
