package tap.factory;

/**
 * Fábrica para crear instancias de vehículos según su tipo.
 */
class VehiculoFactory {

    /**
     * Crea un vehículo del tipo especificado con parámetros personalizados.
     * @param tipo Tipo de vehículo.
     * @param parametros Parámetros como marca, modelo, cilindrada o capacidad.
     * @return Instancia de Vehiculo correspondiente.
     */
    public static Vehiculo crearVehiculo(TipoVehiculo tipo, String... parametros) {
        switch (tipo) {
            case COCHE:
                if (parametros.length >= 2) {
                    return new Coche(parametros[0], parametros[1]);
                } else {
                    return new Coche("Toyota", "Corolla");
                }

            case MOTOCICLETA:
                if (parametros.length >= 2) {
                    try {
                        int cilindrada = Integer.parseInt(parametros[1]);
                        return new Motocicleta(parametros[0], cilindrada);
                    } catch (NumberFormatException e) {
                        return new Motocicleta(parametros[0], 250);
                    }
                } else {
                    return new Motocicleta("Yamaha", 250);
                }

            case CAMION:
                if (parametros.length >= 2) {
                    try {
                        double capacidad = Double.parseDouble(parametros[1]);
                        return new Camion(parametros[0], capacidad);
                    } catch (NumberFormatException e) {
                        return new Camion(parametros[0], 10.0);
                    }
                } else {
                    return new Camion("Volvo", 15.0);
                }

            default:
                throw new IllegalArgumentException("Tipo de vehículo no soportado: " + tipo);
        }
    }

    /**
     * Crea un vehículo del tipo especificado con valores por defecto.
     * @param tipo Tipo de vehículo.
     * @return Instancia de Vehiculo con valores predeterminados.
     */
    public static Vehiculo crearVehiculoPorDefecto(TipoVehiculo tipo) {
        return crearVehiculo(tipo);
    }
}