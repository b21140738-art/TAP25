/*
las interfaces genericas funcionan igual que las clases
 */

public class InterfazGenerica {

    public static void main(String[] args) {
        Suma suma = new  Suma();
        System.out.println(suma.ejecutar(10, 5));
    }
}
/// Interfaz generica
interface Operacion<T>{
    T ejecutar (T a, T b);
}
/// implementacion de la interfaz con enteros Integer
/// es una interfaz generica que se implementa con un tipo especifico de datos
class Suma implements Operacion<Integer>{
    public Integer ejecutar(Integer a, Integer b){
        return a + b;
    }
}