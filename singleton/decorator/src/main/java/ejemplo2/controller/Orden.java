package ejemplo2.controller;

// Orden.java
import ejemplo2.model.Bebida;

import java.util.ArrayList;
import java.util.List;

public class Orden {
    private String id;
    private List<Bebida> bebidas;
    private double total;
    private String estado;

    public Orden() {
        this.bebidas = new ArrayList<>();
        this.estado = "Pendiente";
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<Bebida> getBebidas() { return bebidas; }

    public double getTotal() {
        total = bebidas.stream()
                .mapToDouble(Bebida::getCosto)
                .sum();
        return total;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public void agregarBebida(Bebida bebida) {
        bebidas.add(bebida);
    }

    public String getDetallesOrden() {
        StringBuilder detalles = new StringBuilder();
        detalles.append("Orden ID: ").append(id).append("\n");
        for (Bebida bebida : bebidas) {
            detalles.append("- ").append(bebida.getDescripcion())
                    .append(": $").append(String.format("%.2f", bebida.getCosto()))
                    .append("\n");
        }
        detalles.append("Total: $").append(String.format("%.2f", getTotal()));
        return detalles.toString();
    }
}