package clases.practicaequipo5.model;


import java.io.*;
        import java.net.*;
        import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorModel {
    private ServerSocket serverSocket;
    private ExecutorService poolClientes;
    private boolean servidorActivo;
    private java.util.function.Consumer<String> onLogCallback;

    public ServidorModel() {
        this.poolClientes = Executors.newCachedThreadPool();
        this.servidorActivo = false;
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(9090);
            servidorActivo = true;
            log("Servidor iniciado en puerto 9090");

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

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                salida.println("Bienvenido al servidor de chat! Escribe 'MAYUS' para mayúsculas, 'minus' para minúsculas, o 'normal' para texto normal.");

                String mensajeCliente;
                while ((mensajeCliente = entrada.readLine()) != null) {
                    log("Cliente dice: " + mensajeCliente);

                    String respuesta = procesarMensaje(mensajeCliente);
                    salida.println(respuesta);
                    log("Servidor responde: " + respuesta);
                }

            } catch (IOException e) {
                log("Error con cliente: " + e.getMessage());
            } finally {
                try {
                    if (socket != null) socket.close();
                    log("Cliente desconectado: " + socket.getInetAddress());
                } catch (IOException e) {
                    log("Error cerrando conexión: " + e.getMessage());
                }
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
            log("Cliente dice: " + mensaje);

            // El servidor puede aplicar sus propias reglas además de las del cliente
            String respuesta = "Eco: " + mensaje;
            log("Servidor responde: " + respuesta);

            return respuesta;
        }
    }
}

