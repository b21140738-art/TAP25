package clases.model.repository;


import clases.model.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    void guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(String id);
    List<Usuario> obtenerTodos();
    boolean eliminar(String id);
    boolean existeUsuario(String id);
}