package tap.Implementación_del_Patron_Factory_en_Java;

public class Cliente {
    public static void main(String[] args) {
        VehiculoFactory factory = new VehiculoFactory();
// Crear un coche
        Vehiculo coche = factory.crearVehiculo("COCHE");
        coche.conducir();
// Crear una moto
        Vehiculo moto = factory.crearVehiculo("MOTO");
        moto.conducir();
// Crear un camión
        Vehiculo camion = factory.crearVehiculo("CAMION");
        camion.conducir();
    }
}
