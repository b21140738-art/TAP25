package ejemplo2.model.decoradorCondimento;

import ejemplo2.model.Bebida;

// CremaBatida.java
public class CremaBatida extends DecoradorCondimento {
    public CremaBatida(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescripcion() {
        return bebida.getDescripcion() + ", Crema batida";
    }

    @Override
    public double getCosto() {
        return bebida.getCosto() + 15;
    }
}
