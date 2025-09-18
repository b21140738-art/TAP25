package tap.Implementación_del_Patron_Factory_en_Java;

public class Coche implements Vehiculo {
    @Override
    public void conducir() {
        System.out.println("Conduciendo un coche...");
    }
}
public class Moto implements Vehiculo {
    @Override
    public void conducir() {
        System.out.println("Conduciendo una moto...");
    }
}
public class Camion implements Vehiculo {
    @Override

    public void conducir() {
        System.out.println("Conduciendo un camión...");
    }
}
