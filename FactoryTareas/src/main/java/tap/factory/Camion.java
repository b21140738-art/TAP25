package tap.factory;

/**
 * Clase que representa un camión con marca y capacidad de carga.
 */
class Camion implements Vehiculo {
    private String marca;
    private double capacidadCarga;

    /**
     * Constructor del camión.
     * @param marca Marca del camión.
     * @param capacidadCarga Capacidad de carga en toneladas.
     */
    public Camion(String marca, double capacidadCarga) {
        this.marca = marca;
        this.capacidadCarga = capacidadCarga;
    }

    /** {@inheritDoc} */
    @Override
    public void acelerar() {
        System.out.println("El camión " + marca + " está acelerando lentamente con " + capacidadCarga + " toneladas");
    }

    /** {@inheritDoc} */
    @Override
    public void frenar() {
        System.out.println("El camión está frenando con sistema de frenos neumático");
    }

    /** {@inheritDoc} */
    @Override
    public void mostrarInfo() {
        System.out.println("🚛 Camión: " + marca + " - Capacidad: " + capacidadCarga + " toneladas");
    }
}
