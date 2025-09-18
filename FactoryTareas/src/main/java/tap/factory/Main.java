package tap.factory;

public class Main {
    public static void main(String[] args) {
        CarFactory sedanFactory = new SedanFactory();
        Car sedan = sedanFactory.createCar();
        sedan.assemble();

        CarFactory suvFactory = new SUVFactory();
        Car suv = suvFactory.createCar();
        suv.assemble();
    }
}


class Sedan implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling a sedan car.");
    }
}

class SUV implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling an SUV car.");
    }
}


class SedanFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Sedan();
    }
}

class SUVFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new SUV();
    }
}

