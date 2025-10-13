package ejemplo2.controller;

// RepositorioOrden.java
import java.util.*;

public class RepositorioOrden implements Repositorio<Orden, String> {
    private Map<String, Orden> ordenes = new HashMap<>();
    private static int contadorOrdenes = 1;

    @Override
    public void guardar(Orden orden) {
        if (orden.getId() == null) {
            orden.setId("ORD-" + contadorOrdenes++);
        }
        ordenes.put(orden.getId(), orden);
    }

    @Override
    public Orden buscarPorId(String id) {
        return ordenes.get(id);
    }

    @Override
    public List<Orden> buscarTodos() {
        return new ArrayList<>(ordenes.values());
    }

    @Override
    public void eliminar(String id) {
        ordenes.remove(id);
    }
}
