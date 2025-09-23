package tap;

/**
 * Clase principal para demostrar el patrón Strategy con descuentos.
 */
public class Main {
    public static void main(String[] args) {
        Producto camisa = new Producto("Camisa", 500);
        Producto pantalon = new Producto("Pantalón", 800);

        camisa.setEstrategia(new DescuentoFijo());
        pantalon.setEstrategia(new DescuentoTemporada());

        camisa.mostrarInfo();     // Camisa - Precio final: $450.0
        pantalon.mostrarInfo();   // Pantalón - Precio final: $600.0
    }
}