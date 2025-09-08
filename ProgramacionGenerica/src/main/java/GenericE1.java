/// • Par<K, V> es una clase genérica con dos parámetros de tipo.
/// • Se usa con String e Integer.
/// multiples tipos genericos

public class GenericE1 {
    public static void main(String[] args) {
        Par<String, Integer> edadPersona = new Par<>("Carlos", 25);
        System.out.println(edadPersona.getClave() + " tiene " + edadPersona.getValor() + " años");
    }
}

class Par<K, V> {
    private K clave;
    private V valor;

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