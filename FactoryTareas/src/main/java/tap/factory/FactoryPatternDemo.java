package tap.factory;

/**
 * Clase principal que demuestra el uso del patrón Factory con vehículos.
 */
public class FactoryPatternDemo {

    /**
     * Método principal que ejecuta la demostración.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN FACTORY ===\n");

        Vehiculo coche1 = VehiculoFactory.crearVehiculo(TipoVehiculo.COCHE, "Honda", "Civic");
        Vehiculo moto1 = VehiculoFactory.crearVehiculo(TipoVehiculo.MOTOCICLETA, "Kawasaki", "600");
        Vehiculo camion1 = VehiculoFactory.crearVehiculo(TipoVehiculo.CAMION, "Mercedes", "12.5");

        Vehiculo coche2 = VehiculoFactory.crearVehiculoPorDefecto(TipoVehiculo.COCHE);
        Vehiculo moto2 = VehiculoFactory.crearVehiculoPorDefecto(TipoVehiculo.MOTOCICLETA);

        Vehiculo[] vehiculos = {coche1, moto1, camion1, coche2, moto2};

        System.out.println("1. INFORMACIÓN DE VEHÍCULOS:");
        System.out.println("----------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInfo();
        }

        System.out.println("\n2. ACELERANDO TODOS LOS VEHÍCULOS:");
        System.out.println("----------------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.acelerar();
        }

        System.out.println("\n3. FRENANDO TODOS LOS VEHÍCULOS:");
        System.out.println("--------------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.frenar();
        }

        System.out.println("\n4. CREACIÓN DINÁMICA BASADA EN INPUT:");
        System.out.println("-------------------------------------");

        String[] tiposInput = {"COCHE", "MOTOCICLETA", "CAMION"};

        for (String tipoStr : tiposInput) {
            try {
                TipoVehiculo tipo = TipoVehiculo.valueOf(tipoStr);
                Vehiculo vehiculo = VehiculoFactory.crearVehiculoPorDefecto(tipo);
                System.out.print("Creado dinámicamente: ");
                vehiculo.mostrarInfo();
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de vehículo no válido: " + tipoStr);
            }
        }

        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");
    }
}