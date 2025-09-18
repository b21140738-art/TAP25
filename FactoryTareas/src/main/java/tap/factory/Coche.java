package tap.factory;
/**
 * Clase que representa un coche con marca y modelo.
 */
class Coche implements Vehiculo {
    private String marca;
    private String modelo;

    /**
     * Constructor del coche.
     * @param marca Marca del coche.
     * @param modelo Modelo del coche.
     */
    public Coche(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    /** {@inheritDoc} */
    @Override
    public void acelerar() {
        System.out.println("El coche " + marca + " " + modelo + " está acelerando por la carretera");
    }

    /** {@inheritDoc} */
    @Override
    public void frenar() {
        System.out.println("El coche está frenando con frenos de disco");
    }

    /** {@inheritDoc} */
    @Override
    public void mostrarInfo() {
        System.out.println("🚗 Coche: " + marca + " " + modelo + " - 4 ruedas, motor a gasolina");
    }
}