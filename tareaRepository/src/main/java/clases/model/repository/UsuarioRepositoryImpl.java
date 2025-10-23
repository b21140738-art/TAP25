package clases.model.repository;


import clases.model.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void guardar(Usuario usuario) {
        eliminar(usuario.getId());
        usuarios.add(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public boolean eliminar(String id) {
        return usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    @Override
    public boolean existeUsuario(String id) {
        return usuarios.stream().anyMatch(usuario -> usuario.getId().equals(id));
    }
}