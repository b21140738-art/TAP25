/*clase generica
se defince usando <> para definir un tipo de dato generico
Se usa un parametro en este caso T para poder sustituirlo a lo largo del codigo
se puede usar cualquier tipo de dato al instanciar

@autor CLAUDIA PIZANO
 */
/// Clase generica que utiliza el parametro de tipo T
class caja<T> {
    private T contenido;

    public caja(T contenido) {
        this.contenido = contenido;
    }

    public T getcontenido() {
        return contenido;
    }

    public void setcontenido(T contenido) {
        this.contenido = contenido;
    }
}


/// Uso de la clase generica
public class Main{
    public static void main(String[] args) {
        caja<String> cajaTexto = new caja<>("Hey world");
        System.out.println(cajaTexto.getcontenido());

        caja<Integer> cajaNumero = new caja<>(42);
        System.out.println(cajaNumero.getcontenido());
    }
}
