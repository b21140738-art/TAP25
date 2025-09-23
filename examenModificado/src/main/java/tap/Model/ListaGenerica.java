package tap.Model;
import java.util.ArrayList;
import java.util.List;

public class ListaGenerica<T> {
    private List<T> items = new ArrayList<>();

    public void agregar(T item) {
        items.add(item);
    }

    public void actualizar(int index, T item) {
        items.set(index, item);
    }

    public void eliminar(int index) {
        items.remove(index);
    }

    public T get(int index) {
        return items.get(index);
    }

    public List<T> getAll() {
        return items;
    }

    public int size() {
        return items.size();
    }
}

