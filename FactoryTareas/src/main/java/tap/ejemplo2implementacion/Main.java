package tap.ejemplo2implementacion;

public class Main {
    public static void main(String[] args) {
        Figura figura1 = FiguraFactory.getFigura("Circulo");
        figura1.dibujar(); //salida:dibujando un circulo

        Figura figura2 = FiguraFactory.getFigura("Rectangulo");
        figura2.dibujar(); //salida: dibujando un cuadrado
    }
}
