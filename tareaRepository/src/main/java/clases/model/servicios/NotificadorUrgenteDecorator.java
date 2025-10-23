package clases.model.servicios;

public class NotificadorUrgenteDecorator extends NotificadorDecorator {

    public NotificadorUrgenteDecorator(Notificador notificador) {
        super(notificador);
    }

    @Override
    public void enviar(String mensaje) {
        String mensajeUrgente = "URGENTE: " + mensaje;
        notificadorDecorado.enviar(mensajeUrgente);
    }

    @Override
    public String getTipo() {
        return notificadorDecorado.getTipo() + " (URGENTE)";
    }
}