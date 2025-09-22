package strategy;

import models.CajeroModel;
import views.CajeroView;

/**
 * Estrategia para realizar un depósito en la cuenta actual.
 */
public class DepositoStrategy implements OperacionStrategy {

    /**
     * Solicita una cantidad y la deposita en la cuenta.
     *
     * @param model Modelo con la lógica del cajero.
     * @param view Vista para mostrar mensajes al usuario.
     */
    @Override
    public void ejecutar(CajeroModel model, CajeroView view) {
        double cantidad = view.solicitarCantidad("depositar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Cantidad inválida");
            return;
        }
        if (model.realizarDeposito(cantidad)) {
            view.mostrarMensaje("Depósito exitoso de: " + cantidad);
        } else {
            view.mostrarMensaje("No se pudo realizar el depósito");
        }
    }
}
