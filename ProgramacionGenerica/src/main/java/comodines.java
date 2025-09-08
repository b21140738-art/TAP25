/*
Los comodines permiten flexivilidad en metodos
que aceptan diferentes tipos de genericos

 */
import java.util.List;

public class comodines {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        List<String> palabras = List.of("java","genericos");

        Util.imprimirLista(numeros);
        Util.imprimirLista(palabras);
    }
}
//List<?> acepta cualquier tipo de lista, evita el uso de conversación de tipos
class Util{
    public static void imprimirLista(List<?> lista){
        for(Object elemento : lista){
            System.out.println(elemento);
        }
    }
}