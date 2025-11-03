package clases.practicaequipo5.model;

import clases.practicaequipo5.model.decorador.ServidorDecorator;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorModel {
    private ServerSocket serverSocket;
    private ExecutorService poolClientes;
    private boolean servidorActivo;
    private java.util.function.Consumer<String> onLogCallback;
    private ServidorDecorator servidorDecorator;

    public ServidorModel() {
        this.poolClientes = Executors.newCachedThreadPool();
        this.servidorActivo = false;
        this.servidorDecorator = new ServidorDecorator();
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(9090);
            servidorActivo = true;
            log("Servidor iniciado en puerto 9090");
            log("Base de datos MySQL configurada y lista");

            while (servidorActivo) {
                try {
                    Socket clienteSocket = serverSocket.accept();
                    log("Cliente conectado: " + clienteSocket.getInetAddress());

                    // Manejar cada cliente en un hilo separado
                    poolClientes.execute(new ManejadorCliente(clienteSocket));

                } catch (IOException e) {
                    if (servidorActivo) {
                        log("Error aceptando cliente: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            log("Error iniciando servidor: " + e.getMessage());
        }
    }

    public void detenerServidor() {
        servidorActivo = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            poolClientes.shutdown();
            log("Servidor detenido");
        } catch (IOException e) {
            log("Error deteniendo servidor: " + e.getMessage());
        }
    }

    private void log(String mensaje) {
        if (onLogCallback != null) {
            onLogCallback.accept(mensaje);
        }
    }

    public void setOnLogCallback(java.util.function.Consumer<String> callback) {
        this.onLogCallback = callback;
    }

    // Clase interna para manejar cada cliente
    private class ManejadorCliente implements Runnable {
        private Socket socket;
        private BufferedReader entrada;
        private PrintWriter salida;
        private String ipCliente;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
            this.ipCliente = socket.getInetAddress().getHostAddress();
        }

        @Override
        public void run() {
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                salida.println("Bienvenido al servidor de chat! Comandos especiales: HISTORIAL");

                String mensajeCliente;
                while ((mensajeCliente = entrada.readLine()) != null) {
                    // Procesar comando especial HISTORIAL
                    if (mensajeCliente.equalsIgnoreCase("HISTORIAL")) {
                        String historial = obtenerHistorialCliente();
                        salida.println(historial);
                        continue;
                    }

                    log("Cliente " + ipCliente + " dice: " + mensajeCliente);

                    // Guardar mensaje del cliente en BD
                    Mensaje mensajeClienteObj = new Mensaje(mensajeCliente, "CLIENTE", "NORMAL");
                    servidorDecorator.guardarMensajeCliente(mensajeClienteObj, socket);

                }

            } catch (IOException e) {
                log("Error con cliente " + ipCliente + ": " + e.getMessage());
            } finally {
                try {
                    if (socket != null) socket.close();
                    log("Cliente desconectado: " + ipCliente);
                } catch (IOException e) {
                    log("Error cerrando conexión: " + e.getMessage());
                }
            }
        }

        private String obtenerHistorialCliente() {
            try {
                var historial = servidorDecorator.obtenerHistorialPorCliente(ipCliente);
                return servidorDecorator.formatearHistorial(historial);
            } catch (Exception e) {
                return "Error obteniendo historial: " + e.getMessage();
            }
        }

        private String procesarMensaje(String mensaje) {
            // Detectar comandos de cambio de modo
            if (mensaje.startsWith("MODO_")) {
                String modo = mensaje.substring(5);
                switch (modo) {
                    case "MAYUS":
                        return "Servidor: Modo cambiado a MAYÚSCULAS";
                    case "MINUS":
                        return "Servidor: Modo cambiado a minúsculas";
                    case "NORMAL":
                        return "Servidor: Modo cambiado a texto normal";
                    default:
                        return "Servidor: Modo no reconocido";
                }
            }

            // Procesar mensajes normales
            String respuesta = "Eco: " + mensaje;
            return respuesta;
        }
    }
}