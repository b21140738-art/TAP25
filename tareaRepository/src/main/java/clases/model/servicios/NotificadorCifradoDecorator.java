package clases.model.servicios;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class NotificadorCifradoDecorator extends NotificadorDecorator {

        public NotificadorCifradoDecorator(Notificador notificador) {
            super(notificador);
        }

        @Override
        public void enviar(String mensaje) {
            String mensajeHash = generarHashSHA256(mensaje);
            // Enviamos tanto el mensaje original como el hash para demostración
            notificadorDecorado.enviar(mensaje + " [Hash: " + mensajeHash + "]");
        }

        private String generarHashSHA256(String mensaje) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = digest.digest(mensaje.getBytes());
                return bytesToHex(hashBytes);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error al generar hash SHA-256", e);
            }
        }

        private String bytesToHex(byte[] bytes) {
            HexFormat hexFormat = HexFormat.of();
            return hexFormat.formatHex(bytes);
        }

        @Override
        public String getTipo() {
            return notificadorDecorado.getTipo() + " (HASH SHA-256)";
        }
    }