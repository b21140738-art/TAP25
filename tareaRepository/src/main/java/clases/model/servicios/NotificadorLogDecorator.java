package clases.model.servicios;

public class NotificadorLogDecorator extends NotificadorDecorator {

    public NotificadorLogDecorator(Notificador notificador) {
        super(notificador);
    }

    @Override
    public void enviar(String mensaje) {
        System.out.println("Log: Enviando notificación tipo " + getTipo());
        notificadorDecorado.enviar(mensaje);
        System.out.println("Log: Notificación enviada exitosamente");
    }
}