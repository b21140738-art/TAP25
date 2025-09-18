package tap.ejemplo.view;

import tap.ejemplo.model.Producto;
import java.util.List;

/**
 * Vista encargada de mostrar mensajes y listas al usuario.
 */
public class ProductoView{

    /**
     * Muestra un mensaje en consola.
     * @param mensaje texto a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra una lista de productos en consola.
     * @param productos lista de productos a mostrar.
     */
    public void mostrarLista(List<Producto> productos) {
        System.out.println("📦 Lista de productos:");
        for (Producto p : productos) {
            System.out.println("- " + p.getDescripcion());
        }
    }
}