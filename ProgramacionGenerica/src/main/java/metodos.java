/*
Métodos Genéricos
Los métodos genéricos permiten definir parámetros de tipo sin hacer la clase
genérica
 */

public class metodos {
    public static void main(String[] args) {
        Utilidad.imprimir("Texto generico");
        Utilidad.imprimir(123);
        Utilidad.imprimir(3.1416);
    }
}
/// Metodos genericos, utilizan el parametro T para retornar cualquier
/// tipo de dato sin tener que hacer la clase generica

class Utilidad {
    public static <T> void imprimir(T elemento) {
        System.out.println(elemento);
    }
}
