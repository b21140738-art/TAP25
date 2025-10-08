package strategy;

import models.CajeroModel;
import views.CajeroView;

/*
 * Estrategia para realizar un retiro de dinero de la cuenta.
 */
public class RetiroStrategy implements OperacionStrategy {

    //Solicita una cantidad y la retira de la cuenta si es posible.

    @Override
    public void ejecutar(CajeroModel model, CajeroView view) {
        double cantidad = view.solicitarCantidad("retirar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Cantidad inválida");
            return;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("Retiro exitoso de: " + cantidad);
        } else {
            view.mostrarMensaje("Saldo insuficiente");
        }
    }
}
