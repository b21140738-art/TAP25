package ejemplo2.model.bebidas;

import ejemplo2.model.BebidaAbstracta;

// Te.java
public class Te extends BebidaAbstracta {
    public Te() {
        descripcion = "Té";
    }

    @Override
    public double getCosto() {
        return 55;
    }
}