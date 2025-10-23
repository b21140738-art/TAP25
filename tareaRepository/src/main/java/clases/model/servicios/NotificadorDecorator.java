package clases.model.servicios;

public abstract class NotificadorDecorator implements Notificador {
    protected Notificador notificadorDecorado;

    public NotificadorDecorator(Notificador notificador) {
        this.notificadorDecorado = notificador;
    }

    @Override
    public void enviar(String mensaje) {
        notificadorDecorado.enviar(mensaje);
    }

    @Override
    public String getTipo() {
        return notificadorDecorado.getTipo();
    }
}