package ejemplo2.model.bebidas;

import ejemplo2.model.BebidaAbstracta;

// Expreso.java
public class Expreso extends BebidaAbstracta {
    public Expreso() {
        descripcion = "Expreso";
    }

    @Override
    public double getCosto() {
        return 40;
    }
}
