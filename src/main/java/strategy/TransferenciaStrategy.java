package strategy;

import models.CajeroModel;
import views.CajeroView;

/*
 * Estrategia para realizar una transferencia entre cuentas.
 */
public class TransferenciaStrategy implements OperacionStrategy {

    /*
     * Solicita una cantidad y la transfiere a otra cuenta si es posible.
     */
    @Override
    public void ejecutar(CajeroModel model, CajeroView view) {
        double cantidad = view.solicitarCantidad("transferir");
        if (cantidad <= 0) {
            view.mostrarMensaje("Cantidad inválida");
            return;
        }
        if (model.realizarTransferencia(cantidad)) {
            view.mostrarMensaje("Transferencia exitosa de: " + cantidad);
        } else {
            view.mostrarMensaje("No se pudo realizar la transferencia");
        }
    }
}
