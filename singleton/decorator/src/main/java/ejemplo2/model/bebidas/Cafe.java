package ejemplo2.model.bebidas;

import ejemplo2.model.BebidaAbstracta;

// Cafe.java
public class Cafe extends BebidaAbstracta {
    public Cafe() {
        descripcion = "Café negro";
    }

    @Override
    public double getCosto() {
        return 50;
    }
}
