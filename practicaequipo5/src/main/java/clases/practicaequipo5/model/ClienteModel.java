package clases.practicaequipo5.model;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private List<Mensaje> mensajes;
    private boolean conectado;
    private String modoActual; // "NORMAL", "MAYUS", "MINUS"

    public ClienteModel() {
        this.mensajes = new ArrayList<>();
        this.conectado = false;
        this.modoActual = "NORMAL";
    }

    public boolean conectar(String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
            conectado = true;

            // Hilo para recibir mensajes del servidor
            new Thread(this::recibirMensajes).start();

            return true;
        } catch (IOException e) {
            System.err.println("Error al conectar: " + e.getMessage());
            return false;
        }
    }

    public void enviarMensaje(String mensaje) {
        if (conectado && salida != null) {
            // Transformar el mensaje según el modo actual
            String mensajeTransformado = transformarMensaje(mensaje);
            salida.println(mensajeTransformado);
            mensajes.add(new Mensaje(mensaje, "CLIENTE", modoActual));
        }
    }

    public void cambiarModo(String nuevoModo) {
        this.modoActual = nuevoModo.toUpperCase();
        // Enviar comando especial al servidor para notificar cambio de modo
        if (conectado && salida != null) {
            salida.println("MODO_" + nuevoModo.toUpperCase());
        }
    }

    public void solicitarHistorial() {
        if (conectado && salida != null) {
            salida.println("HISTORIAL");
        }
    }

    private String transformarMensaje(String mensaje) {
        switch (modoActual) {
            case "MAYUS":
                return mensaje.toUpperCase();
            case "MINUS":
                return mensaje.toLowerCase();
            case "NORMAL":
            default:
                return mensaje;
        }
    }

    public String getModoActual() {
        return modoActual;
    }

    private void recibirMensajes() {
        try {
            String mensaje;
            while (conectado && (mensaje = entrada.readLine()) != null) {
                mensajes.add(new Mensaje(mensaje, "SERVIDOR"));
                // Notificar a los observadores
                if (onMensajeRecibido != null) {
                    onMensajeRecibido.accept(new Mensaje(mensaje, "SERVIDOR"));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al recibir mensajes: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public void desconectar() {
        conectado = false;
        try {
            if (salida != null) salida.close();
            if (entrada != null) entrada.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Error al desconectar: " + e.getMessage());
        }
    }

    public List<Mensaje> getMensajes() {
        return new ArrayList<>(mensajes);
    }

    public boolean isConectado() {
        return conectado;
    }

    // Callback para notificar mensajes recibidos
    private java.util.function.Consumer<Mensaje> onMensajeRecibido;

    public void setOnMensajeRecibido(java.util.function.Consumer<Mensaje> callback) {
        this.onMensajeRecibido = callback;
    }
}