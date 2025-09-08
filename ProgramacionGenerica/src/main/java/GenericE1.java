/*
Métodos Genéricos
Los métodos genéricos permiten definir parámetros de tipo sin hacer la clase
genérica
 */

public class GenericE1 {
    public static void main(String[] args) {
        Par<String, Integer> edadPersona = new Par<>("Carlos", 25);
        System.out.println(edadPersona.getClave() + " tiene " + edadPersona.getValor() + " años");
    }
}

class Par<K, V> {
    private K clave;
    private V valor;
/// Metodos genericos, utilizan el parametro T para retornar cualquier
/// tipo de dato sin tener que hacer la clase generica
    public Par(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }
    public K getClave() {
        return clave;
    }
    public V getValor() {
        return valor;
    }
}