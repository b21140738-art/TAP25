package clases.practicaequipo5.model.decorador;

import clases.practicaequipo5.model.Mensaje;
import clases.practicaequipo5.controller.dao.MensajeDao;
import java.net.Socket;
import java.util.List;

public class ServidorDecorator {
    private final MensajeDao mensajeDAO;

    public ServidorDecorator() {
        this.mensajeDAO = new MensajeDao();
    }

    public void guardarMensajeCliente(Mensaje mensaje, Socket clienteSocket) {
        String ipCliente = clienteSocket.getInetAddress().getHostAddress();
        mensajeDAO.guardarMensaje(mensaje, ipCliente);
    }

    public void guardarMensajeServidor(Mensaje mensaje, String ipCliente) {
        mensajeDAO.guardarMensaje(mensaje, ipCliente);
    }

    public List<Mensaje> obtenerHistorialCompleto() {
        return mensajeDAO.obtenerTodosLosMensajes();
    }

    public List<Mensaje> obtenerHistorialPorCliente(String ipCliente) {
        return mensajeDAO.obtenerMensajesPorIp(ipCliente);
    }

    public String formatearHistorial(List<Mensaje> mensajes) {
        if (mensajes.isEmpty()) {
            return "No hay mensajes en el historial.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== HISTORIAL DE MENSAJES (MySQL) ===\n");

        for (Mensaje mensaje : mensajes) {
            sb.append(String.format("[%s] %s: %s\n",
                    mensaje.getModo(),
                    mensaje.getTipo(),
                    mensaje.getContenido()));
        }

        sb.append("=== FIN DEL HISTORIAL ===");
        return sb.toString();
    }
}