package tap.factory2;
/**
 * Interfaz que define el contrato para fábricas de automóviles.
 */
interface CarFactory {
    /**
     * Crea una instancia de un automóvil.
     * @return un objeto que implementa la interfaz Car.
     */
    Car createCar();
}

/**
 * Interfaz que representa un automóvil genérico.
 */
interface Car {
    /**
     * Ensambla el automóvil.
     * Este método define el comportamiento específico de ensamblaje.
     */
    void assemble();
}

/**
 * Clase concreta que representa un automóvil tipo Sedan.
 */
class Sedan implements Car {
    /**
     * {@inheritDoc}
     */
    @Override
    public void assemble() {
        System.out.println("Assembling a sedan car.");
    }
}

/**
 * Clase concreta que representa un automóvil tipo SUV.
 */
class SUV implements Car {
    /**
     * {@inheritDoc}
     */
    @Override
    public void assemble() {
        System.out.println("Assembling an SUV car.");
    }
}

/**
 * Fábrica concreta para crear automóviles tipo Sedan.
 */
class SedanFactory implements CarFactory {
    /**
     * Crea una instancia de Sedan.
     * @return un objeto Sedan.
     */
    @Override
    public Car createCar() {
        return new Sedan();
    }
}

/**
 * Fábrica concreta para crear automóviles tipo SUV.
 */
class SUVFactory implements CarFactory {
    /**
     * Crea una instancia de SUV.
     * @return un objeto SUV.
     */
    @Override
    public Car createCar() {
        return new SUV();
    }
}

/**
 * Clase principal que ejecuta el programa de ensamblaje de automóviles.
 */
public class Main {
    /**
     * Método principal que demuestra el uso del patrón Factory.
     * @param args argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        CarFactory sedanFactory = new SedanFactory();
        Car sedan = sedanFactory.createCar();
        sedan.assemble();

        CarFactory suvFactory = new SUVFactory();
        Car suv = suvFactory.createCar();
        suv.assemble();
    }
}