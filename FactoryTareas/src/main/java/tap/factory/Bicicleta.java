package tap.factory;

/**
 * Clase que representa una bicicleta con tipo y número de velocidades.
 */

class Bicicleta implements Vehiculo {
    private String tipo;
    private int numVelocidades;

    /**
     * Constructor de la bicicleta.
     * @param tipo Tipo de bicicleta (urbana, montaña, etc.).
     * @param numVelocidades Número de velocidades disponibles.
     */
    public Bicicleta(String tipo, int numVelocidades) {
        this.tipo = tipo;
        this.numVelocidades = numVelocidades;
    }

/** {@inheritDoc}
