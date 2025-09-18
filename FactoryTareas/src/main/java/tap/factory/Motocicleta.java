package tap.factory;

/**
 * Clase que representa una motocicleta con marca y cilindrada.
 */
class Motocicleta implements Vehiculo {
    private String marca;
    private int cilindrada;

    /**
     * Constructor de la motocicleta.
     * @param marca Marca de la motocicleta.
     * @param cilindrada Cilindrada en centímetros cúbicos.
     */
    public Motocicleta(String marca, int cilindrada) {
        this.marca = marca;
        this.cilindrada = cilindrada;
    }

    /** {@inheritDoc} */
    @Override
    public void acelerar() {
        System.out.println("La motocicleta " + marca + " está acelerando con " + cilindrada + "cc");
    }

    /** {@inheritDoc} */
    @Override
    public void frenar() {
        System.out.println("La motocicleta está frenando con precaución");
    }

    /** {@inheritDoc} */
    @Override
    public void mostrarInfo() {
        System.out.println("🏍️ Motocicleta: " + marca + " " + cilindrada + "cc - 2 ruedas, ágil y rápida");
    }
}
