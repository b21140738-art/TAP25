package tap.ejemplo.model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGenerico<T> {
    private final List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }
    public List<T> obtenerTodos() {

        return elementos;
    }
}
