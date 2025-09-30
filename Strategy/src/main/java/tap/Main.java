package tap;

/**
 * Clase principal para demostrar el patrón Strategy con descuentos.
 */
public class Main {
    public static void main(String[] args) {
        Producto camisa = new Producto("Camisa", 500);
        Producto pantalon = new Producto("Pantalón", 800);
        Producto Laptop = new Producto("Laptop", 12000);

        camisa.setEstrategia(new DescuentoFijo());
        pantalon.setEstrategia(new DescuentoTemporada());
        Laptop.setEstrategia(new SinDescuento());

        camisa.mostrarInfo();     // Camisa - Precio final: $450.0
        pantalon.mostrarInfo();   // Pantalón - Precio final: $600.0
        Laptop.mostrarInfo(); //Lap - Precio final: $12,000.00
    }
}