package tap.Implementación_del_Patron_Factory_en_Java;

public class VehiculoFactory {
    public Vehiculo crearVehiculo(String tipo) {
        if (tipo == null) {
            return null;
        }
        if (tipo.equalsIgnoreCase("COCHE")) {
            return new Coche();
        } else if (tipo.equalsIgnoreCase("MOTO")) {
            return new Moto();
        } else if (tipo.equalsIgnoreCase("CAMION")) {
            return new Camion();
        }
        return null;
    }
}
