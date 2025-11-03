package clases.practicaequipo5.controller;

import clases.practicaequipo5.model.ClienteModel;
import clases.practicaequipo5.model.Mensaje;
import clases.practicaequipo5.view.ClienteView;
import javafx.application.Platform;

public class ClienteController {
    private ClienteView vista;
    private ClienteModel modelo;

    public ClienteController(ClienteView vista, ClienteModel modelo) {
        if (vista == null) {
            throw new IllegalArgumentException("La vista no puede ser nula");
        }
        if (modelo == null) {
            throw new IllegalArgumentException("El modelo no puede ser nulo");
        }

        this.vista = vista;
        this.modelo = modelo;
        configurarEventos();
    }

    private void configurarEventos() {
        // Eventos de conexión
        vista.getBotonConectar().setOnAction(e -> conectar());
        vista.getBotonDesconectar().setOnAction(e -> desconectar());

        // Eventos de envío de mensajes
        vista.getBotonEnviar().setOnAction(e -> enviarMensaje());
        vista.getCampoMensaje().setOnAction(e -> enviarMensaje());

        // Evento de historial
        vista.getBotonHistorial().setOnAction(e -> solicitarHistorial());

        // Eventos de cambio de modo
        vista.getGrupoModo().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String nuevoModo = vista.getModoSeleccionado();
                modelo.cambiarModo(nuevoModo);
                vista.agregarMensaje("SISTEMA: Modo cambiado a " + nuevoModo);

                // Actualizar el placeholder según el modo
                switch (nuevoModo) {
                    case "MAYUS":
                        vista.getCampoMensaje().setPromptText("Escribe tu mensaje... (se convertirá a MAYÚSCULAS)");
                        break;
                    case "MINUS":
                        vista.getCampoMensaje().setPromptText("Escribe tu mensaje... (se convertirá a minúsculas)");
                        break;
                    case "NORMAL":
                    default:
                        vista.getCampoMensaje().setPromptText("Escribe tu mensaje aquí... (texto normal)");
                        break;
                }
            }
        });

        // Configurar callback para mensajes recibidos
        modelo.setOnMensajeRecibido(this::mostrarMensajeRecibido);
    }

    private void conectar() {
        if (vista.getCampoHost() == null) {
            System.err.println("Error: campoHost es null");
            return;
        }

        String hostInput = vista.getCampoHost().getText();
        String host = hostInput.isEmpty() ? "localhost" : hostInput;

        boolean exito = modelo.conectar(host, 9090);
        if (exito) {
            Platform.runLater(() -> {
                vista.setEstadoConectado(true);
                vista.agregarMensaje("SISTEMA: Conectado al servidor " + host + ":9090");
                vista.agregarMensaje("SISTEMA: Selecciona un modo de escritura (Normal, MAYÚSCULAS o minúsculas)");
                vista.agregarMensaje("SISTEMA: Usa el botón 'Ver Historial' para ver todos los mensajes anteriores");
            });
        } else {
            Platform.runLater(() -> {
                vista.agregarMensaje("SISTEMA: Error al conectar al servidor");
            });
        }
    }

    private void desconectar() {
        modelo.desconectar();
        Platform.runLater(() -> {
            vista.setEstadoConectado(false);
            vista.agregarMensaje("SISTEMA: Desconectado del servidor");
        });
    }

    private void enviarMensaje() {
        if (vista.getCampoMensaje() == null) return;

        String mensaje = vista.getCampoMensaje().getText().trim();
        if (!mensaje.isEmpty() && modelo.isConectado()) {
            modelo.enviarMensaje(mensaje);
            vista.limpiarCampoMensaje();
        }
    }

    private void solicitarHistorial() {
        if (modelo.isConectado()) {
            // Enviar comando especial al servidor para solicitar historial
            modelo.solicitarHistorial();
            vista.agregarMensaje("SISTEMA: Solicitando historial de mensajes...");
        }
    }

    private void mostrarMensajeRecibido(Mensaje mensaje) {
        Platform.runLater(() -> {
            vista.agregarMensaje(mensaje.toString());
        });
    }
}