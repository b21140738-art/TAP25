package clases.model.servicios;


public interface Notificador {
    void enviar(String mensaje);
    String getTipo();
}