package clases.model.servicios;


import clases.model.entidades.Usuario;
import clases.model.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioNotificaciones {
    private UsuarioRepository usuarioRepository;

    public ServicioNotificaciones(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean enviarNotificacion(String usuarioId, String mensaje) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorId(usuarioId);
        if (!usuarioOpt.isPresent()) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();
        List<Notificador> notificadores = crearNotificadores(usuario, false);

        for (Notificador notificador : notificadores) {
            notificador.enviar(mensaje);
        }

        return true;
    }

    public boolean enviarNotificacionUrgente(String usuarioId, String mensaje) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorId(usuarioId);
        if (!usuarioOpt.isPresent()) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();
        List<Notificador> notificadores = crearNotificadores(usuario, true);

        for (Notificador notificador : notificadores) {
            notificador.enviar(mensaje);
        }

        return true;
    }

    public boolean enviarNotificacionCifrada(String usuarioId, String mensaje) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorId(usuarioId);
        if (!usuarioOpt.isPresent()) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();
        List<Notificador> notificadores = crearNotificadoresCifradas(usuario);

        for (Notificador notificador : notificadores) {
            notificador.enviar(mensaje);
        }

        return true;
    }

    public boolean enviarNotificacionGlobal(String mensaje) {
        List<Usuario> usuarios = usuarioRepository.obtenerTodos();
        if (usuarios.isEmpty()) {
            return false;
        }

        for (Usuario usuario : usuarios) {
            List<Notificador> notificadores = crearNotificadores(usuario, false);
            for (Notificador notificador : notificadores) {
                notificador.enviar(mensaje);
            }
        }

        return true;
    }

    private List<Notificador> crearNotificadores(Usuario usuario, boolean esUrgente) {
        List<Notificador> notificadores = new ArrayList<>();

        if (usuario.isNotificacionesEmail()) {
            Notificador notificador = new NotificadorEmail(usuario.getEmail());
            notificador = new NotificadorLogDecorator(notificador);
            if (esUrgente) {
                notificador = new NotificadorUrgenteDecorator(notificador);
            }
            notificadores.add(notificador);
        }

        if (usuario.isNotificacionesSMS()) {
            Notificador notificador = new NotificadorSMS(usuario.getTelefono());
            notificador = new NotificadorLogDecorator(notificador);
            if (esUrgente) {
                notificador = new NotificadorUrgenteDecorator(notificador);
            }
            notificadores.add(notificador);
        }

        if (usuario.isNotificacionesPush()) {
            Notificador notificador = new NotificadorPush(usuario.getId());
            notificador = new NotificadorLogDecorator(notificador);
            if (esUrgente) {
                notificador = new NotificadorUrgenteDecorator(notificador);
            }
            notificadores.add(notificador);
        }

        return notificadores;
    }

    private List<Notificador> crearNotificadoresCifradas(Usuario usuario) {
        List<Notificador> notificadores = new ArrayList<>();

        if (usuario.isNotificacionesEmail()) {
            Notificador notificador = new NotificadorEmail(usuario.getEmail());
            notificador = new NotificadorCifradoDecorator(notificador);
            notificador = new NotificadorLogDecorator(notificador);
            notificadores.add(notificador);
        }

        if (usuario.isNotificacionesSMS()) {
            Notificador notificador = new NotificadorSMS(usuario.getTelefono());
            notificador = new NotificadorCifradoDecorator(notificador);
            notificador = new NotificadorLogDecorator(notificador);
            notificadores.add(notificador);
        }

        if (usuario.isNotificacionesPush()) {
            Notificador notificador = new NotificadorPush(usuario.getId());
            notificador = new NotificadorCifradoDecorator(notificador);
            notificador = new NotificadorLogDecorator(notificador);
            notificadores.add(notificador);
        }

        return notificadores;
    }
}