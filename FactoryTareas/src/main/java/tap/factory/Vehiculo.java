package tap.factory;

/**
 * Interfaz que define el comportamiento común de todos los vehículos.
 */
interface Vehiculo {
    /**
     * Método para acelerar el vehículo.
     */
    void acelerar();

    /**
     * Método para frenar el vehículo.
     */
    void frenar();

    /**
     * Muestra la información detallada del vehículo.
     */
    void mostrarInfo();
}
