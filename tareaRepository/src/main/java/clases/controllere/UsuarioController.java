package clases.controllere;

import clases.model.entidades.Usuario;
import clases.model.repository.UsuarioRepository;
import clases.model.servicios.ServicioNotificaciones;
import java.util.List;
import java.util.Optional;

public class UsuarioController {
    private UsuarioRepository usuarioRepository;
    private ServicioNotificaciones servicioNotificaciones;

    public UsuarioController(UsuarioRepository usuarioRepository, ServicioNotificaciones servicioNotificaciones) {
        this.usuarioRepository = usuarioRepository;
        this.servicioNotificaciones = servicioNotificaciones;
    }

    // Operaciones CRUD de usuarios
    public boolean crearUsuario(String id, String nombre, String email, String telefono) {
        if (usuarioRepository.existeUsuario(id)) {
            return false;
        }

        Usuario usuario = new Usuario(id, nombre, email, telefono);
        usuarioRepository.guardar(usuario);
        return true;
    }

    public Optional<Usuario> obtenerUsuario(String id) {
        return usuarioRepository.buscarPorId(id);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.obtenerTodos();
    }

    public boolean actualizarUsuario(String id, String nombre, String email, String telefono) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorId(id);
        if (!usuarioOpt.isPresent()) {
            return false;
        }

        // En una implementación real, se debería crear un nuevo usuario o tener setters para estos campos
        Usuario usuario = new Usuario(id, nombre, email, telefono);
        usuarioRepository.guardar(usuario);
        return true;
    }

    public boolean eliminarUsuario(String id) {
        return usuarioRepository.eliminar(id);
    }

    public boolean configurarNotificaciones(String id, boolean email, boolean sms, boolean push) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorId(id);
        if (!usuarioOpt.isPresent()) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setNotificacionesEmail(email);
        usuario.setNotificacionesSMS(sms);
        usuario.setNotificacionesPush(push);

        usuarioRepository.guardar(usuario);
        return true;
    }

    // Operaciones de notificaciones
    public boolean enviarNotificacion(String usuarioId, String mensaje) {
        return servicioNotificaciones.enviarNotificacion(usuarioId, mensaje);
    }

    public boolean enviarNotificacionUrgente(String usuarioId, String mensaje) {
        return servicioNotificaciones.enviarNotificacionUrgente(usuarioId, mensaje);
    }

    public boolean enviarNotificacionCifrada(String usuarioId, String mensaje) {
        return servicioNotificaciones.enviarNotificacionCifrada(usuarioId, mensaje);
    }

    public boolean enviarNotificacionGlobal(String mensaje) {
        return servicioNotificaciones.enviarNotificacionGlobal(mensaje);
    }

    public boolean existeUsuario(String id) {
        return usuarioRepository.existeUsuario(id);
    }
}