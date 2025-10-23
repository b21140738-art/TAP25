package clases.practicaequipo5.controller;


import clases.practicaequipo5.model.ServidorModel;
import clases.practicaequipo5.view.ServidorView;
import javafx.application.Platform;

public class ServidorController {
    private ServidorView vista;
    private ServidorModel modelo;

    public ServidorController(ServidorView vista, ServidorModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        configurarEventos();
    }

    private void configurarEventos() {
        // Evento para iniciar servidor
        vista.getBotonIniciar().setOnAction(e -> iniciarServidor());

        // Evento para detener servidor
        vista.getBotonDetener().setOnAction(e -> detenerServidor());

        // Configurar callback para logs del servidor
        modelo.setOnLogCallback(this::agregarLog);
    }

    private void iniciarServidor() {
        new Thread(() -> {
            modelo.iniciarServidor();
        }).start();

        Platform.runLater(() -> {
            vista.setEstadoServidor(true);
            vista.agregarLog("Servidor iniciado en puerto 9090");
        });
    }

    private void detenerServidor() {
        modelo.detenerServidor();
        Platform.runLater(() -> {
            vista.setEstadoServidor(false);
            vista.agregarLog("Servidor detenido");
        });
    }

    private void agregarLog(String mensaje) {
        Platform.runLater(() -> {
            vista.agregarLog(mensaje);
        });
    }
}