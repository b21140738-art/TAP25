package clases.practica_login.model.dao;


import clases.practica_login.model.Usuario;
import java.util.Optional;

public interface IUsuarioDAO {
    Optional<Usuario> buscarPorUsername(String username);
    Optional<Usuario> buscarPorCorreo(String correo);
    boolean insertarUsuario(Usuario usuario);
    boolean existeUsername(String username);
    boolean existeCorreo(String correo);
}