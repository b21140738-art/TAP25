package ejemplo2.controller;

// ControladorCafeteria.java
import ejemplo2.model.Bebida;
import ejemplo2.model.decoradorCondimento.DecoradorCondimento;

import java.util.List;

public class ControladorCafeteria {
    private RepositorioOrden repositorioOrden;
    private Orden ordenActual;

    public ControladorCafeteria() {
        this.repositorioOrden = new RepositorioOrden();
        this.ordenActual = new Orden();
    }

    // Métodos para manejar la orden actual
    public void iniciarNuevaOrden() {
        this.ordenActual = new Orden();
    }

    public void agregarBebidaAOrdenActual(Bebida bebida) {
        ordenActual.agregarBebida(bebida);
    }

    public void guardarOrdenActual() {
        if (!ordenActual.getBebidas().isEmpty()) {
            repositorioOrden.guardar(ordenActual);
        }
    }

    // Métodos genéricos para gestión de órdenes
    public <T extends Orden> List<T> obtenerTodasLasOrdenes() {
        return (List<T>) repositorioOrden.buscarTodos();
    }

    public Orden obtenerOrdenPorId(String id) {
        return repositorioOrden.buscarPorId(id);
    }

    public double calcularTotal(Orden orden) {
        return orden.getTotal();
    }

    // Factory method genérico para crear bebidas con decoradores
    public <T extends Bebida> T crearBebida(Class<T> claseBebida,
                                            List<Class<? extends DecoradorCondimento>> condimentos) {
        try {
            T bebida = claseBebida.getDeclaredConstructor().newInstance();

            for (Class<? extends DecoradorCondimento> condimento : condimentos) {
                bebida = (T) condimento.getDeclaredConstructor(Bebida.class)
                        .newInstance(bebida);
            }

            return bebida;
        } catch (Exception e) {
            throw new RuntimeException("Error creando bebida", e);
        }
    }

    // Getter para la orden actual
    public Orden getOrdenActual() {
        return ordenActual;
    }
}