package clases.practica_login.service;

import clases.practica_login.model.Usuario;
import clases.practica_login.model.dao.UsuarioDAO;

import java.util.Optional;

public class autentificar {
    private UsuarioDAO usuarioDAO;
    private password passwordService;

    public autentificar() {
        this.usuarioDAO = new UsuarioDAO();
        this.passwordService = new password();
    }

    public boolean registrarUsuario(Usuario usuario, String password) {
        if (!passwordService.isValidPassword(password)) {
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad");
        }

        usuario.setPasswordHash(passwordService.hashPassword(password));
        return usuarioDAO.insertarUsuario(usuario);
    }

    public Optional<Usuario> login(String usernameOrEmail, String password) {
        Optional<Usuario> usuarioOpt;

        // Verificar si es email o username
        if (usernameOrEmail.contains("@")) {
            usuarioOpt = usuarioDAO.buscarPorCorreo(usernameOrEmail);
        } else {
            usuarioOpt = usuarioDAO.buscarPorUsername(usernameOrEmail);
        }

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordService.verifyPassword(password, usuario.getPasswordHash())) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public boolean existeUsername(String username) {
        return usuarioDAO.existeUsername(username);
    }

    public boolean existeCorreo(String correo) {
        return usuarioDAO.existeCorreo(correo);
    }
}

