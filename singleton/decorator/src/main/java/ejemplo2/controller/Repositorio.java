package ejemplo2.controller;

// Repository.java
import java.util.List;

public interface Repositorio<T, ID> {
    void guardar(T entity);
    T buscarPorId(ID id);
    List<T> buscarTodos();
    void eliminar(ID id);
}